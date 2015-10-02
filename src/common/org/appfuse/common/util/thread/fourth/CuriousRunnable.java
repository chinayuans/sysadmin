/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : MyRunnable.java
 * class   name : MyRunnable
 * Created on 2005-10-9 16:04:27
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.fourth;

public class CuriousRunnable implements Runnable {
	
	boolean cache = true;
	
	public void run() {
		for (int i=0;i<1000;i++) {
			if (cache)
				System.out.println(Thread.currentThread().getName()+"--- this is thread one!----"+i);
			else
				System.out.println(Thread.currentThread().getName()+"--- this is thread two!----"+i);
		}

	}
}
