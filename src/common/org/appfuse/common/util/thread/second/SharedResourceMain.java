/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : SharedResourceMain.java
 * class   name : SharedResourceMain
 * Created on 2005-10-23 21:02:42
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.second;

//经典

/**
 * 
 * 线程之间的通知:
 * 
 * 这里使用“通知”这个词，而不用“通信”这个词，是为了避免词义的扩大化线程之间的通知， 通过Object对象的wait()和notify()
 * 或notifyAll() 方法实现。 下面用一个例子，来说明其工作原理： 假设有两个线程，A和B。共同拥有一个同步对象，lock。
 * 1．首先，线程A通过synchronized(lock)获得lock同步对象，然后调用lock.wait()函数，放弃lock同步对象，
 * 线程A停止运行，进入等待队列。
 * 
 * 2．线程B通过synchronized(lock) 获得线程A放弃的lock同步对象，做完一定的处理，然后调用 lock.notify()
 * 或者lock.notifyAll() 通知等待队列里面的线程A。
 * 
 * 3．线程A从等待队列里面出来，进入ready队列，等待调度。
 * 
 * 4．线程B继续处理，出了synchronized(lock)块之后，放弃lock同步对象。
 * 
 * 5．线程A获得lock同步对象，继续运行。
 * 
 */
public class SharedResourceMain {

	public static void main(String[] args) {
		Runnable resource = new SharedResource();

		Thread A = new Thread(resource, "A");
		A.start();

		// 强迫主线程停止运行，以便线程A开始运行。
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		Thread B = new Thread(resource, "B");
		B.start();

	}

}
