/*
 * Created on 2005-1-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.appfuse.web.tree;

/**
 * �����ӿ�. <br/>
 * Ҫ��һ���һ������ת��ΪĿ¼����������ʵ�ִ˽ӿڻ�{@link TreeNode}�ӿڡ�<br/>
 * �����ܹ������ӽ�㣬��ͨ������{@link #getChilds(java.lang.Object)}�õ�һ��������ӽ�㡣<br/>
 * Ŀ¼���е�ÿһ������ֵ���������������ָ��Ķ���Ҳ����˵��ͨ�����÷���
 * {@link Node#getValue()}���õ��������<br/>
 * �磺Tree t=Tree.getTree(new File("D:/"),new FileContainer());<br/>
 * ��t�е�ÿ����㣬�������÷���{@link Node#getValue()}�����õ�һ��File����<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0
 */
public interface Container
{
  /**
   * �õ�������������Ӷ���.
   * @param obj Object ������
   * @return Object[] �Ӷ����б�
   */
  public Object[] getChilds(Object obj);
}

