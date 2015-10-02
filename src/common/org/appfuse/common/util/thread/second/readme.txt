synchronized 关键字，它包括两种用法：synchronized 方法和 synchronized 块。 
1. synchronized 方法：通过在方法声明中加入 synchronized关键字来声明 synchronized 方法。如： 
public synchronized void accessVal(int newVal); 
synchronized 方法控制对类成员变量的访问：每个类实例对应一把锁，每个 synchronized 方法都必须获得调用该方法的类实例的锁方能执行，否则所属线程阻塞，方法一旦执行，就独占该锁，直到从该方法返回时才将锁释放，此后被阻塞的线程方能获得该锁，重新进入可执行状态。这种机制确保了同一时刻对于每一个类实例，其所有声明为 synchronized 的成员函数中至多只有一个处于可执行状态（因为至多只有一个能够获得该类实例对应的锁），从而有效避免了类成员变量的访问冲突（只要所有可能访问类成员变量的方法均被声明为 synchronized）。 
在 Java 中，不光是类实例，每一个类也对应一把锁，这样我们也可将类的静态成员函数声明为 synchronized ，以控制其对类的静态成员变量的访问。 
synchronized 方法的缺陷：若将一个大的方法声明为synchronized 将会大大影响效率，典型地，若将线程类的方法 run() 声明为 synchronized ，由于在线程的整个生命期内它一直在运行，因此将导致它对本类任何 synchronized 方法的调用都永远不会成功。当然我们可以通过将访问类成员变量的代码放到专门的方法中，将其声明为 synchronized ，并在主方法中调用来解决这一问题，但是 Java 为我们提供了更好的解决办法，那就是 synchronized 块。 

2. synchronized 块：通过 synchronized关键字来声明synchronized 块。语法如下： 
synchronized(syncObject) { 
//允许访问控制的代码 
} 

synchronized 块是这样一个代码块，其中的代码必须获得对象 syncObject （如前所述，可以是类实例或类）的锁方能执行，具体机制同前所述。由于可以针对任意代码块，且可任意指定上锁的对象，故灵活性较高。 

对synchronized(this)的一些理解（很细致，感谢作者！） 

一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。 

二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。 

三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。 

四、第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。 

五、以上规则对其它对象锁同样适用 
具体例子看：http://cyco00.blog.163.com/getBlog.do?bid=_fks_w_FIQ3sfjw_klZmhf8MtD2P07FAKA83I


------------------------
如何在程序中控制线程的生命周期
-------------------------
如何来控制线程的生命周期，由下面例子说明

class ThreadDemo implements Runnable
{
 private boolean STOP = false;   //设置一标记
 
 public  void stopMe() 
 {
  STOP = true;    //程序运行过程中通过调用此方法来改变线程标记
 }
 public void run()
 {
  while(!STOP)    //循环要用标记来作为线程运行的判别条件
  {
   System.out.println(Thread.currentThread().getName()+" is running!");
  }
 }
};

class Test1
{
 public static void main(String [] args)
 {
  ThreadDemo tt=new ThreadDemo();
  new Thread(tt).start();
  for(int i=0;i<100;i++)
  {
   if(i==50)
    tt.stopMe();
   System.out.println(Thread.currentThread().getName()+" is running!");
  }
 }

};

编译运行的结果为
main is running!
main is running!
main is running!
main is running!
main is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
main is running!
Thread-0 is running!
main is running!
Thread-0 is running!
main is running!
Thread-0 is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
Thread-0 is running!
main is running!
Thread-0 is running!
main is running!
Thread-0 is running!
main is running!
Thread-0 is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!
main is running!

在main（）方法运行50次后，我们通过将Thread-0线程运行过程中使用的一个标志（boolean型）值改为false，这时候线程的生命周期就结束了

---------------------
Java多线程中线程间的通信
---------------------
我们首先来看一个例子——生产者-消费者问题，即生产者向缓冲池发送数据，消费者由缓冲池取数据使用，我们假设数据信息为（姓名、性别）二元组，且缓冲池的长度为1，即只能放一个二元组
我们简单实现如下：
class Producer implements Runnable
{
 Resource res;

