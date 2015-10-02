
package org.appfuse.web.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Ŀ¼��. <br/>
 * �ṩ��������������Ŀ¼����֧�֡�<br/>
 * �����Ŀ¼:Tree t=Tree.getTree(new java.io.File("D:/"),new FileContainer());<br/>
 * XML�ļ���Document doc=...<br/>
 * Tree t=Tree.getTree(doc,new DocumentContainer());<br/>
 * �����File��Document����Ķ���Ҫ����Ŀ¼������ʵ��{@link TreeNode}��{@link Container}�ӿڡ�
 * ����SQL�еı������ֶ�Ҫ��id����ʶ����parentId���ϼ�Ŀ¼��ʶ���������ֶ��Զ���Ȼ��Բ�ѯ����ÿһ
 * ����¼����һ��ʵ��TreeNode�ӿڵĶ���
 * ���ʵ����TreeNode�ӿڣ�<br/>
 *   class Group implements TreeNode{}<br/>
 *   Group[] groups=...<br/>
 *   Tree t=Tree.getTree(groups,0);//����0Ϊ������ID<br/>������groups��Ҫ����ͬһ����ֻҪ���Ƕ�ʵ����
 * �ӿ�TreeNode������ID��ͬ����������������Ŀ¼���Ĺ����и��ݶ����������д���
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0
 */
