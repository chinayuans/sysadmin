/*
 * Created on 2005-1-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.appfuse.web.tree;

/**
 * Ŀ¼����һ�����. <br/>
 * Ҫ��һ�����ת��ΪĿ¼����������ʵ�ִ˽ӿڻ�{@link Container}�ӿڡ�<br/>
 * ͨ��ʵ�ִ˽ӿڵ�Ŀ¼����Ŀ¼����ÿ�����ı�ʶ����ʵ�ִ˽ӿڵ���Ӧ����ı�ʶ��<br/>
 * һ���������Ҫ�����������ı�ʶ�����ϼ�Ŀ¼�ı�ʶ��<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author flyxxxxx
 * @version 1.0
 */

public interface TreeNode
{

  /**
   * �õ��˽��ı�ʶ
   * @return String ���ı�ʶ
   */
  public int getId();

  /**
   * �õ������ı�ʶ
   * @return String �����ı�ʶ
   */
  public int getParentId();

}