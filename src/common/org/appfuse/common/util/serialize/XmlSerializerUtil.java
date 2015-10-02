/*
 Java Reflection in Action
 Ira R. Forman and Nate Forman
 ISBN 1932394184
 Publisher: Manning Publications Co.
 */

package org.appfuse.common.util.serialize;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;


public class XmlSerializerUtil
{

    //start extract serializeObject

    public static Document serializeObject(Object source)
        throws Exception
    {
        return serializeHelper(source, new Document(new Element("serialized")),
            new IdentityHashMap());
    }


    private static Document serializeHelper(Object source,Document target,Map table)
        throws Exception
    {
        String id = Integer.toString(table.size()); //#1
        table.put(source, id); //#1
        Class sourceclass = source.getClass(); //#1

        Element oElt = new Element("object"); //#2
        oElt.setAttribute("class", sourceclass.getName()); //#2
        oElt.setAttribute("id", id); //#2
        target.getRootElement().addContent(oElt); //#2

        if (!sourceclass.isArray()) { //#3
            Field[] fields = Mopex.getInstanceVariables(sourceclass); //#4
            for (int i = 0; i < fields.length; i++) { //#4

                if (!Modifier.isPublic(fields[i].getModifiers())) //#5
                    fields[i].setAccessible(true); //#5

                Element fElt = new Element("field"); //#6
                fElt.setAttribute("name", fields[i].getName()); //#6
                Class declClass = fields[i].getDeclaringClass(); //#6
                fElt.setAttribute("declaringclass", //#6
                    declClass.getName()); //#6
                //#6
                Class fieldtype = fields[i].getType(); //#6
                Object child = fields[i].get(source); //#6
                //#6
                if (Modifier.isTransient(fields[i].getModifiers())) { //#6
                    child = null; //#6
                } //#6
                fElt.addContent(serializeVariable(fieldtype, child, //#6
                    target, table)); //#6
                //#6
                oElt.addContent(fElt); //#6
            }
        }
        else {
            Class componentType = sourceclass.getComponentType(); //#7
            //#7
            int length = Array.getLength(source); //#7
            oElt.setAttribute("length", Integer.toString(length)); //#7
            //#7
            for (int i = 0; i < length; i++) { //#7
                oElt.addContent(serializeVariable(componentType, //#7
                    Array.get(source, i),//#7
                    target, //#7
                    table)); //#7
            } //#7
        }
        return target;
    }


    //stop extract serializeObject

    //start extract serializeVariable

    private static Element serializeVariable(Class fieldtype,
        Object child,
        Document target,
        Map table)
        throws Exception
    {
        if (child == null) {
            return new Element("null");
        }
        else if (!fieldtype.isPrimitive()) {
            Element reference = new Element("reference");
            if (table.containsKey(child)) {
                reference.setText(table.get(child).toString());
            }
            else {
                reference.setText(Integer.toString(table.size()));
                serializeHelper(child, target, table);
            }
            return reference;
        }
        else {
            Element value = new Element("value");
            value.setText(child.toString());
            return value;
        }
    }


    //stop extract serializeVariable

    //start extract deserializeObject

    public static Object deserializeObject(Document source)
        throws Exception
    {
        List objList = source.getRootElement().getChildren();

        Map table = new HashMap();

        createInstances(table, objList);

        assignFieldValues(table, objList);

        return table.get("0");
    }


    //stop extract deserializeObject

    //start extract createInstances

    private static void createInstances(Map table,
        List objList)
        throws Exception
    {
        for (int i = 0; i < objList.size(); i++) {
            Element oElt = (Element) objList.get(i);
            Class cls = Class.forName(oElt.getAttributeValue("class"));
            Object instance = null;
            if (!cls.isArray()) {
                Constructor c = cls.getDeclaredConstructor(null);
                if (!Modifier.isPublic(c.getModifiers())) {
                    c.setAccessible(true);
                }
                instance = c.newInstance(null);
            }
            else {
                instance = Array.newInstance(cls.getComponentType(), Integer
                    .parseInt(oElt.getAttributeValue("length")));
            }
            table.put(oElt.getAttributeValue("id"), instance);
        }
    }


