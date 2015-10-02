package org.appfuse.web.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.appfuse.model.taglibs.LabelValue;


public class RadioOptionsTag extends TagSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(RadioOptionsTag.class);

	private static final long serialVersionUID = 3905528206810167095L;
    private String name;
    private String msg;         
    private String defaultvalue;
    
   
	public String getDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		String[] pairs=defaultvalue.split("-");
		this.defaultvalue = pairs[0];
	}

	public String getName() {
		return name;
	}
	
    public void setName(String name) {
        this.name = name;
    }

          
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int doStartTag() throws JspException {
        
        List list=getValueList(getMsg());
       
        StringBuffer sb = new StringBuffer();
        for (Iterator i = list.iterator(); i.hasNext();) {
            Object prop = (Object) i.next();
            sb.append("    <input type=radio name=\""+getName()+"\" value=\"" + getPropertyValue(prop,"value") + "\"");
            if ((getDefaultvalue() != null) && getDefaultvalue().equals(getPropertyValue(prop,"value"))) {
                sb.append(" checked ");
            }
            sb.append(">" + getPropertyValue(prop,"label") + "</input>\n");
        }
        
		if (logger.isDebugEnabled()) {
			logger.debug("doStartTag()" + sb);
		}
        
        
        
        try {
            pageContext.getOut().write(sb.toString());
        } catch (IOException io) {
            throw new JspException(io);
        }

        return super.doStartTag();
    }

    public void release() {
        super.release();
    }
    
    private String getPropertyValue(Object bean, String prop_name){
//    	System.out.println("prop_bean:"+bean);
//    	System.out.println("prop_name:"+prop_name);
    	String prop_value="";
    	try {
			prop_value= BeanUtils.getProperty(bean,prop_name);
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		}
		return prop_value;
    }
   
    
    private List getValueList(String msg){  	
    	StringTokenizer st = new StringTokenizer(msg, "|");
    	List list = new ArrayList();
        while (st.hasMoreTokens()) {
        	String map = st.nextToken();
        	String[] pairs=map.split("-");
        	if (pairs.length==2) {
        		LabelValue label=new LabelValue();
            	label.setValue(pairs[0]);
            	label.setLabel(pairs[1]);
            	if (!list.contains(label)) {
                    list.add(label);
                }
			}        	
        }   
    
          
//          for (Iterator iter = list.iterator(); iter.hasNext();) {
//			LabelValue element = (LabelValue) iter.next();
//			System.out.println("value:"+element.getValue());
//		  }
          return list;
    }
    
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, JspException {
    	
    	 RadioOptionsTag local=new RadioOptionsTag();
         local.setName("name");
         local.setMsg("1-beijing|2-tianjing|3+shanghai|");
         local.setDefaultvalue("2");
         local.doStartTag();
	}
   
    

	
}
