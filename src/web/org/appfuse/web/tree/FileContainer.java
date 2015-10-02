/*
 * Created on 2005-1-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.appfuse.web.tree;


import java.io.FileFilter;
import java.io.File;

/**
 * 文件容器. <br/>
 * 将一个磁盘目录转化为目录树，此目录树中的每个结点将对应磁盘中的一个目录或文件。<br/>
 * 目录树的每个结点的值({@link Node#getValue()}均为File，结点的ID由系统产生，根结点为0。<br/>
 * 使用方法：Tree t=new Tree(File f,new FileContainer());<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0

 */
final public class FileContainer
    implements Container
{
  private FileFilter filter;

  /**
   * 默认容器构造方法
   */
  public FileContainer()
  {
  }

  /**
   * 带文件过滤器的构造方法.
   * 通过此方法，将目录树中不会有不满足条件的目录或文件
   * @param filter FileFilter 文件过滤器
   */
  public FileContainer(FileFilter filter)
  {
    this.filter = filter;
  }

  /**
   * 得到将对象的所有子对象.
   * @param obj Object 父对象(类型File)
   * @return Object[] 子对象列表(类型File[])
   */
  public Object[] getChilds(Object obj)
  {
    if (obj instanceof File)
    {
      File f = (File) obj;
      if (f.isFile())
      {
        return new Object[0];
      }
      if (filter == null)
      {
        return f.listFiles();
      }
      else
      {
        return f.listFiles(filter);
      }
    }
    throw new IllegalArgumentException("Required param type is java.io.File.");
  }

}