    //stop extract createInstances

    //start extract assignFieldValues

    private static void assignFieldValues(Map table,
        List objList)
        throws Exception
    {
        for (int i = 0; i < objList.size(); i++) {
            Element oElt = (Element) objList.get(i);
            Object instance = table.get(oElt.getAttributeValue("id"));
            List fElts = oElt.getChildren();
            if (!instance.getClass().isArray()) {
                for (int j = 0; j < fElts.size(); j++) {
                    Element fElt = (Element) fElts.get(j);
                    String className = fElt.getAttributeValue("declaringclass");
                    Class fieldDC = Class.forName(className);
                    String fieldName = fElt.getAttributeValue("name");
                    Field f = fieldDC.getDeclaredField(fieldName);
                    if (!Modifier.isPublic(f.getModifiers())) {
                        f.setAccessible(true);
                    }

                    Element vElt = (Element) fElt.getChildren().get(0);
                    f.set(instance, deserializeValue(vElt, f.getType(), table));
                }
            }
            else {
                Class comptype = instance.getClass().getComponentType();
                for (int j = 0; j < fElts.size(); j++) {
                    Array.set(instance, j, deserializeValue((Element) fElts.get(j),
                        comptype, table));
                }
            }
        }
    }


    //stop extract assignFieldValues

    //start extract deserializeValue

    private static Object deserializeValue(Element vElt,
        Class fieldType,
        Map table)
        throws ClassNotFoundException
    {
        String valtype = vElt.getName();
        if (valtype.equals("null")) {
            return null;
        }
        else if (valtype.equals("reference")) {
            return table.get(vElt.getText());
        }
        else {
            if (fieldType.equals(boolean.class)) {
                if (vElt.getText().equals("true")) {
                    return Boolean.TRUE;
                }
                else {
                    return Boolean.FALSE;
                }
            }
            else if (fieldType.equals(byte.class)) {
                return Byte.valueOf(vElt.getText());
            }
            else if (fieldType.equals(short.class)) {
                return Short.valueOf(vElt.getText());
            }
            else if (fieldType.equals(int.class)) {
                return Integer.valueOf(vElt.getText());
            }
            else if (fieldType.equals(long.class)) {
                return Long.valueOf(vElt.getText());
            }
            else if (fieldType.equals(float.class)) {
                return Float.valueOf(vElt.getText());
            }
            else if (fieldType.equals(double.class)) {
                return Double.valueOf(vElt.getText());
            }
            else if (fieldType.equals(char.class)) {
                return new Character(vElt.getText().charAt(0));
            }
            else {
                return vElt.getText();
            }
        }
    }
    //stop extract deserializeValue

}


abstract class Mopex
{

    /**
     * Returns a syntactically correct name for a class object. If the class
     * object represents an array, the proper number of square bracket pairs are
     * appended to the component type.
     * 
     * @return java.lang.String
     * @param cls
     *            java.lang.Class
     */
    //start extract classNameToString
    public static String getTypeName(Class cls)
    {
        if (!cls.isArray()) {
            return cls.getName();
        }
        else {
            return getTypeName(cls.getComponentType()) + "[]";
        }
    }


    //stop extract classNameToString

    /**
     * Returns an array of the superclasses of cls.
     * 
     * @return java.lang.Class[]
     * @param cls
     *            java.lang.Class
     */
    //start extract getSuperclasses
    public static Class[] getSuperclasses(Class cls)
    {
        int i = 0;
        for (Class x = cls.getSuperclass(); x != null; x = x.getSuperclass())
            i++;
        Class[] result = new Class[i];
        i = 0;
        for (Class x = cls.getSuperclass(); x != null; x = x.getSuperclass())
            result[i++] = x;
        return result;
    }


    //stop extract getSuperclasses

