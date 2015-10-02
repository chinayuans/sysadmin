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

	//用来结束线程的标志
	private int killThread_counter = 1000;

    //用来计数类所生成的实例个数
	private static int object_counter = 0; 
	
	//记载类所生成的实例的名字。
	private int instance_name=0;
	public CustomThread() {
		instance_name=++object_counter;
	}

	
	//用来防止静态数据访问冲突的一个手段。
	private static Object static_lock = new Object();

	//用来防止“对类实例数据的访问冲突”的一个手段用synchronized(this)。
	
	
	
	//=================================================
	//=================================================

	//不准备供 类的一个实例中的多个线程共享的资源
	//仅仅用来表示它不是挣强的资源。
	private int private_data = 0;

	//类的多个实例共享的资源
	private static int static_data;

	//类的一个实例中的多个线程共享的资源
	private int instance_data = 0;

	//如果函数前面加上synchronized，那么就会防止对"同一对象的函数访问冲突"的问题。 
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

			//结束线程运行的标志；
			if (--killThread_counter == 0){
				return;
			}

		}
	}

	public void sample2() {
		
		//注意不可以使用while(true),--killThread_counter,因为它又访问了实例变量了,
		//会导致冲突。
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
