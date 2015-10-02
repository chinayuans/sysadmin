/*
 * project name : equinox-self
 * package name : org.appfuse.model.audit
 * file    name : Auditable.java
 * class   name : Auditable
 * Created on 2005-8-3 9:41:24
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.audit;

/**
 * 凡是要参与审计功能的Domain Object 必须实现下面的接口
 * ，这样他才可以得到审计
 * Created on 2005-8-3 9:41:29
 * @author ---Joson Yuan
 * author comments:
 *
 */
public interface Auditable {
   public String getId();
}
