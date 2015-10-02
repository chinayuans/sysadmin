package org.appfuse.common.util.thread.sixth;

class Main
{
 public static void main (String [] args)
 {
  Resource t = new Resource();
  new Thread (new Producer(t)).start();
  new Thread (new Consumer(t)).start();
 }
};

