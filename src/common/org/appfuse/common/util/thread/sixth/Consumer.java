package org.appfuse.common.util.thread.sixth;

class Consumer implements Runnable
{
 Resource res;

 public Consumer(Resource res)
 {
  this.res=res;
 }

 public void run()
 {
  while (true)
  {
   res.get();                       //�����ߴӻ����ȡ����
  }
 }
};


