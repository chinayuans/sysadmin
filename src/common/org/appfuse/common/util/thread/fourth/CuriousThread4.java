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
���߳����Ƚ�thread1��thread2��������,���Ͻ��Լ�������߳̽���,Ȼ��cpuʱ���ø�thread1,thread2ȥ��.
*/

public class CuriousThread4 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
		
		System.out.println("main thread end!");
	}

}
