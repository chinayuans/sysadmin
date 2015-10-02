/*
 * project name : equinox-self
 * package name : org.appfuse.model
 * file    name : BaseObject.java
 * class   name : BaseObject
 * Created on 2005-7-22 9:55:47
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.audit.Auditable;

public class BaseObject implements Serializable ,Auditable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 262762448347228623L;
	private String id=null;
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id=id;
	}
	
	public boolean equals(Object obj) {
		
		return EqualsBuilder.reflectionEquals(this,obj);
	}

	
	public int hashCode() {
		
		return HashCodeBuilder.reflectionHashCode(this);
	}

	
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
    

}
