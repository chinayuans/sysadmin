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
package org.appfuse.common.util.serialize;

import java.io.Serializable;


public class HelloWorld implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 5362978033127103447L;
    private String m_sName="";
    private String property="";
    
    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public String getName()
    {
        return m_sName; 
    }
    
    public void setName(String name){
      m_sName=name;  
    }
    
    
}
