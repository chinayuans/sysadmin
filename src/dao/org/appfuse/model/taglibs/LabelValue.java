/*
 * project name : sysadmin
 * package name : org.appfuse.model.taglibs
 * file    name : ListValues.java
 * class   name : ListValues
 * Created on 2006-2-20 16:08:20
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.taglibs;

/**
 * Created on 2006-2-20 16:08:20
 * @author ---Joson Yuan
 * author comments:
 * 
 */
public class LabelValue {
	private String value;  //keyValue
	private String label;   //displayValue
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	
	
	public String getLabel() {
		String local_label="";
		if (label==null||label.equals("")) {
			local_label=getProp1()+"-"+getProp2()+"-"+getProp3()+"-"+getProp4()+"";
		}
		else {
			local_label=label;
		}
		return local_label;
	}
	public String getProp1() {
		return prop1;
	}
	public String getProp2() {
		return prop2;
	}
	public String getProp3() {
		return prop3;
	}
	public String getProp4() {
		return prop4;
	}
	public String getValue() {
		return value;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}
	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
