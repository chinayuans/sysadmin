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
���߳����Ƚ�thread1��thread2��������,Ȼ����ʹ���Լ�����,��cpuʱ���ø�thread1,thread2.
��ʱthread1 ��������һ��ʱ��,Ȼ��thread2��ռcpu������һ��ʱ��,Ȼ��ֱ��������һ��ʱ��.
������߳�ʱ�䵽,��Ҳ����cpuʱ��������߳�.
*/

public class CuriousThread3 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
		
		//ǿ�����߳�ֹͣ���У��Ա������߳̿�ʼ���С�
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		System.out.println("main thread end!");
	}

}
