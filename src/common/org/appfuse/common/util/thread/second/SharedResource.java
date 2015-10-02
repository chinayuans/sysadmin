/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : SharedResource.java
 * class   name : SharedResource
 * Created on 2005-10-23 20:59:40
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.second;

/**
 * ʵ���̣߳������ַ�����һ���Ǽ̳�Thread�࣬һ����ʵ��Runnable�ӿڡ�
 * �����Ƽ�ʵ��Runnable�ӿڵķ�����
 * 1.����Ҫ���������(�����Ǿ�̬�ģ��Ǿ�̬�ı�����)����һ��ʵ��Runnable
 * �ӿڵ������棬Ȼ���������ʵ���������Thread�Ĺ��췽�����������´���
 * �Ķ��Thread������ͬӵ��һ��Runnableʵ��������ͬһ�����ݡ�
 * 2.������ü̳�Thread��ķ�������ֻ��ʹ��static��̬��Ա�ˡ�
 * �����������ݱȽ϶࣬����Ҫ������static��̬��Ա����������ݽṹ���ң�
 * ������չ���������Ӧ�þ������⡣
 */

/**
 * 
 */
public class SharedResource implements Runnable {


    //lock���� �����߳�A��BҪ��ǿ����Դ��Ҳ����lock����Ҫͬ���Ķ���
	private Object lock = new Object();

	public void run() {
		// ��ȡ��ǰ�̵߳����ơ�
		String threadName = Thread.currentThread().getName();

		if ("A".equals(threadName)) {
			synchronized (lock) { // �߳�Aͨ��synchronized(lock) ���lockͬ������
				try {
					System.out.println("A gives up lock.");
					lock.wait(); // ����lock.wait()����������lockͬ�������߳�Aֹͣ���У�����ȴ����С�
				} catch (InterruptedException e) {

				}
				// �߳�A���»��lockͬ������֮�󣬼������С�
				System.out.println("A got lock again and continue to run.");
			} // end of synchronized(lock)
		}
		if ("B".equals(threadName)) {
			synchronized (lock) {// �߳�Bͨ��synchronized(lock) ����߳�A������lockͬ������
				System.out.println("B got lock.");
				lock.notify(); // ֪ͨ�ȴ�����������߳�A������ready���У��ȴ����ȡ�
				// �߳�B������������synchronized(lock)��֮�󣬷���lockͬ������
				System.out.println("B gives up lock.");
			} // end of synchronized(lock)
			boolean hasLock = Thread.holdsLock(lock); // ���B�Ƿ�ӵ��lockͬ������
			System.out.println("B has lock ? -- " + hasLock); // false.
		}

	}
}