 public Producer(Resource res)
 {
  this.res=res;
 }

 public void run ()
 {
  int i=0;
  while(true)                 // 这里假设数据有两个，下一段程序用于实现在把这两个数据循环放入缓冲池
  {
   if(i==0)
    res.put("zhangsan","male");
    
   else
    res.put("lisi","female");
   i=(i+1)%2;
  }
 }
};

class Consumer implements Runnable
{
 Resource res;

 public Consumer(Resource res)
 {
  this.res=res;
 }

 public void run()
 {
  while (true)
  {
   res.get();                       //消费者从缓冲池取数据
  }

 }
};

class Resource
{
 private String ;
 private String sex = "unknown";
 private boolean bfull = false;

 public void put(String name,String sex)             //生产者放数据的方法
 {
  this.name = name;
  this.sex = sex;
 }

 public void get()                                  //消费者取数据的方法
 {
  System.out.print(name);
  System.out.println(":"+sex);
 }
};

class Test                                                 //测试结果，main方法所在的类
{
 public static void main (String [] args)
 {
  Resource t = new Resource();
  new Thread (new Producer(t)).start();
  new Thread (new Consumer(t)).start();
 }
};

编译运行我们得到一个无限序列，截取一段如下：
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
zhangsan:female
zhangsan:female
zhangsan:female
zhangsan:female
zhangsan:female
zhangsan:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:male

我们发现了程序中居然有zhangsan:female和lisi:male的结果，但我们程序中生产者向缓冲池只放了("zhangsan","male")和("lisi","female")信息，这是为什么？  这主要是由于两个线程运行的时候CPU频繁地进行切换，当程序运行到 res.put("zhangsan","male");时，就去相应的put方法，当运行到put方法中this.sex = sex;语句的时候，CPU切换到了消费者线程，这时候生产者线程还没有来得及把zhangsan的性别信息改变，而缓冲池中的性别信息还是lisi的female,这时候程序就发生了错误，所以我们要把生产者向缓冲池投放数据的过程定义为一个原子过程，即CPU在运行这一段代码的时候不能转去别的线程执行，由上一篇有关synchronized关键字的文章，我们可以用同步方法简单实现如下：

class Producer implements Runnable
{
 Resource res;

 public Producer(Resource res)
 {
  this.res=res;
 }

 public void run ()
 {
  int i=0;
  while(true)             // 这里假设数据有两个，下一段程序用于实现在把这两个数据循环放入缓冲池
  {
   if(i==0)
    res.put("zhangsan","male");
    
   else
    res.put("lisi","female");
   i=(i+1)%2;
  }
 }
};

class Consumer implements Runnable
{
 Resource res;

 public Consumer(Resource res)
 {
  this.res=res;
 }

 public void run()
 {
  while (true)
  {
   res.get();                       //消费者从缓冲池取数据
  }

 }
};

class Resource
{
 private String ;
 private String sex = "unknown";
 private boolean bfull = false;

 public synchronized void put(String name,String sex)      //生产者放数据的方法,这里设置为同步方法
 {
  this.name = name;
  this.sex = sex;
 }

 public synchronized void get()                                  //消费者取数据的方法，同步方法
 {
  System.out.print(name);
  System.out.println(":"+sex);
 }
};

class Test
{
 public static void main (String [] args)
 {
  Resource t = new Resource();
  new Thread (new Producer(t)).start();
  new Thread (new Consumer(t)).start();
 }
};

编译运行后我们发现结果中不再出现类似于zhangsan:female和lisi:male的错误了，即当生产者向缓冲池发数据的时候，消费者不能够取数据。截取一段运行结果如下：
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
lisi:female
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male
zhangsan:male

这里用同步方法实现了线程间的同步，但是我们发现运行结果中，出现了多条连续的zhangsan:male或lisi:female，但我们一般希望生产者往缓冲池投放数据以后，当缓冲池满的时候，由消费者来取走数据，当缓冲池不满的时候，生产者再往其中投数据。这就是线程间的通信问题。我们将程序改写如下：

