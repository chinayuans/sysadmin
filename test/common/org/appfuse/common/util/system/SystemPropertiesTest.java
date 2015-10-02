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
package org.appfuse.common.util.system;

import java.util.Enumeration;
import java.util.Properties;

import junit.framework.TestCase;


public class SystemPropertiesTest
    extends TestCase
{

    public SystemPropertiesTest(String name)
    {
        super(name);
    }


    public final void testGetSystemProperties()
    {
    	int linenumber = new Exception().getStackTrace()[0].getLineNumber(); 
    	System.out.println(linenumber);
        
        Properties props=SystemProperty.getSystemProperties();
        for (Enumeration enum1 = props.propertyNames(); enum1.hasMoreElements();) {
            String key = (String) enum1.nextElement();
            System.out.println(key + " = " + (String) (props.get(key)));
        }
        
        
        
    }

}
