package org.appfuse.common.util.thread.sixth;

class Resource
{
 private String name = "unknown";
 private String sex = "unknown";
 boolean bfull = false;

 public synchronized void put(String name,String sex)       // 生产者放数据的方法,这里设置为同步方法
 {
  if(bfull)  
  try
  {
   wait();      // 当缓冲池已经有数据时，生产者线程等待，避免了数据覆盖
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }  
  this.name = name;
  this.sex = sex;
  bfull = true;
  notify();      // 生产者放入数据后，唤醒休眠的消费者线程继续取数据

 }

 public synchronized void get()                                  // 消费者取数据的方法，同步方法
 {
  if(!bfull)
  try
  {
   wait();      // 缓冲池没有数据据时，消费者线程等待，避免了数据重复读出
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }
  System.out.print(name);
  System.out.println(":"+sex);
  bfull = false;
  notify();      // 消费者取完数据后，唤醒休眠的生产者线程向缓冲池投数据
 }
};

