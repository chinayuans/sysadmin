
package org.appfuse.web.tree;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * 目录树的一个结点. <br/>
 * 它的主要属性有结点标识、父结点、它在目录树中的层次(根结点为0)、结点的值、子结点。
 * <p>Copyright: Copyright (c) 2004</p>
 * @author flyxxxxx
 * @version 1.0
 */
public class Node
{
  private int id;
  private Node parent;
  private int level;
  private Object value;
  protected ArrayList childs = new ArrayList();

  /**
   * 构造方法
   * @param id int 结点ID
   * @param parent Node 父结点
   */
  Node(int id, Node parent)
  {
    this.id = id;
    if (parent != null)
    {
      this.parent = parent;
      parent.childs.add(this);
      this.level = parent.getLevel() + 1;
    }
    else
    {
      level = 0;
    }
  }

  /**
   * 得到结点ID.
   * @return int 结点ID
   */
  public int getId()
  {
    return id;
  }

  /**
   * 得到结点在目录树中的层次.
   * 其中根结点为0，根结点的子结点为1，依次类推
   * @return int 结点在目录树中的层次
   */
  final public int getLevel()
  {
    return level;
  }

  /**
   * 得到结点的值.
   * 也就是TreeNode接口所引用的对象
   * @return Object 结点的值
   */
  final public Object getValue()
  {
    return value;
  }

  /**
   * 设定结点的值.
   */
  final void setValue(Object value)
  {
    this.value = value;
  }

  /**
   * 得到子结点列表.
   * Iterator中存储的是Node对象
   * @return Iterator 子结点列表
   */
  final public Iterator getChilds()
  {
    return childs.iterator();
  }

  /**
   * 得到子结点数量.
   * @return int 子结点数量
   */
  final public int getChildsNumber()
  {
    return childs.size();
  }

  /**
   * 是否有子结点.
   * @return boolean 有子结点返回true
   */
  final public boolean hasChilds()
  {
    return childs.size() > 0;
  }

  /**
   * 得到父结点.
   * 如果结点为根结点，返回null
   * @return Node 父结点
   */
  final public Node getParent()
  {
    return parent;
  }

  /**
   * 得到第level级父结点.
   * @param level int 父结点的层次(level大于等于0，小于此结点的层次)
   * @return Node 第level级父结点
   */
  final public Node getParent(int level)
  {
    if (level < 0 || level >= this.level)
    {
      throw new ArrayIndexOutOfBoundsException("level is error.");
    }
    Node n = parent;
    for (int i = 1; i < level; i++)
    {
      n = n.getParent();
    }
    return n;
  }

  /**
   * 得到结点在同级结点的相对位置.
   * @return int 结点在同级结点的相对位置
   */
  final public int getPosition()
  {
    if (parent == null)
    {
      return 0;
    }
    return parent.childs.indexOf(this);
  }

  /**
   * 结点是否是同级结点的最后一个.
   * @return boolean 是返回true
   */
  final public boolean isLast()
  {
    if (parent == null)
    {
      return true;
    }
    return getPosition() == parent.childs.size() - 1;
  }

  /**
   * 结点是否同级结点的第一个.
   * @return boolean 是返回true
   */
  final public boolean isFirst()
  {
    return getPosition() == 0;
  }

  /**
   * 得到目录树中下一个结点.
   * 如果此结点是目录树最后一个结点则返回null
   * @return Node 下一个结点
   */
  final public Node getNext()
  {
    if (childs.size() > 0)
    {
      return (Node) childs.get(0);
    }
    Node n = parent;
    while (n != null)
    {
      Node node = n.getNextSibling();
      if (node != null)
      {
        return node;
      }
      n = n.getParent();
    }
    return null;
  }

  /**
   * 得到下一个同级结点.
   * 没有下一个同级结点返回null
   * @return Node 下一个同级结点
   */
  final public Node getNextSibling()
  {
    if (parent == null)
    {
      return null;
    }
    int k = getPosition();
    if (k == parent.getChildsNumber() - 1)
    {
      return null;
    }
    return (Node) parent.childs.get(k + 1);
  }

  /**
   * 得到前一个同级结点.
   * 没有前一个同级结点返回null
   * @return Node 前一个同级结点
   */
  final public Node getPreviousSibling()
  {
    int k = getPosition();
    if (k == 0)
    {
      return null;
    }
    return (Node) parent.childs.get(k - 1);
  }

  /**
   * 得到前一个结点.
   * 根结点的前一个结点为null
   * @return Node 前一个结点
   */
  final public Node getPrevious()
  {
    Node n = getPreviousSibling();
    if (n != null)
    {
      return n;
    }
    return parent;
  }

}