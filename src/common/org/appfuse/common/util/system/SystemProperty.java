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

import java.util.Properties;


public class SystemProperty
{
    private SystemProperty(){
    }

    public static Properties getSystemProperties(){
        return System.getProperties();
    }
}
