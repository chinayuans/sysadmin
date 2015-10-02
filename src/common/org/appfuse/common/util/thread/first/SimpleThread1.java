package org.appfuse.common.util.thread.first;

/**
 * ʵ���̣߳������ַ�����һ���Ǽ̳�Thread�࣬һ����ʵ��Runnable�ӿڡ�
 * �����Ƽ�ʵ��Runnable�ӿڵķ�����
 * 1.����Ҫ���������(�����Ǿ�̬�ģ��Ǿ�̬�ı�����)����һ��ʵ��Runnable
 * �ӿڵ������棬Ȼ���������ʵ���������Thread�Ĺ��췽����Ҳ����
 * ����CustomerThread extends Runnable, Ȼ��CustomerThread custom= new CustomerThread(),
 * ��Thread thread1=new Thread(custom,"thread1") ;
 *  Thread thread2=new Thread(custom,"thread2") �������´���
 * �Ķ��Thread(thread1,thread2)������ͬӵ��һ��Runnableʵ��������ͬһ�����ݡ�
 * 
 * �������Ǿ�̬�ı�����ɹ�����Դ�Ļ�,��Ӧ���������:
 * CustomerThread extends Runnable;
 * CustomerThread custom1= new CustomerThread();
 * CustomerThread custom2= new CustomerThread();
 * Thread thread1=new Thread(custom1,"thread1");
 * Thread thread2=new Thread(custom2,"thread2");
 * ��ô��ʱthread1��thread2��ֻ�ܹ���CustomerThread�еľ�̬������;
 * 
 * 2.������ü̳�Thread��ķ�������ֻ��ʹ��static��̬��Ա�ˡ�
 * �����������ݱȽ϶࣬����Ҫ������static��̬��Ա����������ݽṹ���ң�
 * ������չ���������Ӧ�þ������⡣
 */
public class SimpleThread1 extends Thread {

	private int countDown=1000;
	private static int threadCount=0;
	private int threadNumber=++threadCount;
	SimpleThread1(){
		System.out.println("thread number:"+threadNumber);
	}
	
	//ע��while(true) {...} �м�Ĵ�����ʱ���Դ��,Ȼ�������������߳�,
	//����һ��ԭ�Ӳ���
	public void run() {
		
		while(true){
			System.out.println("thread number:"+threadNumber+" countDown:"+countDown+" thread name:"+getName());
			System.out.println("------");
			if (--countDown==0) return;
			
		}
	}

	
	//ע��Thread.sleep(1000);��ǿ�����߳�ֹͣ����.
	//1.���û����δ���Ļ�,���߳̽����������н���.
	//�����Ĳ������߳���Ȼ��������,������Ϊ���̵߳Ľ����������̲߳����������߳�Ҳ����.
	//2.������δ���,��ʹ�����߳��ȹ���һ��ʱ��,�ȴ����̲߳����������߳�����,�ܿ��������߳���������,���߳����Ž���.
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 5; i++) {
			new SimpleThread1().start();			
		}
		Thread.sleep(1000000);
		System.out.println("the main thread has ended.");
	}
}