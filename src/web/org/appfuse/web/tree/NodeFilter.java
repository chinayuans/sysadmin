
package org.appfuse.web.tree;


/**
 * 结点过滤接口. <br/>
* 此接口用于从目录树中查找符合一定条件的结点。<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author flyxxxxx
 * @version 1.0
 */
public interface NodeFilter
{
  /**
   * 判断结点是否满足一定条件.
   * @param n Node 要判断的结点
   * @return boolean 满足条件返回true
   */
  public boolean accept(Node n);
}