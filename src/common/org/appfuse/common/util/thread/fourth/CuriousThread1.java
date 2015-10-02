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
Ҳ������Ҫ�ﵽ��Ч���ǣ������ӡ"this is Thread one!"��"this is Thread two!";
������ʵ��ȴ���Ǵ�ӡ"this is Thread two!";
Ϊʲô���������������أ���Ϊ���߳�����һ��ʱ��Ƭ��
���һ��ʱ��Ƭ�㹻������ô��ִ���� new Thread(r).start();// 
Thread one�������߳�(main--Demo)���������У�Thread one �����ȵȴ���
Ҳ����˵����̲߳�û�����У���ִ���� r.cache=false;
�Լ������ new Thread(r).start();//
Thread two�����߳�(main--Demo)��������ʱ��ȴ��Ѿõ�Thread one����������
�������ʱ����������r.cache������������Ҫ��true,
���Ѿ������߳��б��޸ĳ���false,
����Thread one ��Thread two ��ֻ���ӡ"this is Thread two!".
*/

public class CuriousThread1 {

	public static void main(String[] args) {
		CuriousRunnable r = new CuriousRunnable();
		new Thread(r,"thread1").start();// Thread one
		
		r.cache = false;
		new Thread(r,"thread2").start();// Thread two
	}

}
