
package org.appfuse.web.tree;


/**
 * �����˽ӿ�. <br/>
* �˽ӿ����ڴ�Ŀ¼���в��ҷ���һ�������Ľ�㡣<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author flyxxxxx
 * @version 1.0
 */
public interface NodeFilter
{
  /**
   * �жϽ���Ƿ�����һ������.
   * @param n Node Ҫ�жϵĽ��
   * @return boolean ������������true
   */
  public boolean accept(Node n);
}