class Producer implements Runnable
{
 Resource res;

 public Producer(Resource res)
 {
  this.res=res;
 }

 public void run ()
 {
  int i=0;
  while(true)                                 // 这里假设数据有两个，下一段程序用于实现在把这两个数据循环放入缓冲池
  {
   if(i==0)
    res.put("zhangsan","male");
    
   else
    res.put("lisi","female");
   i=(i+1)%2;
  }
 }
};

class Consumer implements Runnable
{
 Resource res;

 public Consumer(Resource res)
 {
  this.res=res;
 }

 public void run()
 {
  while (true)
  {
   res.get();                       //消费者从缓冲池取数据
  }

 }
};

class Resource
{
 private String ;
 private String sex = "unknown";
 boolean bfull = false;

 public synchronized void put(String name,String sex)       //生产者放数据的方法,这里设置为同步方法
 {
  if(bfull)  
  try
  {
   wait();      //当缓冲池已经有数据时，生产者线程等待，避免了数据覆盖
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }  
  this.name = name;
  this.sex = sex;
  bfull = true;
  notify();      //生产者放入数据后，唤醒休眠的消费者线程继续取数据  

 }

 public synchronized void get()                                  //消费者取数据的方法，同步方法
 {
  if(!bfull)
  try
  {
   wait();      //缓冲池没有数据据时，消费者线程等待，避免了数据重复读出
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }
  System.out.print(name);
  System.out.println(":"+sex);
  bfull = false;
  notify();      //消费者取完数据后，唤醒休眠的生产者线程向缓冲池投数据
 }
};

class Test
{
 public static void main (String [] args)
 {
  Resource t = new Resource();
  new Thread (new Producer(t)).start();
  new Thread (new Consumer(t)).start();
 }
};

编译运行后的结果为：
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female
zhangsan:male
lisi:female.....

这时已经符合我们的要求。

--------------------------
synchronized在多线程中的应用
--------------------------
先看一个例子：

//模仿火车售票系统，假设有4个售票点同时售票，我们可以把四个售票点定义为四个独立线程

class Test
{
 public static void main(String[] args) 
 {
  TestThread tt=new TestThread();
  new Thread(tt).start();        //每一个售票点定义为一个独立的线程，共同卖出所有的票
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
 }
}

class TestThread implements Runnable
{
 int ticket=10;                 //票数定义
 public void run()
 {
  while(ticket>0)
  {

     System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");

     ticket--;

  }

 }

}

运行结果为：

Thread-0:ticket 10is saling
Thread-0:ticket 9is saling
Thread-0:ticket 8is saling
Thread-0:ticket 7is saling
Thread-0:ticket 6is saling
Thread-0:ticket 5is saling
Thread-0:ticket 4is saling
Thread-0:ticket 3is saling
Thread-1:ticket 3is saling
Thread-2:ticket 3is saling
Thread-3:ticket 3is saling
Thread-1:ticket 2is saling
Thread-2:ticket 1is saling

我们可以看出来票３倍卖出了４次，很明显不正确，我们仔细观察程序，可以发现问题出在

while(ticket>0)
  {
   System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");

   ticket--;

  }　　

的执行当中，当线程０（模拟售票点１）执行System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");语句时（模拟售票点１卖出票３），这时，CPU切换到了线程１（售票点２）

而这时，售票点０卖出了票３，但是在剩余的票中没有及时将票３除去（即执行ticket--;语句），所以此时在线程１处，并不知道票３已经卖出，所以它又把票３卖出，同理，售票点２和３也重复地卖了票３，原因是我们无法确定CPU无法进行线程切换，如果我们可以把一段要执行的语句放到一个无法分开的块中，使CPU没有执行完这个块时无法去执行其他的块，我们就可以正确实现上述售票模拟了，这就是synchronized关键字的作用！！

 

我们把上述实现改动一下，并加入synchronized代码块

 

