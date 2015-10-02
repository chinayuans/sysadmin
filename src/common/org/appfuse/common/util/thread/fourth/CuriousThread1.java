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
也许我们要达到的效果是：交错打印"this is Thread one!"和"this is Thread two!";
但是事实上却总是打印"this is Thread two!";
为什么会出现上面的现象呢？因为主线程享有一个时间片，
如果一个时间片足够长，那么当执行了 new Thread(r).start();// 
Thread one这句后，主线程(main--Demo)继续在运行，Thread one 将被迫等待，
也就是说这个线程并没有运行；当执行了 r.cache=false;
以及后面的 new Thread(r).start();//
Thread two后，主线程(main--Demo)结束，这时候等待已久的Thread one运行起来，
可是这个时候它看见的r.cache并不是我们想要的true,
而已经在主线程中被修改成了false,
所以Thread one 和Thread two 都只会打印"this is Thread two!".
*/

public class CuriousThread1 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
	}

}
