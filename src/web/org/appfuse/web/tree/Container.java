/*
 * Created on 2005-1-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.appfuse.web.tree;

/**
 * 容器接口. <br/>
 * 要将一组或一个对象转化为目录树，它必须实现此接口或{@link TreeNode}接口。<br/>
 * 容器能够包含子结点，它通过方法{@link #getChilds(java.lang.Object)}得到一个对象的子结点。<br/>
 * 目录树中的每一个结点的值将引用这个容器所指向的对象，也就是说：通过调用方法
 * {@link Node#getValue()}将得到这个对象。<br/>
 * 如：Tree t=Tree.getTree(new File("D:/"),new FileContainer());<br/>
 * 在t中的每个结点，对它调用方法{@link Node#getValue()}都将得到一个File对象。<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0
 */
public interface Container
{
  /**
   * 得到将对象的所有子对象.
   * @param obj Object 父对象
   * @return Object[] 子对象列表
   */
  public Object[] getChilds(Object obj);
}

