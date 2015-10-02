package org.appfuse.common.util.thread.sixth;

class Resource
{
 private String name = "unknown";
 private String sex = "unknown";
 boolean bfull = false;

 public synchronized void put(String name,String sex)       // �����߷����ݵķ���,��������Ϊͬ������
 {
  if(bfull)  
  try
  {
   wait();      // ��������Ѿ�������ʱ���������̵߳ȴ������������ݸ���
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }  
  this.name = name;
  this.sex = sex;
  bfull = true;
  notify();      // �����߷������ݺ󣬻������ߵ��������̼߳���ȡ����

 }

 public synchronized void get()                                  // ������ȡ���ݵķ�����ͬ������
 {
  if(!bfull)
  try
  {
   wait();      // �����û�����ݾ�ʱ���������̵߳ȴ��������������ظ�����
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }
  System.out.print(name);
  System.out.println(":"+sex);
  bfull = false;
  notify();      // ������ȡ�����ݺ󣬻������ߵ��������߳��򻺳��Ͷ����
 }
};

