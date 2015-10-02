
package org.appfuse.web.tree;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ŀ¼����һ�����. <br/>
 * ������Ҫ�����н���ʶ������㡢����Ŀ¼���еĲ��(�����Ϊ0)������ֵ���ӽ�㡣
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
   * ���췽��
   * @param id int ���ID
   * @param parent Node �����
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
   * �õ����ID.
   * @return int ���ID
   */
  public int getId()
  {
    return id;
  }

  /**
   * �õ������Ŀ¼���еĲ��.
   * ���и����Ϊ0���������ӽ��Ϊ1����������
   * @return int �����Ŀ¼���еĲ��
   */
  final public int getLevel()
  {
    return level;
  }

  /**
   * �õ�����ֵ.
   * Ҳ����TreeNode�ӿ������õĶ���
   * @return Object ����ֵ
   */
  final public Object getValue()
  {
    return value;
  }

  /**
   * �趨����ֵ.
   */
  final void setValue(Object value)
  {
    this.value = value;
  }

  /**
   * �õ��ӽ���б�.
   * Iterator�д洢����Node����
   * @return Iterator �ӽ���б�
   */
  final public Iterator getChilds()
  {
    return childs.iterator();
  }

  /**
   * �õ��ӽ������.
   * @return int �ӽ������
   */
  final public int getChildsNumber()
  {
    return childs.size();
  }

  /**
   * �Ƿ����ӽ��.
   * @return boolean ���ӽ�㷵��true
   */
  final public boolean hasChilds()
  {
    return childs.size() > 0;
  }

  /**
   * �õ������.
   * ������Ϊ����㣬����null
   * @return Node �����
   */
  final public Node getParent()
  {
    return parent;
  }

  /**
   * �õ���level�������.
   * @param level int �����Ĳ��(level���ڵ���0��С�ڴ˽��Ĳ��)
   * @return Node ��level�������
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
   * �õ������ͬ���������λ��.
   * @return int �����ͬ���������λ��
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
   * ����Ƿ���ͬ���������һ��.
   * @return boolean �Ƿ���true
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
   * ����Ƿ�ͬ�����ĵ�һ��.
   * @return boolean �Ƿ���true
   */
  final public boolean isFirst()
  {
    return getPosition() == 0;
  }

  /**
   * �õ�Ŀ¼������һ�����.
   * ����˽����Ŀ¼�����һ������򷵻�null
   * @return Node ��һ�����
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
   * �õ���һ��ͬ�����.
   * û����һ��ͬ����㷵��null
   * @return Node ��һ��ͬ�����
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
   * �õ�ǰһ��ͬ�����.
   * û��ǰһ��ͬ����㷵��null
   * @return Node ǰһ��ͬ�����
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
   * �õ�ǰһ�����.
   * ������ǰһ�����Ϊnull
   * @return Node ǰһ�����
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