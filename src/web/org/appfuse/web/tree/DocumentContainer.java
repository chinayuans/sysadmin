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
 * XML�ĵ�����. <br/>
 * ��һ��XML�ļ�ת��ΪĿ¼������Ŀ¼���е�ÿ����㽫��ӦXML�ļ��е�һ��Element��Ҳ����Document
 * ����ӦĿ¼���ĸ���㣬�������ơ�<br/>
 * Ŀ¼����ÿ������ֵ({@link Node#getValue()}��ΪElement������ID��ϵͳ�����������Ϊ0��<br/>
 * ʹ�÷�����Tree t=new Tree(Document document,new DocumentContainer());<br/>
 * ������������documentΪXML�ĵ�Ԫ�ء�<br/>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p> </p>
 * @author flyxxxxx
 * @version 1.0
 */
final public class DocumentContainer
    implements Container
{
  /**
   * XML�ĵ��������췽��
   */
  public DocumentContainer()
  {
  }

  /**
   * �õ�������������Ӷ���.
   * @param obj Object ������(����Element)
   * @return Object[] �Ӷ����б�(����Element[])
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



