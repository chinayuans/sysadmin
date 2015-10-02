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
 * 实现线程，有两种方法，一种是继承Thread类，一种是实现Runnable接口。
 * 本文推荐实现Runnable接口的方法。
 * 1.把需要共享的数据(可以是静态的，非静态的变量了)放在一个实现Runnable
 * 接口的类里面，然后把这个类的实例传给多个Thread的构造方法。也就是
 * 首先CustomerThread extends Runnable, 然后CustomerThread custom= new CustomerThread(),
 * 再Thread thread1=new Thread(custom,"thread1") ;
 *  Thread thread2=new Thread(custom,"thread2") 这样，新创建
 * 的多个Thread(thread1,thread2)，都共同拥有一个Runnable实例，共享同一份数据。
 * 
 * 如果不想非静态的变量变成共享资源的话,就应改这样设计:
 * CustomerThread extends Runnable;
 * CustomerThread custom1= new CustomerThread();
 * CustomerThread custom2= new CustomerThread();
 * Thread thread1=new Thread(custom1,"thread1");
 * Thread thread2=new Thread(custom2,"thread2");
 * 那么此时thread1和thread2就只能共享CustomerThread中的静态变量了;
 * 2.如果采用继承Thread类的方法，就只好使用static静态成员了。
 * 如果共享的数据比较多，就需要大量的static静态成员，令程序数据结构混乱，
 * 难以扩展。这种情况应该尽量避免。
 */
public class CustomThreadMain {

	
	public static void main(String[] args) {
		
		
		CustomThread.setStatic_data(100);
		
		//产生三个实例，每个实例又产生一个线程，并且使其运行起来。
		CustomThread s1 = new CustomThread();//实例s1
		CustomThread s2 = new CustomThread();//实例s2
		CustomThread s3 = new CustomThread();//实例s3
		
		new Thread(s1,"t1").start();	//实例s1的线程1	
		s1.setPrivate_data(1);
		
		new Thread(s2,"t2").start();	//实例s2的线程2
		s2.setPrivate_data(2);
		
		new Thread(s3,"t3").start();    //实例s3的线程3
		s3.setPrivate_data(3);
		
		//注意：线程1，2，3在不同的实例中运行，
		//实例变量资源不可能有冲突问题,因为是不同的实例中。
		//但是静态变量资源可能有冲突问题，因为几个实例共同
		//使用静态变量资源。
		//可以看到如果在run函数中注释了  synchronized(lock) 的话，
		//静态资源static_data 有生成103的值的出现，是因为synchronized(lock)
		//中的{}这段代码中其他线程的介入访问static_data数据。
		//如果加上synchronized(lock)的情况的话，可以防止在{}中
		//有多个线程的同时访问。
		
		
		
		//注意：下面的线程4，5是在一个共同的实例s4中运行，
		//如果实例中有实例变量、静态变量的资源的话，会导致资源冲突。
		//几个线程共享一个数据:instance_data
		CustomThread s4 = new CustomThread();
		s4.setInstance_data(100);
		
		
		Thread s4_t1=new Thread(s4,"s4_t1");//实例s4的线程4	
		s4_t1.start();
		
						
		Thread s4_t2=new Thread(s4,"s4_t2");//实例s4的线程5	
		s4_t2.setName("r4_2");
		s4_t2.start();
		
		
	}

}
