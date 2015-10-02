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
  while(true)                                 // 这里假设数据有两个，下一段程序用于实现在把这两个数据循环放入缓冲池
  {
   if(i==0)
    res.put("zhangsan","male");
    
   else
    res.put("lisi","female");
   i=(i+1)%2;
  }
 }
};

