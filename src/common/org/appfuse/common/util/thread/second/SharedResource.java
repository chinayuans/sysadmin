/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : SharedResource.java
 * class   name : SharedResource
 * Created on 2005-10-23 20:59:40
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.second;

/**
 * 实现线程，有两种方法，一种是继承Thread类，一种是实现Runnable接口。
 * 本文推荐实现Runnable接口的方法。
 * 1.把需要共享的数据(可以是静态的，非静态的变量了)放在一个实现Runnable
 * 接口的类里面，然后把这个类的实例传给多个Thread的构造方法。这样，新创建
 * 的多个Thread，都共同拥有一个Runnable实例，共享同一份数据。
 * 2.如果采用继承Thread类的方法，就只好使用static静态成员了。
 * 如果共享的数据比较多，就需要大量的static静态成员，令程序数据结构混乱，
 * 难以扩展。这种情况应该尽量避免。
 */

/**
 * 
 */
public class SharedResource implements Runnable {


    //lock对象 代表线程A和B要争强的资源，也就是lock是需要同步的对象
	private Object lock = new Object();

	public void run() {
		// 获取当前线程的名称。
		String threadName = Thread.currentThread().getName();

		if ("A".equals(threadName)) {
			synchronized (lock) { // 线程A通过synchronized(lock) 获得lock同步对象
				try {
					System.out.println("A gives up lock.");
					lock.wait(); // 调用lock.wait()函数，放弃lock同步对象，线程A停止运行，进入等待队列。
				} catch (InterruptedException e) {

				}
				// 线程A重新获得lock同步对象之后，继续运行。
				System.out.println("A got lock again and continue to run.");
			} // end of synchronized(lock)
		}
		if ("B".equals(threadName)) {
			synchronized (lock) {// 线程B通过synchronized(lock) 获得线程A放弃的lock同步对象
				System.out.println("B got lock.");
				lock.notify(); // 通知等待队列里面的线程A，进入ready队列，等待调度。
				// 线程B继续处理，出了synchronized(lock)块之后，放弃lock同步对象。
				System.out.println("B gives up lock.");
			} // end of synchronized(lock)
			boolean hasLock = Thread.holdsLock(lock); // 检查B是否拥有lock同步对象。
			System.out.println("B has lock ? -- " + hasLock); // false.
		}

	}
}
