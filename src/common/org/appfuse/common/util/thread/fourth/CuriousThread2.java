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
��������������̹߳���һ��ʱ��Ļ�,��ô����ǰ����߳�thread1����ռ��cpu��������,
�ᵼ��thread1һֱռ��cpu,ֱ�����н���.��"helloo--------yuanjs"���Կ�����.
�����̵߳�ʱ�䵽�˺�,�Ὣcpu������,Ȼ�����߳�����������,���ɺ�����߳�,������������߳���������,������߳��˳�,
�����߳����ɵ��߳��Ǽ���������ȥֱ������.
*/

public class CuriousThread2 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		
		
		//ǿ�����߳�ֹͣ���У��Ա������߳̿�ʼ���С�
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		System.out.println("helloo--------yuanjs");
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
	}

}
