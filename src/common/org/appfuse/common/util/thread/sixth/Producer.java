package org.appfuse.common.util.thread.sixth;

class Producer implements Runnable
{
 Resource res;

 public Producer(Resource res)
 {
  this.res=res;
 }

 public void run ()
 {
  int i=0;
  while(true)                                 // ���������������������һ�γ�������ʵ���ڰ�����������ѭ�����뻺���
  {
   if(i==0)
    res.put("zhangsan","male");
    
   else
    res.put("lisi","female");
   i=(i+1)%2;
  }
 }
};

