package org.appfuse.web.taglib;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.appfuse.model.taglibs.LabelValue;


public class SelectionOptionsTag extends TagSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(SelectionOptionsTag.class);

	private static final long serialVersionUID = 3905528206810167095L;
    private String name;
    private String keycol;         //value
    private String valuecol;       //display value
    private String defaultvalue;
    private String object;         //objectName
    
    
    
    public String getDefaultvalue() {
		return defaultvalue;
	}

	public String getKeycol() {
		return keycol;
	}

	public String getName() {
		return name;
	}

	public String getObject() {
		return object;
	}

	public String getValuecol() {
		return valuecol;
	}

	

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public void setKeycol(String keycol) {
		this.keycol = keycol;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setValuecol(String valuecol) {
		this.valuecol = valuecol;
	}

	
    public void setName(String name) {
        this.name = name;
    }

    
      
    public int doStartTag() throws JspException {
        
        List list=getValueList(getObject());
       
        StringBuffer sb = new StringBuffer();
        sb.append("<select name=\"" + name + "\" id=\"" + name + "\">\n");

       // if (getDefaultvalue()!= null) {
            sb.append("    <option value=\"\" selected=\"selected\">");
            sb.append("" + "</option>\n");
       // }

        for (Iterator i = list.iterator(); i.hasNext();) {
            Object prop = (Object) i.next();
            sb.append("    <option value=\"" + getPropertyValue(prop,getKeycol()) + "\"");

            if ((getDefaultvalue() != null) && getDefaultvalue().equals(getPropertyValue(prop,getKeycol()))) {
                sb.append(" selected=\"selected\"");
            }

            sb.append(">" + getFilterDisplayValue(prop) + "</option>\n");
        }

        sb.append("</select>");
        
        
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
    private String getFilterDisplayValue(Object object){
    	String return_val="";
    	try {
    		return_val= getDisplayValue(object);
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		}
		return return_val;
    }
    private String getDisplayValue(Object object) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	
    	Map properties_map = BeanUtils.describe(object);
    	Set properties_set = properties_map.keySet();
    	
		// bean的属性；
		String fields[] = new String[properties_set.size() - 1];
		// bean的属性值；
		String values[] = new String[properties_set.size() - 1];
		// bean的属性的类型；
		String types[] = new String[properties_set.size() - 1];

		// 将javabean所有的属性全部取出来放置到数组中去。
		int j = 0;
		for (Iterator iter = properties_set.iterator(); iter.hasNext();) {

			String field = (String) iter.next();
			if (!field.equalsIgnoreCase("class")) {

				// 获得的bean的属性名
				fields[j] = field;

				// 获得对应属性的值
				String value = "";
				if (properties_map.get(field) == null
						|| properties_map.get(field).toString().equals("")) {
					value = "";
					values[j] = value;
				} else {
					value = properties_map.get(field).toString();
					values[j] = value;
				}

				// 获得对应属性的类型
				String type = (PropertyUtils.getPropertyType(object, field))
						.getName();
				types[j] = type;
				j++;
			}

		}
		
		
		String[] filter=getValuecol().split("&");
		String[] fields_filter=new String[filter.length];
		String[] values_filter=new String[filter.length];
		int index=0;
		for (int i = 0; i < filter.length; i++) {
			for (int k = 0; k < fields.length; k++) {
				if (filter[i].equals(fields[k])) {
					fields_filter[index]=fields[k];
					values_filter[index]=values[k];
					index++;
				}
			}
		}	
		
//		for (int i = 0; i < fields_filter.length; i++) {
//			System.out.println("fields:"+fields_filter[i]);
//			System.out.println("values:"+values_filter[i]);
//		}
		
		StringBuffer merge_values=new StringBuffer();
    	for (int w = 0; w < values_filter.length; w++) {
    		if (w==values_filter.length-1) {
    			merge_values.append(values_filter[w]);
			}
    		else{
    			merge_values.append(values_filter[w]+"-");
    		}
		}
    	
//    	System.out.println(merge_values);
    	
		
    	return merge_values.toString();
    }
    
    
    private List getValueList(String BO_Name){  	
    	
    	
    	 List list = new ArrayList();
//      	初始化数据
          for (int i = 0; i < 10; i++) { 
              	LabelValue label=new LabelValue();
              	label.setValue(""+i);
              	label.setProp1("a"+i);
              	label.setProp2("b"+i);
              	label.setProp3("c"+i);
              	label.setProp4("d"+i);
              	 if (!list.contains(label)) {
                       list.add(label);
                   }
          }
          
//          
//          for (Iterator iter = list.iterator(); iter.hasNext();) {
//			LabelValue element = (LabelValue) iter.next();
//			System.out.println("value:"+element.getValue());
//		  }
          return list;
    }
    
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, JspException {
    	
    	 SelectionOptionsTag local=new SelectionOptionsTag();
         local.setName("name");
         local.setDefaultvalue("2");
         local.setObject("LabelValue");
         local.setValuecol("prop1&prop2&");
         local.setKeycol("value");
         local.doStartTag();
	}
   
    

	
}
