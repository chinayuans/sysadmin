/*
 * project name : sysadmin
 * package name : org.appfuse.service.exception
 * file    name : BaseServiceException.java
 * class   name : BaseServiceException
 * Created on 2005-10-19 8:09:47
 * creator ---Joson Yuan
 * author comments:
 * 
 * 支持以下功能：
 * 1。支持异常级联,rootCause属性指定原始异常
 * 2。支持多样化异常，exceptions属性存放所有嵌套的异常
 * 3。支持和Struts的Resouce Bundle 绑定，messageKey属性指定消息key,为struts层做准备。
 * 4。支持复合式消息，messageArgs属性指定复合式消息中的参数
 */
package org.appfuse.service.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BaseServiceException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4798681892080927450L;
	protected Throwable rootCause=null;
	private List exceptions =new ArrayList();
	private String messageKey=null;
	private Object[] messageArgs=null;
	public BaseServiceException() {
		super();
	}

	
	public BaseServiceException(Throwable rootCause) {
		this.rootCause=rootCause;
	}

	//rootCause:
	public Throwable getRootCause() {
		return rootCause;
	}


	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	//exceptions:
	public void addException(BaseServiceException ex){
		exceptions.add(ex);
	}
	
	public List getExceptions(){
		return exceptions;
	}

	//MessageArgs:
	public Object[] getMessageArgs() {
		return messageArgs;
	}


	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}
	
    //MessageKey:
	public String getMessageKey() {
		return messageKey;
	}
	
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}


	//printStackTrace:
	public void printStackTrace(){
		printStackTrace(System.err);
	}
	
	public void printStackTrace(PrintStream outStream){
		printStackTrace(new PrintWriter(outStream));
	}
	
	public void printStackTrace(PrintWriter writer){
		super.printStackTrace(writer);
		if (getRootCause()!=null) {
			getRootCause().printStackTrace(writer);
		}
		writer.flush();
	}

}
