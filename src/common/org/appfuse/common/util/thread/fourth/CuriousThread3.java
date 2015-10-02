/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : Demo.java
 * class   name : Demo
 * Created on 2005-10-9 15:54:45
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.fourth;

/**
主线程首先将thread1和thread2运行起来,然后再使得自己挂起,将cpu时间让给thread1,thread2.
此时thread1 会先运行一点时间,然后thread2抢占cpu再运行一点时间,然后分别各自运行一段时间.
最后当主线程时间到,它也会抢cpu时间结束主线程.
*/

public class CuriousThread3 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
		
		//强迫主线程停止运行，以便上面线程开始运行。
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		System.out.println("main thread end!");
	}

}
