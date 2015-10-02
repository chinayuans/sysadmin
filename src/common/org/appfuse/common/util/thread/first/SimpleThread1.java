package org.appfuse.common.util.thread.first;

/**
 * 实现线程，有两种方法，一种是继承Thread类，一种是实现Runnable接口。
 * 本文推荐实现Runnable接口的方法。
 * 1.把需要共享的数据(可以是静态的，非静态的变量了)放在一个实现Runnable
 * 接口的类里面，然后把这个类的实例传给多个Thread的构造方法。也就是
 * 首先CustomerThread extends Runnable, 然后CustomerThread custom= new CustomerThread(),
 * 再Thread thread1=new Thread(custom,"thread1") ;
 *  Thread thread2=new Thread(custom,"thread2") 这样，新创建
 * 的多个Thread(thread1,thread2)，都共同拥有一个Runnable实例，共享同一份数据。
 * 
 * 如果不想非静态的变量变成共享资源的话,就应改这样设计:
 * CustomerThread extends Runnable;
 * CustomerThread custom1= new CustomerThread();
 * CustomerThread custom2= new CustomerThread();
 * Thread thread1=new Thread(custom1,"thread1");
 * Thread thread2=new Thread(custom2,"thread2");
 * 那么此时thread1和thread2就只能共享CustomerThread中的静态变量了;
 * 
 * 2.如果采用继承Thread类的方法，就只好使用static静态成员了。
 * 如果共享的数据比较多，就需要大量的static静态成员，令程序数据结构混乱，
 * 难以扩展。这种情况应该尽量避免。
 */
public class SimpleThread1 extends Thread {

	private int countDown=1000;
	private static int threadCount=0;
	private int threadNumber=++threadCount;
	SimpleThread1(){
		System.out.println("thread number:"+threadNumber);
	}
	
	//注意while(true) {...} 中间的代码随时可以打断,然后运行其他的线程,
	//不是一个原子操作
	public void run() {
		
		while(true){
			System.out.println("thread number:"+threadNumber+" countDown:"+countDown+" thread name:"+getName());
			System.out.println("------");
			if (--countDown==0) return;
			
		}
	}

	
	//注意Thread.sleep(1000);将强迫主线程停止运行.
	//1.如果没有这段代码的话,主线程将会首先运行结束.
	//其他的产生的线程仍然继续运行,不会因为主线程的结束导致主线程产生的其他线程也结束.
	//2.有了这段代码,将使得主线程先挂起一段时间,等待主线程产生的其他线程运行,很可能其他线程运行忘了,主线程最后才结束.
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 5; i++) {
			new SimpleThread1().start();			
		}
		Thread.sleep(1000000);
		System.out.println("the main thread has ended.");
	}
}