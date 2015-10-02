/*
 * Created on 2005-1-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.appfuse.web.tree;

  
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * XML文档容器. <br/>
 * 将一个XML文件转化为目录树，此目录树中的每个结点将对应XML文件中的一个Element，也就是Document
 * 结点对应目录树的根结点，依次类推。<br/>
 * 目录树的每个结点的值({@link Node#getValue()}均为Element，结点的ID由系统产生，根结点为0。<br/>
 * 使用方法：Tree t=new Tree(Document document,new DocumentContainer());<br/>
 * 　　　　其中document为XML文档元素。<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0
 */
final public class DocumentContainer
    implements Container
{
  /**
   * XML文档容器构造方法
   */
  public DocumentContainer()
  {
  }

  /**
   * 得到将对象的所有子对象.
   * @param obj Object 父对象(类型Element)
   * @return Object[] 子对象列表(类型Element[])
   */
  public Object[] getChilds(Object obj)
  {
    if (obj instanceof Element)
    {
      ArrayList rs = new ArrayList();
      NodeList list = ( (Element) obj).getChildNodes();
      for (int i = 0; i < list.getLength(); i++)
      {
        Node n = list.item(i);
        if (n.getNodeType() == Node.ELEMENT_NODE)
        {
          rs.add(n);
        }
      }
      return rs.toArray();
    }
    throw new IllegalArgumentException(
        "Required param type is org.w3c.dom.Element.");
  }
}



