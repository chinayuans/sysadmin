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
如果我们先让主线程挂起一段时间的话,那么会让前面的线程thread1首先占用cpu运行起来,
会导致thread1一直占用cpu,直至运行结束.从"helloo--------yuanjs"可以看出来.
当主线程的时间到了后,会将cpu抢过来,然后主线程再运行起来,生成后面的线程,再启动后面的线程运行起来,最后主线程退出,
让主线程生成的线程们继续运行下去直至结束.
*/

public class CuriousThread2 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		
		
		//强迫主线程停止运行，以便上面线程开始运行。
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		System.out.println("helloo--------yuanjs");
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
	}

}
