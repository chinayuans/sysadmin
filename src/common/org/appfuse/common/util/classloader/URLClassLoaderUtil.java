/*BEGIN OCO COPYRIGHT
 *************************************************************************
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724-L01, 5655-N53, 5724-I82, 5655-R15
 * (C) Copyright IBM Corporation 2006.
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 *
 **************************************************************************
 *END OCO COPYRIGHT
 */
package org.appfuse.common.util.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderUtil {
    
    public static Object getInstance(String[] jarsLocation,String fullClassName ){
        URL[] urls = new URL[jarsLocation.length];
        for (int i = 0; i < jarsLocation.length; i++) {
            try {
                urls[i]= new URL("file:"+jarsLocation[i]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        
        URLClassLoader ucl = new URLClassLoader(urls);
        Class clazz=null;
        try {
            clazz = ucl.loadClass(fullClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        Object instance=null;
        try {
            instance= clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return instance;
    }
}