class Test
{
 public static void main(String[] args) 
 {
  TestThread tt=new TestThread();
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
 }
}

class TestThread implements Runnable
{
 int ticket=10;
 String str=new String(" ");
 public void run()
 {
  while(true)
  { 
   synchronized(str)                    //synchronized 代码块
   {
    if(ticket>0)
    {
     System.out.println(Thread.currentThread().getName()+":ticket "+ticket--+"is saling");
    }
   }
  }
 }
};

 

运行一下，结果为：

Thread-0:ticket 10is saling
Thread-0:ticket 9is saling
Thread-0:ticket 8is saling
Thread-0:ticket 7is saling
Thread-0:ticket 6is saling
Thread-0:ticket 5is saling
Thread-0:ticket 4is saling
Thread-0:ticket 3is saling
Thread-1:ticket 2is saling
Thread-2:ticket 1is saling

 

结果正确

 

我们再改动一下我们的程序：

class Test
{
 public static void main(String[] args) 
 {
  TestThread tt=new TestThread();
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
 }
}

class TestThread implements Runnable
{
 int ticket=10;
 String str=new String(" ");
 public void run()
 {
  while(true)
  {
   sale();
  }
 }
 public synchronized void sale()      //synchronized方法
 {
  if(ticket > 0)
   System.out.println(Thread.currentThread().getName()+":ticket "+ticket--+"is saling");

 }
};


运行结果为：

Thread-0:ticket 10is saling
Thread-0:ticket 9is saling
Thread-0:ticket 8is saling
Thread-0:ticket 7is saling
Thread-0:ticket 6is saling
Thread-0:ticket 5is saling
Thread-0:ticket 4is saling
Thread-0:ticket 3is saling
Thread-1:ticket 2is saling
Thread-2:ticket 1is saling

结果也正确！

 

由此我们给出synchronized关键字在多线程中的作用：

两种用法：synchronized 方法和 synchronized 块。
1. synchronized 方法：通过在方法声明中加入 synchronized关键字来声明 synchronized 方法。如上述例子所述。
synchronized 方法控制对类成员变量的访问：每个类实例对象对应一把锁，每个 synchronized 方法都必须获得调用该方法的类实例对象的锁方能执行，否则所属线程阻塞，方法一旦执行，就独占该锁，直到从该方法返回时才将锁释放，此后被阻塞的线程方能获得该锁，重新进入可执行状态。这种机制确保了同一时刻对于每一个类实例对象，其所有声明为 synchronized 的成员方法中至多只有一个处于可执行状态（因为至多只有一个能够获得该类实例对象对应的锁），从而有效避免了类成员变量的访问冲突（只要所有可能访问类成员变量的方法均被声明为 synchronized）。
在 Java 中，不光是类实例，每一个类也对应一把锁，这样我们也可将类的静态成员函数声明为 synchronized ，以控制其对类的静态成员变量的访问。
synchronized 方法的缺陷：若将一个大的方法声明为synchronized 将会大大影响效率，典型地，若将线程类的方法 run() 声明为 synchronized ，由于在线程的整个生命期内它一直在运行，因此将导致它对本类任何 synchronized 方法的调用都永远不会成功。当然我们可以通过将访问类成员变量的代码放到专门的方法中，将其声明为 synchronized ，并在主方法中调用（如上述例子中的sale()方法，在run()方法中来调用sale()方法）来解决这一问题，但是 Java 为我们提供了更好的解决办法，那就是 synchronized 块。
2. synchronized 块：通过 synchronized关键字来声明synchronized 块。语法如下： 
synchronized(syncObject) {
//允许访问控制的代码
}
synchronized 块是这样一个代码块，其中的代码必须获得对象 syncObject （如前所述，可以是类实例或类，上述例子中我们定义了一个String类的对象）的锁方能执行，具体机制同前所述。由于可以针对任意代码块，且可任意指定上锁的对象，故灵活性较高。

对synchronized(this)的一些理解

 一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。 

二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。 

三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。 

四、当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。 

五、以上规则对其它对象锁同样适用. 