    /**
     * Returns an array of the instance variablies of the the specified class.
     * An instance variable is defined to be a non-static field that is declared
     * by the class or inherited.
     * 
     * @return java.lang.Field[]
     * @param cls
     *            java.lang.Class
     */
    //start extract getInstanceVariables
    public static Field[] getInstanceVariables(Class cls)
    {
        List accum = new LinkedList();
        while (cls != null) {
            Field[] fields = cls.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (!Modifier.isStatic(fields[i].getModifiers())) {
                    accum.add(fields[i]);
                }
            }
            cls = cls.getSuperclass();
        }
        Field[] retvalue = new Field[accum.size()];
        return (Field[]) accum.toArray(retvalue);
    }


    //stop extract getInstanceVariables

    /**
     * Returns an array of fields that are the declared instance variables of
     * cls. An instance variable is a field that is not static.
     * 
     * @return java.lang.reflect.Field[]
     * @param cls
     *            java.lang.Class
     */
    //start extract getDeclaredIVS
    public static Field[] getDeclaredIVs(Class cls)
    {
        Field[] fields = cls.getDeclaredFields();
        // Count the IVs
        int numberOfIVs = 0;
        for (int i = 0; i < fields.length; i++) {
            if (!Modifier.isStatic(fields[i].getModifiers()))
                numberOfIVs++;
        }
        Field[] declaredIVs = new Field[numberOfIVs];
        // Populate declaredIVs
        int j = 0;
        for (int i = 0; i < fields.length; i++) {
            if (!Modifier.isStatic(fields[i].getModifiers()))
                declaredIVs[j++] = fields[i];
        }
        return declaredIVs;
    }


    //stop extract getDeclaredIVS

    /**
     * Return an array of the supported instance variables of this class. A
     * supported instance variable is not static and is either declared or
     * inherited from a superclass.
     * 
     * @return java.lang.reflect.Field[]
     * @param cls
     *            java.lang.Class
     */
    //start extract getSupportedIVS
    public static Field[] getSupportedIVs(Class cls)
    {
        if (cls == null) {
            return new Field[0];
        }
        else {
            Field[] inheritedIVs = getSupportedIVs(cls.getSuperclass());
            Field[] declaredIVs = getDeclaredIVs(cls);
            Field[] supportedIVs = new Field[declaredIVs.length + inheritedIVs.length];
            for (int i = 0; i < declaredIVs.length; i++) {
                supportedIVs[i] = declaredIVs[i];
            }
            for (int i = 0; i < inheritedIVs.length; i++) {
                supportedIVs[i + declaredIVs.length] = inheritedIVs[i];
            }
            return supportedIVs;
        }
    }


    //stop extract getSupportedIVS

    /**
     * Returns an array of the methods that are not static.
     * 
     * @return java.lang.reflect.Method[]
     * @param cls
     *            java.lang.Class
     */
    //start extract getInstanceMethods
    public static Method[] getInstanceMethods(Class cls)
    {
        List instanceMethods = new ArrayList();
        for (Class c = cls; c != null; c = c.getSuperclass()) {
            Method[] methods = c.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++)
                if (!Modifier.isStatic(methods[i].getModifiers()))
                    instanceMethods.add(methods[i]);
        }
        Method[] ims = new Method[instanceMethods.size()];
        for (int j = 0; j < instanceMethods.size(); j++)
            ims[j] = (Method) instanceMethods.get(j);
        return ims;
    }


    //stop extract getInstanceMethods

    /**
     * Returns an array of methods to which instances of this class respond.
     * 
     * @return java.lang.reflect.Method[]
     * @param cls
     *            java.lang.Class
     */
    //start extract getSupportedMethods
    public static Method[] getSupportedMethods(Class cls)
    {
        return getSupportedMethods(cls, null);
    }


    //stop extract getSupportedMethods

    /**
     * This method retrieves the modifiers of a Method without the unwanted
     * modifiers specified in the second parameter. Because this method uses
     * bitwise operations, multiple unwanted modifiers may be specified by
     * bitwise or.
     * 
     * @return int
     * @param m
     *            java.lang.Method
     * @param unwantedModifiers
     *            int
     */
    //start extract getModifiersWithout
    public static int getModifiersWithout(Method m,
        int unwantedModifiers)
    {
        int mods = m.getModifiers();
        return (mods ^ unwantedModifiers) & mods;
    }


    //stop extract getModifiersWithout

    /**
     * Returns a Method that has the signature specified by the calling
     * parameters.
     * 
     * @return Method
     * @param cls
     *            java.lang.Class
     * @param name
     *            String
     * @param paramTypes
     *            java.lang.Class[]
     */
    //start extract getSupportedMethod
    public static Method getSupportedMethod(Class cls,
        String name,
        Class[] paramTypes)
        throws NoSuchMethodException
    {
        if (cls == null) {
            throw new NoSuchMethodException();
        }
        try {
            return cls.getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException ex) {
            return getSupportedMethod(cls.getSuperclass(), name, paramTypes);
        }
    }


    //stop extract getSupportedMethod
    /**
     * Returns a Method array of the methods to which instances of the specified
     * respond except for those methods defined in the class specifed by limit
     * or any of its superclasses. Note that limit is usually used to eliminate
     * them methods defined by java.lang.Object.
     * 
     * @return Method[]
     * @param cls
     *            java.lang.Class
     * @param limit
     *            java.lang.Class
     */
    //start extract getSupportedMethods
    public static Method[] getSupportedMethods(Class cls,
        Class limit)
    {
        Vector supportedMethods = new Vector();
        for (Class c = cls; c != limit; c = c.getSuperclass()) {
            Method[] methods = c.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                boolean found = false;
                for (int j = 0; j < supportedMethods.size(); j++)
                    if (equalSignatures(methods[i], (Method) supportedMethods
                        .elementAt(j))) {
                        found = true;
                        break;
                    }
                if (!found)
                    supportedMethods.add(methods[i]);
            }
        }
        Method[] mArray = new Method[supportedMethods.size()];
        for (int k = 0; k < mArray.length; k++)
            mArray[k] = (Method) supportedMethods.elementAt(k);
        return mArray;
    }

    //stop extract getSupportedMethods

    /**
     * This field is initialized with a method object for the equalSignatures
     * method. This is an optimization in that selectMethods can use this field
     * instead of calling getMethod each time it is called.
     */

    //start extract equalSignaturesMethod
    static private Method equalSignaturesMethod;

    static {
        Class[] fpl = { Method.class, Method.class };
        try {
            equalSignaturesMethod = Mopex.class.getMethod("equalSignatures", fpl);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    //stop extract equalSignaturesMethod
    /**
     * Determines if the signatures of two method objects are equal. In Java, a
     * signature comprises the method name and the array of of formal parameter
     * types. For two signatures to be equal, the method names must be the same
     * and the formal parameters must be of the same type (in the same order).
     * 
     * @return boolean
     * @param m1
     *            java.lang.Method
     * @param m2
     *            java.lang.Method
     */
    //start extract equalSignatures
    public static boolean equalSignatures(Method m1,
        Method m2)
    {
        if (!m1.getName().equals(m2.getName()))
            return false;
        if (!Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes()))
            return false;
        return true;
    }


    //stop extract equalSignatures

    /**
     * Return a string that represents the signature of the specified method.
     * 
     * @return String
     * @param m
     *            java.lang.Method
     */
    //start extract signatureToString
    public static String signatureToString(Method m)
    {
        return m.getName() + "(" + formalParametersToString(m.getParameterTypes()) + ")";
    }


    //stop extract signatureToString
    /**
     * Returns a string that can be used as a formal parameter list for a method
     * that has the parameter types of the specified array.
     * 
     * @return String
     * @param pts
     *            java.lang.Class[]
     */
    //start extract formalParametersToString
    public static String formalParametersToString(Class[] pts)
    {
        String result = "";
        for (int i = 0; i < pts.length; i++) {
            result += getTypeName(pts[i]) + " p" + i;
            if (i < pts.length - 1)
                result += ",";
        }
        return result;
    }


    //stop extract formalParametersToString
    /**
     * Returns a string that is an actual parameter list that matches the formal
     * parameter list produced by formalParametersToString.
     * 
     * @return String
     * @param pts
     *            java.lang.Class[]
     */
    //start extract actualParametersToString
    public static String actualParametersToString(Class[] pts)
    {
        String result = "";
        for (int i = 0; i < pts.length; i++) {
            result += "p" + i;
            if (i < pts.length - 1)
                result += ",";
        }
        return result;
    }


    //stop extract actualParametersToString

    /**
     * Returns a String that represents the header for a constructor.
     * 
     * @return String
     * @param c
     *            java.lang.Constructor
     */
    //start extract constructorHeaderToString
    public static String headerToString(Constructor c)
    {
        String mods = Modifier.toString(c.getModifiers());
        if (mods.length() == 0)
            return headerSuffixToString(c);
        else
            return mods + " " + headerSuffixToString(c);
    }


    //stop extract constructorHeaderToString
    /**
     * Returns a String that represents the header suffix for a constructor. The
     * term "header suffix" is not a standard Java term. We use it to mean the
     * Java header without the modifiers.
     * 
     * @return String
     * @param c
     *            java.lang.Constructor
     */
    //start extract constructorHeaderToString
    public static String headerSuffixToString(Constructor c)
    {
        String header = signatureToString(c);
        Class[] eTypes = c.getExceptionTypes();
        if (eTypes.length != 0)
            header += " throws " + classArrayToString(eTypes);
        return header;
    }


    //stop extract constructorHeaderToString
    /**
     * Returns a String that represents the signature for a constructor.
     * 
     * @return String
     * @param c
     *            java.lang.Constructor
     */
    //start extract constructorHeaderToString
    public static String signatureToString(Constructor c)
    {
        return c.getName() + "(" + formalParametersToString(c.getParameterTypes()) + ")";
    }


    //stop extract constructorHeaderToString

    /**
     * Returns a String that represents the header of a method.
     * 
     * @return String
     * @param m
     *            java.lang.Method
     */
    //start extract headerToString
    public static String headerToString(Method m)
    {
        String mods = Modifier.toString(m.getModifiers());
        if (mods.length() == 0)
            return headerSuffixToString(m);
        else
            return mods + " " + headerSuffixToString(m);
    }


    //stop extract headerToString
    /**
     * Returns a String that represents the suffix of the header of a method.
     * The suffix of a header is not a standard Java term. We use the term to
     * mean the Java header without the method modifiers.
     * 
     * @return String
     * @param m
     *            java.lang.Method
     */
    //start extract headerToString
    public static String headerSuffixToString(Method m)
    {
        String header = getTypeName(m.getReturnType()) + " " + signatureToString(m);
        Class[] eTypes = m.getExceptionTypes();
        if (eTypes.length != 0) {
            header += " throws " + classArrayToString(eTypes);
        }
        return header;
    }


    //stop extract headerToString
    /**
     * Returns a String that is a comma separated list of the typenames of the
     * classes in the array pts.
     * 
     * @return String
     * @param pts
     *            java.lang.Class[]
     */
    //start extract classArrayToString
    public static String classArrayToString(Class[] pts)
    {
        String result = "";
        for (int i = 0; i < pts.length; i++) {
            result += getTypeName(pts[i]);
            if (i < pts.length - 1)
                result += ",";
        }
        return result;
    }


    //stop extract classArrayToString

    /**
     * Turns true if and only if the header suffixes of the two specified
     * methods are equal. The header suffix is defined to be the signature, the
     * return type, and the exception types.
     * 
     * @return boolean
     * @param m1
     *            java.lang.Method
     * @param m2
     *            java.lang.Method
     */
    public static boolean equalsHeaderSuffixes(Method m1,
        Method m2)
    {
        if (m1.getReturnType() != m2.getReturnType())
            return false;
        if (!Arrays.equals(m1.getExceptionTypes(), m2.getExceptionTypes()))
            return false;
        return equalSignatures(m1, m2);
    }


    /**
     * Creates constructor with the signature of c and a new name. It adds some
     * code after generating a super statement to call c. This method is used
     * when generating a subclass of class that declared c.
     * 
     * @return String
     * @param c
     *            java.lang.Constructor
     * @param name
     *            String
     * @param code
     *            String
     */
    //start extract createRenamedConstructor
    public static String createRenamedConstructor(Constructor c,
        String name,
        String code)
    {
        Class[] pta = c.getParameterTypes();
        String fpl = formalParametersToString(pta);
        String apl = actualParametersToString(pta);
        Class[] eTypes = c.getExceptionTypes();
        String result = name + "(" + fpl + ")\n";
        if (eTypes.length != 0)
            result += "    throws " + classArrayToString(eTypes) + "\n";
        result += "{\n    super(" + apl + ");\n" + code + "}\n";
        return result;
    }


    //stop extract createRenamedConstructor

    /**
     * Returns a String that is formatted as a Java method declaration having
     * the same header as the specified method but with the code parameter
     * substituted for the method body.
     * 
     * @return String
     * @param m
     *            java.lang.Method
     * @param code
     *            String
     */
    //start extract createReplacementMethod
    public static String createReplacementMethod(Method m,
        String code)
    {
        Class[] pta = m.getParameterTypes();
        String fpl = formalParametersToString(pta);
        Class[] eTypes = m.getExceptionTypes();
        String result = m.getName() + "(" + fpl + ")\n";
        if (eTypes.length != 0)
            result += "    throws " + classArrayToString(eTypes) + "\n";
        result += "{\n" + code + "}\n";
        return result;
    }


    //stop extract createReplacementMethod

    /**
     * Returns a string for a cooperative override of the method m. That is, The
     * string has the same return type and signature as m but the body has a
     * super call that is sandwiched between the strings code1 and code2.
     * 
     * @return String
     * @param m
     *            java.lang.Method
     * @param code1
     *            String
     * @param code2
     *            String
     */
    //start extract createCooperativeWrapper
    public static String createCooperativeWrapper(Method m,
        String code1,
        String code2)
    {
        Class[] pta = m.getParameterTypes();
        Class retType = m.getReturnType();
        String fpl = formalParametersToString(pta);
        String apl = actualParametersToString(pta);
        Class[] eTypes = m.getExceptionTypes();
        String result = retType.getName() + " " + m.getName() + "(" + fpl + ")\n";
        if (eTypes.length != 0)
            result += "    throws " + classArrayToString(eTypes) + "\n";
        result += "{\n" + code1 + "    ";
        if (retType != void.class)
            result += retType.getName() + " cooperativeReturnValue = ";
        result += "super." + m.getName() + "(" + apl + ");\n";
        result += code2;
        if (retType != void.class)
            result += "    return cooperativeReturnValue;\n";
        result += "}\n";
        return result;
    }


    /**
     * Returns the method object for the unique method named mName. If no such
     * method exists, a null is returned. If there is more than one such method,
     * a runtime exception is thrown.
     * 
     * @return Method
     * @param cls
     *            java.lang.Class
     * @param mName
     *            String
     */
    public static Method getUniquelyNamedMethod(Class cls,
        String mName)
    {
        Method result = null;
        Method[] mArray = cls.getDeclaredMethods();
        for (int i = 0; i < mArray.length; i++)
            if (mName.equals(mArray[i].getName())) {
                if (result == null)
                    result = mArray[i];
                else
                    throw new RuntimeException("name is not unique");
            }
        return result;
    }


    /**
     * Finds the first (from the bottom of the inheritance hierarchy) field with
     * the specified name. Note that Class.getField returns only public fields.
     * 
     * @return Field
     * @param cls
     *            java.lang.Class
     * @param name
     *            String
     */
    //start extract findField
    public static Field findField(Class cls,
        String name)
        throws NoSuchFieldException
    {
        if (cls != null) {
            try {
                return cls.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                return findField(cls.getSuperclass(), name);
            }
        }
        else {
            throw new NoSuchFieldException();
        }
    }

}
