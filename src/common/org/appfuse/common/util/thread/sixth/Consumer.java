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
   res.get();                       //消费者从缓冲池取数据
  }
 }
};


