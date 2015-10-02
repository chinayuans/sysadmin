
package org.appfuse.common.util.thread.third;

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
public class QueueThreadWaitNotify implements Runnable {

	private final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {

		//		for (char i = 'A'; i < ='z'; i++) {
		//			System.out.println(i);
		//		}
		//		for (int i = 'A'; i < ='z'; i++) {
		//			System.out.println(i);
		//		}

		QueueThreadWaitNotify a = new QueueThreadWaitNotify();

		//�ܶ���߳���ΪҪ����lock����������
		//�ųɶ��У��ȴ���Դ���ͷš�

		for (char i = 'a'; i <='z'; i++) {
			new Thread(a, String.valueOf(i)).start();
		}
		
		//�Ƚ�main���߳�����һ��ʱ��
		Thread.sleep(5000);
		
		//�������������еĻ��ѡ�
		new Thread(a, "WakeUp").start();

	}

	public void run() {
		String name = Thread.currentThread().getName();

		if (name.equals("WakeUp")) {
			synchronized (lock) {
				System.out.println("WakeUp get object");
				lock.notifyAll();
				System.out.println("WakeUp give up object");
			}

		} else {
			synchronized (lock) {
				try {
					System.out.println(name + " give up object");
					lock.wait();
					System.out.println(name + " get lock object again");

				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
		}
	}
}