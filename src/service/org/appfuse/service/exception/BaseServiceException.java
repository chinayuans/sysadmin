/*
 * project name : sysadmin
 * package name : org.appfuse.service.exception
 * file    name : BaseServiceException.java
 * class   name : BaseServiceException
 * Created on 2005-10-19 8:09:47
 * creator ---Joson Yuan
 * author comments:
 * 
 * ֧�����¹��ܣ�
 * 1��֧���쳣����,rootCause����ָ��ԭʼ�쳣
 * 2��֧�ֶ������쳣��exceptions���Դ������Ƕ�׵��쳣
 * 3��֧�ֺ�Struts��Resouce Bundle �󶨣�messageKey����ָ����Ϣkey,Ϊstruts����׼����
 * 4��֧�ָ���ʽ��Ϣ��messageArgs����ָ������ʽ��Ϣ�еĲ���
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
