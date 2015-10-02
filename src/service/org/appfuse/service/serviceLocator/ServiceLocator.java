/*
 * project name : equinox-self
 * package name : org.appfuse.service.serviceLocator
 * file    name : ServiceLocator.java
 * class   name : ServiceLocator
 * Created on 2005-8-3 12:46:57
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.serviceLocator;

import org.appfuse.service.audit.AuditService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Service 的定位器，专门用于查找service,整个系统只有一个定位器。
 * Created on 2005-8-3 12:58:03
 * @author ---Joson Yuan
 * author comments:
 *
 */
public class ServiceLocator {

	private static ApplicationContext ctx;
	
	static {
		String[] paths = { "/WEB-INF/applicationContext-*.xml" };
		ctx = new ClassPathXmlApplicationContext(paths);
	}
	public static AuditService getAuditService() {
		return (AuditService) ctx.getBean("auditService");
	}

}