final public class Tree
	extends Node
{
  /**
   * �������ʶ
   */
  private static int maxId = 0;

  private Tree()
  {
	super(getMaxId(), null);
  }

  private Tree(int id)
  {
	super(id, null);
	maxId = id++;
  }

  private static int getMaxId()
  {
	return maxId++;
  }

  /**
   * ������Ŀ¼��.
   * @return Tree ��Ŀ¼��
   */
  public static Tree getTree()
  {
	return new Tree();
  }

  /**
   * ��Ŀ¼�����һ�����.
   * Ŀ¼�������н��������������ͬ�ġ�
   * @param parent Node �����
   * @param value Object ����ֵ
   * @return Node ��ӵĽ��
   */
  public Node addNode(Node parent, Object value)
  {
	Node rs = new Node(getMaxId(), parent);
	rs.setValue(value);
	return rs;
  }

  /**
   * ����Ŀ¼��.
   * �������ʵ���˽ӿ�{@link Container}������ͨ���˷�������Ŀ¼����<br/>
   * ͨ���˷���������Ŀ¼�������н���ID��ϵͳ���ɡ�obj��ֱ����Ϊ����㣬�Ը������÷���
   * {@link Node#getValue()}���õ�obj��<br/>
   * @param obj Object Ŀ¼���ĸ����
   * @param container Container �õ�������Ӷ���Ľӿ�
   * @return Tree Ŀ¼��
   */
  public static Tree getTree(Object obj, Container container)
  {
	Tree rs = new Tree();
	rs.setValue(obj);
	Object[] o = container.getChilds(obj);
	for (int i = 0; i < o.length; i++)
	{
	  addNode(rs, o[i], container);
	}
	return rs;
  }

  private static void addNode(Node n, Object obj, Container container)
  {
	Node node = new Node(getMaxId(), n);
	node.setValue(obj);
	Object[] o = container.getChilds(obj);
	for (int i = 0; i < o.length; i++)
	{
	  addNode(node, o[i], container);
	}
  }

  /**
   * ����Ŀ¼��.
   * �������ʵ���˽ӿ�{@link Container}������ͨ���˷�������Ŀ¼����<br/>
   * ͨ���˷���������Ŀ¼�������н���ID��ϵͳ���ɣ��Ը������÷���{@link Node#getValue()}
   * ���õ�null��<br/>
   * obj�����е�ÿһ������ֱ����Ϊ������ֱ���ӽ�㡣
   * @param obj Object Ŀ¼���ĸ�����ֱ���ӽ��.
   * @param container Container �õ�������Ӷ���Ľӿ�
   * @return Tree Ŀ¼��
   */
  public static Tree getTree(Object obj[], Container container)
  {
	Tree rs = new Tree();
	for (int i = 0; i < obj.length; i++)
	{
	  addNode(rs, obj[i], container);
	}
	return rs;
  }

  /**
   * ����Ŀ¼��.
   * ֻҪһ�����ʵ�ֽӿ�{@link TreeNode}������ÿ�������ID��ͬ���Ϳ��Խ����Ǽ���Ŀ¼����<br/>
   * ͨ���˷����õ���Ŀ¼�������ĸ����IDֵΪrootId����������ֵΪʵ�ֽӿ�TreeNode�Ķ����ID��<br/>
   * ���treeNode�а����˸���㣬������ֵ����ͨ������{@link Node#getValue()}�õ�����֮�õ�����null��<br/>
   * treeNode����û��˳�򣬵�������IDһ�������ӽ��ġ�
   * @param treeNode TreeNode[] ����Ŀ¼���Ľ��
   * @param rootId int ������ID
   * @return Tree ����Ŀ¼��
   */
  public static Tree getTree(TreeNode[] treeNode, int rootId)
  {
	Tree rs = new Tree(rootId);
	ArrayList list = new ArrayList();
	for (int i = 0; i < treeNode.length; i++)
	{
	  list.add(treeNode[i]);
	}
	Collections.sort(list, new Compare()); //����
	Node last = rs;
	for (int i = 0; i < treeNode.length; i++)
	{
	  TreeNode tnode = (TreeNode) list.get(i);
	  if (i == 0 && tnode.getId() == rootId)
	  { //�Ƿ�����
		rs.setValue(tnode);
	  }
	  else
	  {
		Node parent = null; //Ѱ�Ҹ����
		if ( ( (TreeNode) last.getValue()).getId() == tnode.getParentId())
		{
		  parent = last;
		}
		else
		{
		  parent = rs.getNode(tnode.getParentId());
		}
		if (parent == null)
		{ //δ�ҵ�
		  throw new NullPointerException("Node " + tnode.getParentId() +
										 " not found.");
		}
		else
		{ //�ҵ�
		  Node n = new Node(tnode.getId(), parent);
		  n.setValue(tnode);
		  last = parent;
		}
	  }
	}
	return rs;
  }

  /**
   * ��Ŀ¼���в��ұ�ʶΪid�Ľ��.
   * @param id String ����ʶ
   * @return Node ��ʶΪid�Ľ��(δ�ҵ�����null)
   */
  public Node getNode(int id)
  {
	if (id == getId())
	{
	  return this;
	}
	return getNode(getChilds(), id);
  }

  private static Node getNode(Iterator it, int id)
  { //���ҽ��
	while (it.hasNext())
	{
	  Node n = (Node) it.next();
	  if (n.getId() == id)
	  {
		return n;
	  }
	  if (n.getChildsNumber() > 0)
	  {
		n = getNode(n.getChilds(), id);
		if (n != null)
		{
		  return n;
		}
	  }
	}
	return null;
  }

  /**
   * ��Ŀ¼����������
   * @param com Comparator ����ӿ�
   */
  public void sort(Comparator com)
  {
	sort(childs, com);
  }

  private void sort(ArrayList childs, Comparator com)
  { //���ӽ������
	Collections.sort(childs, com);
	for (int i = 0; i < childs.size(); i++)
	{
	  Node n = (Node) childs.get(i);
	  if (n.getChildsNumber() > 1)
	  {
		sort(n.childs, com);
	  }
	}
  }

  /**
   * �õ����������Ľ���б�.
   * @param filter NodeFilter ��������
   * @return Iterator ����б�(�洢Node����)
   */
  public Iterator getNodeList(NodeFilter filter)
  {
	ArrayList rs = new ArrayList();
	getNodeList(childs, filter, rs);
	return rs.iterator();
  }

  private void getNodeList(ArrayList childs, NodeFilter filter,
						   ArrayList rs)
  { //�������������Ľ��
	for (int i = 0; i < childs.size(); i++)
	{
	  Node n = (Node) childs.get(i);
	  if (filter.accept(n))
	  {
		rs.add(n);
	  }
	  if (n.hasChilds())
	  {
		getNodeList(n.childs, filter, rs);
	  }
	}
  }

}

class Compare
	implements Comparator //�Խ�㰴ID����
{
  public Compare()
  {}

  public int compare(Object obj1, Object obj2)
  {
	int id1 = ( (TreeNode) obj1).getId();
	int id2 = ( (TreeNode) obj2).getId();
	return id1 - id2;
  }
}