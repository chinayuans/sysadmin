/*
 * project name : sysadmin
 * package name : org.appfuse.service.exception
 * file    name : DisobeyRuleException.java
 * class   name : DisobeyRuleException
 * Created on 2005-10-19 9:15:44
 * creator ---Joson Yuan
 * author comments:
 * 
 * 注意：在org.appfuse.service.exception中定义了所有
 * 应用异常（也就是服务层中的所有业务逻辑Exception包含在这里面）。
 * 这些异常全部继承org.appfuse.web.exception.BaseException。
 * 
 */
package org.appfuse.service.exception;


public class DisobeyRuleException extends BaseServiceException {
	
	private static final long serialVersionUID = 4290289247320118115L;

	public DisobeyRuleException() {
	}
}
