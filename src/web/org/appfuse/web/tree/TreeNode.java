/*
 * Created on 2005-1-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.appfuse.web.tree;

/**
 * 目录树的一个结点. <br/>
 * 要将一组对象转化为目录树，它必须实现此接口或{@link Container}接口。<br/>
 * 通过实现此接口的目录树，目录树的每个结点的标识等于实现此接口的相应对象的标识。<br/>
 * 一个结点最重要的属性是它的标识和它上级目录的标识。<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author flyxxxxx
 * @version 1.0
 */

public interface TreeNode
{

  /**
   * 得到此结点的标识
   * @return String 结点的标识
   */
  public int getId();

  /**
   * 得到父结点的标识
   * @return String 父结点的标识
   */
  public int getParentId();

}