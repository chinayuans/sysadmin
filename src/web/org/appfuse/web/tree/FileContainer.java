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
 * �ļ�����. <br/>
 * ��һ������Ŀ¼ת��ΪĿ¼������Ŀ¼���е�ÿ����㽫��Ӧ�����е�һ��Ŀ¼���ļ���<br/>
 * Ŀ¼����ÿ������ֵ({@link Node#getValue()}��ΪFile������ID��ϵͳ�����������Ϊ0��<br/>
 * ʹ�÷�����Tree t=new Tree(File f,new FileContainer());<br/>
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
   * Ĭ���������췽��
   */
  public FileContainer()
  {
  }

  /**
   * ���ļ��������Ĺ��췽��.
   * ͨ���˷�������Ŀ¼���в����в�����������Ŀ¼���ļ�
   * @param filter FileFilter �ļ�������
   */
  public FileContainer(FileFilter filter)
  {
    this.filter = filter;
  }

  /**
   * �õ�������������Ӷ���.
   * @param obj Object ������(����File)
   * @return Object[] �Ӷ����б�(����File[])
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