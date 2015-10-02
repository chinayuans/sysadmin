/*
 * project name : sysadmin
 * package name : org.appfuse.service.handleServiceExceptionSample
 * file    name : HandleServiceException.java
 * class   name : HandleServiceException
 * Created on 2005-10-19 9:23:39
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.service.handleServiceExceptionSample;

import org.appfuse.service.base.BaseService;
import org.appfuse.service.exception.BaseServiceException;

public interface HandleServiceException extends BaseService{
  public void businessMethod() throws BaseServiceException;
}
