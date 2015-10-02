
package org.appfuse.common.util.thread.third;

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
public class QueueThreadWaitNotify implements Runnable {

	private final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {

		//		for (char i = 'A'; i < ='z'; i++) {
		//			System.out.println(i);
		//		}
		//		for (int i = 'A'; i < ='z'; i++) {
		//			System.out.println(i);
		//		}

		QueueThreadWaitNotify a = new QueueThreadWaitNotify();

		//很多个线程因为要访问lock对象阻塞，
		//排成队列，等待资源的释放。

		for (char i = 'a'; i <='z'; i++) {
			new Thread(a, String.valueOf(i)).start();
		}
		
		//先将main主线程休眠一段时间
		Thread.sleep(5000);
		
		//开启对阻塞队列的唤醒。
		new Thread(a, "WakeUp").start();

	}

	public void run() {
		String name = Thread.currentThread().getName();

		if (name.equals("WakeUp")) {
			synchronized (lock) {
				System.out.println("WakeUp get object");
				lock.notifyAll();
				System.out.println("WakeUp give up object");
			}

		} else {
			synchronized (lock) {
				try {
					System.out.println(name + " give up object");
					lock.wait();
					System.out.println(name + " get lock object again");

				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
		}
	}
}