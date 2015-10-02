/*
 * project name : learnedJava
 * package name : baseknowledge1.thread
 * file    name : CustomThread.java
 * class   name : CustomThread
 * Created on 2005-10-9 14:31:56
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.common.util.thread.fifth;

public class CustomThread implements Runnable {

	//���������̵߳ı�־
	private int killThread_counter = 1000;

    //���������������ɵ�ʵ������
	private static int object_counter = 0; 
	
	//�����������ɵ�ʵ�������֡�
	private int instance_name=0;
	public CustomThread() {
		instance_name=++object_counter;
	}

	
	//������ֹ��̬���ݷ��ʳ�ͻ��һ���ֶΡ�
	private static Object static_lock = new Object();

	//������ֹ������ʵ�����ݵķ��ʳ�ͻ����һ���ֶ���synchronized(this)��
	
	
	
	//=================================================
	//=================================================

	//��׼���� ���һ��ʵ���еĶ���̹߳������Դ
	//����������ʾ��������ǿ����Դ��
	private int private_data = 0;

	//��Ķ��ʵ���������Դ
	private static int static_data;

	//���һ��ʵ���еĶ���̹߳������Դ
	private int instance_data = 0;

	//�������ǰ�����synchronized����ô�ͻ��ֹ��"ͬһ����ĺ������ʳ�ͻ"�����⡣ 
	public void run() {
//		sample1();
		sample2();
	}

	public void sample1() {
		while (true) {
			synchronized (static_lock) {
				String threadName = Thread.currentThread().getName();
				static_data++;
				System.out.println(
					" instance name:"+instance_name
					+" thread name:"+ threadName
					+" privateData:"+ private_data
					+" instance Data:"+ instance_data
					+" static Data:"+ static_data);
				static_data--;
			}

			//�����߳����еı�־��
			if (--killThread_counter == 0){
				return;
			}

		}
	}

	public void sample2() {
		
		//ע�ⲻ����ʹ��while(true),--killThread_counter,��Ϊ���ַ�����ʵ��������,
		//�ᵼ�³�ͻ��
		for(int i=0;i<killThread_counter;i++) {
//			synchronized (this) {
				String threadName = Thread.currentThread().getName();
				instance_data++;
				System.out.println(
				    " instance name:"+instance_name
					+" thread name:"+ threadName
					+" privateData:"+ private_data
					+" instance Data:"+ instance_data
					+" static Data:"+ static_data);
				instance_data--;
//			}			
		}
	}

	public static int getStatic_data() {
		return static_data;
	}

	public int getInstance_data() {
		return instance_data;
	}

	public int getPrivate_data() {
		return private_data;
	}

	public void setInstance_data(int i) {
		instance_data = i;
	}

	public void setPrivate_data(int i) {
		private_data = i;
	}

	public static void setStatic_data(int si_data) {
			CustomThread.static_data = si_data;
		}
}
