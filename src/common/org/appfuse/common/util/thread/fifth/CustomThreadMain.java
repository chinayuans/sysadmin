/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : CustomThreadMain.java
 * class   name : CustomThreadMain
 * Created on 2005-10-9 14:29:39
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.fifth;


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
 * 2.������ü̳�Thread��ķ�������ֻ��ʹ��static��̬��Ա�ˡ�
 * �����������ݱȽ϶࣬����Ҫ������static��̬��Ա����������ݽṹ���ң�
 * ������չ���������Ӧ�þ������⡣
 */
public class CustomThreadMain {

	
	public static void main(String[] args) {
		
		
		CustomThread.setStatic_data(100);
		
		//��������ʵ����ÿ��ʵ���ֲ���һ���̣߳�����ʹ������������
		CustomThread s1 = new CustomThread();//ʵ��s1
		CustomThread s2 = new CustomThread();//ʵ��s2
		CustomThread s3 = new CustomThread();//ʵ��s3
		
		new Thread(s1,"t1").start();	//ʵ��s1���߳�1	
		s1.setPrivate_data(1);
		
		new Thread(s2,"t2").start();	//ʵ��s2���߳�2
		s2.setPrivate_data(2);
		
		new Thread(s3,"t3").start();    //ʵ��s3���߳�3
		s3.setPrivate_data(3);
		
		//ע�⣺�߳�1��2��3�ڲ�ͬ��ʵ�������У�
		//ʵ��������Դ�������г�ͻ����,��Ϊ�ǲ�ͬ��ʵ���С�
		//���Ǿ�̬������Դ�����г�ͻ���⣬��Ϊ����ʵ����ͬ
		//ʹ�þ�̬������Դ��
		//���Կ��������run������ע����  synchronized(lock) �Ļ���
		//��̬��Դstatic_data ������103��ֵ�ĳ��֣�����Ϊsynchronized(lock)
		//�е�{}��δ����������̵߳Ľ������static_data���ݡ�
		//�������synchronized(lock)������Ļ������Է�ֹ��{}��
		//�ж���̵߳�ͬʱ���ʡ�
		
		
		
		//ע�⣺������߳�4��5����һ����ͬ��ʵ��s4�����У�
		//���ʵ������ʵ����������̬��������Դ�Ļ����ᵼ����Դ��ͻ��
		//�����̹߳���һ������:instance_data
		CustomThread s4 = new CustomThread();
		s4.setInstance_data(100);
		
		
		Thread s4_t1=new Thread(s4,"s4_t1");//ʵ��s4���߳�4	
		s4_t1.start();
		
						
		Thread s4_t2=new Thread(s4,"s4_t2");//ʵ��s4���߳�5	
		s4_t2.setName("r4_2");
		s4_t2.start();
		
		
	}

}
