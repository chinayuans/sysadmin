
package org.appfuse.web.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * 目录树. <br/>
 * 提供由其它对象生成目录树的支持。<br/>
 * 如磁盘目录:Tree t=Tree.getTree(new java.io.File("D:/"),new FileContainer());<br/>
 * XML文件：Document doc=...<br/>
 * Tree t=Tree.getTree(doc,new DocumentContainer());<br/>
 * 如果有File或Document以外的对象要生成目录树，请实现{@link TreeNode}或{@link Container}接口。
 * 对于SQL中的表，建议字段要求id（标识）、parentId（上级目录标识），其它字段自定，然后对查询到的每一
 * 条记录生成一个实现TreeNode接口的对象。
 * 如果实现了TreeNode接口：<br/>
 *   class Group implements TreeNode{}<br/>
 *   Group[] groups=...<br/>
 *   Tree t=Tree.getTree(groups,0);//其中0为根结点的ID<br/>，并且groups不要求是同一对象，只要它们都实现了
 * 接口TreeNode，并且ID不同，这样可以在生成目录树的过程中根据对象类型自行处理。
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0
 */
final public class Tree
	extends Node
{
  /**
   * 结点最大标识
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
   * 创建空目录树.
   * @return Tree 空目录树
   */
  public static Tree getTree()
  {
	return new Tree();
  }

  /**
   * 向目录树添加一个结点.
   * 目录树中所有结点的类型最好是相同的。
   * @param parent Node 父结点
   * @param value Object 结点的值
   * @return Node 添加的结点
   */
  public Node addNode(Node parent, Object value)
  {
	Node rs = new Node(getMaxId(), parent);
	rs.setValue(value);
	return rs;
  }

  /**
   * 创建目录树.
   * 如果对象实现了接口{@link Container}，可以通过此方法加入目录树。<br/>
   * 通过此方法创建的目录树，所有结点的ID由系统生成。obj将直接做为根结点，对根结点调用方法
   * {@link Node#getValue()}将得到obj。<br/>
   * @param obj Object 目录树的根结点
   * @param container Container 得到对象的子对象的接口
   * @return Tree 目录树
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
   * 创建目录树.
   * 如果对象实现了接口{@link Container}，可以通过此方法加入目录树。<br/>
   * 通过此方法创建的目录树，所有结点的ID由系统生成，对根结点调用方法{@link Node#getValue()}
   * 将得到null。<br/>
   * obj数组中的每一个，将直接做为根结点的直接子结点。
   * @param obj Object 目录树的根结点的直接子结点.
   * @param container Container 得到对象的子对象的接口
   * @return Tree 目录树
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
   * 创建目录树.
   * 只要一组对象实现接口{@link TreeNode}，并且每个对象的ID不同，就可以将它们加入目录树。<br/>
   * 通过此方法得到的目录树，它的根结点ID值为rootId，其它结点的值为实现接口TreeNode的对象的ID。<br/>
   * 如果treeNode中包含了根结点，根结点的值可以通过方法{@link Node#getValue()}得到，返之得到的是null。<br/>
   * treeNode可以没有顺序，但父结点的ID一定大于子结点的。
   * @param treeNode TreeNode[] 构成目录树的结点
   * @param rootId int 根结点的ID
   * @return Tree 创建目录树
   */
  public static Tree getTree(TreeNode[] treeNode, int rootId)
  {
	Tree rs = new Tree(rootId);
	ArrayList list = new ArrayList();
	for (int i = 0; i < treeNode.length; i++)
	{
	  list.add(treeNode[i]);
	}
	Collections.sort(list, new Compare()); //排序
	Node last = rs;
	for (int i = 0; i < treeNode.length; i++)
	{
	  TreeNode tnode = (TreeNode) list.get(i);
	  if (i == 0 && tnode.getId() == rootId)
	  { //是否根结点
		rs.setValue(tnode);
	  }
	  else
	  {
		Node parent = null; //寻找父结点
		if ( ( (TreeNode) last.getValue()).getId() == tnode.getParentId())
		{
		  parent = last;
		}
		else
		{
		  parent = rs.getNode(tnode.getParentId());
		}
		if (parent == null)
		{ //未找到
		  throw new NullPointerException("Node " + tnode.getParentId() +
										 " not found.");
		}
		else
		{ //找到
		  Node n = new Node(tnode.getId(), parent);
		  n.setValue(tnode);
		  last = parent;
		}
	  }
	}
	return rs;
  }

  /**
   * 从目录树中查找标识为id的结点.
   * @param id String 结点标识
   * @return Node 标识为id的结点(未找到返回null)
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
  { //查找结点
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
   * 对目录树进行排序
   * @param com Comparator 排序接口
   */
  public void sort(Comparator com)
  {
	sort(childs, com);
  }

  private void sort(ArrayList childs, Comparator com)
  { //对子结点排序
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
   * 得到满足条件的结点列表.
   * @param filter NodeFilter 结点过滤器
   * @return Iterator 结点列表(存储Node对象)
   */
  public Iterator getNodeList(NodeFilter filter)
  {
	ArrayList rs = new ArrayList();
	getNodeList(childs, filter, rs);
	return rs.iterator();
  }

  private void getNodeList(ArrayList childs, NodeFilter filter,
						   ArrayList rs)
  { //检索满足条件的结点
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
	implements Comparator //对结点按ID排序
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