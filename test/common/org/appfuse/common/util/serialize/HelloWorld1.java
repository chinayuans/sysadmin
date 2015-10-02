package org.appfuse.common.util.serialize;

import java.io.Serializable;


public class HelloWorld1 implements Serializable
{
    private String m_sName="20000";
    public String getName()
    {
        return m_sName; 
    }
    
    public void setName(String name){
      m_sName=name;  
    }
}
