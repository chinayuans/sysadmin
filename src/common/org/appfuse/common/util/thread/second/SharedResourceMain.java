/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : SharedResourceMain.java
 * class   name : SharedResourceMain
 * Created on 2005-10-23 21:02:42
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.second;

//����

/**
 * 
 * �߳�֮���֪ͨ:
 * 
 * ����ʹ�á�֪ͨ������ʣ������á�ͨ�š�����ʣ���Ϊ�˱������������߳�֮���֪ͨ�� ͨ��Object�����wait()��notify()
 * ��notifyAll() ����ʵ�֡� ������һ�����ӣ���˵���乤��ԭ�� �����������̣߳�A��B����ͬӵ��һ��ͬ������lock��
 * 1�����ȣ��߳�Aͨ��synchronized(lock)���lockͬ������Ȼ�����lock.wait()����������lockͬ������
 * �߳�Aֹͣ���У�����ȴ����С�
 * 
 * 2���߳�Bͨ��synchronized(lock) ����߳�A������lockͬ����������һ���Ĵ���Ȼ����� lock.notify()
 * ����lock.notifyAll() ֪ͨ�ȴ�����������߳�A��
 * 
 * 3���߳�A�ӵȴ������������������ready���У��ȴ����ȡ�
 * 
 * 4���߳�B������������synchronized(lock)��֮�󣬷���lockͬ������
 * 
 * 5���߳�A���lockͬ�����󣬼������С�
 * 
 */
public class SharedResourceMain {

	public static void main(String[] args) {
		Runnable resource = new SharedResource();

		Thread A = new Thread(resource, "A");
		A.start();

		// ǿ�����߳�ֹͣ���У��Ա��߳�A��ʼ���С�
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		Thread B = new Thread(resource, "B");
		B.start();

	}

}
