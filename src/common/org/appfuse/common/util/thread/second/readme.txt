synchronized �ؼ��֣������������÷���synchronized ������ synchronized �顣 
1. synchronized ������ͨ���ڷ��������м��� synchronized�ؼ��������� synchronized �������磺 
public synchronized void accessVal(int newVal); 
synchronized �������ƶ����Ա�����ķ��ʣ�ÿ����ʵ����Ӧһ������ÿ�� synchronized �����������õ��ø÷�������ʵ����������ִ�У����������߳�����������һ��ִ�У��Ͷ�ռ������ֱ���Ӹ÷�������ʱ�Ž����ͷţ��˺��������̷߳��ܻ�ø��������½����ִ��״̬�����ֻ���ȷ����ͬһʱ�̶���ÿһ����ʵ��������������Ϊ synchronized �ĳ�Ա����������ֻ��һ�����ڿ�ִ��״̬����Ϊ����ֻ��һ���ܹ���ø���ʵ����Ӧ���������Ӷ���Ч���������Ա�����ķ��ʳ�ͻ��ֻҪ���п��ܷ������Ա�����ķ�����������Ϊ synchronized���� 
�� Java �У���������ʵ����ÿһ����Ҳ��Ӧһ��������������Ҳ�ɽ���ľ�̬��Ա��������Ϊ synchronized ���Կ��������ľ�̬��Ա�����ķ��ʡ� 
synchronized ������ȱ�ݣ�����һ����ķ�������Ϊsynchronized ������Ӱ��Ч�ʣ����͵أ������߳���ķ��� run() ����Ϊ synchronized ���������̵߳���������������һֱ�����У���˽��������Ա����κ� synchronized �����ĵ��ö���Զ����ɹ�����Ȼ���ǿ���ͨ�����������Ա�����Ĵ���ŵ�ר�ŵķ����У���������Ϊ synchronized �������������е����������һ���⣬���� Java Ϊ�����ṩ�˸��õĽ���취���Ǿ��� synchronized �顣 

2. synchronized �飺ͨ�� synchronized�ؼ���������synchronized �顣�﷨���£� 
synchronized(syncObject) { 
//������ʿ��ƵĴ��� 
} 

synchronized ��������һ������飬���еĴ�������ö��� syncObject ����ǰ��������������ʵ�����ࣩ��������ִ�У��������ͬǰ���������ڿ�������������飬�ҿ�����ָ�������Ķ��󣬹�����Խϸߡ� 

��synchronized(this)��һЩ��⣨��ϸ�£���л���ߣ��� 

һ�������������̷߳���ͬһ������object�е����synchronized(this)ͬ�������ʱ��һ��ʱ����ֻ����һ���̵߳õ�ִ�С���һ���̱߳���ȴ���ǰ�߳�ִ�������������Ժ����ִ�иô���顣 

����Ȼ������һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ����һ���߳���Ȼ���Է��ʸ�object�еķ�synchronized(this)ͬ������顣 

��������ؼ����ǣ���һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�������̶߳�object����������synchronized(this)ͬ�������ķ��ʽ��������� 

�ġ�����������ͬ����������ͬ������顣Ҳ����˵����һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�����ͻ�������object�Ķ�����������������̶߳Ը�object��������ͬ�����벿�ֵķ��ʶ�����ʱ������ 

�塢���Ϲ��������������ͬ������ 
�������ӿ���http://cyco00.blog.163.com/getBlog.do?bid=_fks_w_FIQ3sfjw_klZmhf8MtD2P07FAKA83I


------------------------
����ڳ����п����̵߳���������
-------------------------
����������̵߳��������ڣ�����������˵��

class ThreadDemo implements Runnable
{
 private boolean STOP = false;   //����һ���
 
 public  void stopMe() 
 {
  STOP = true;    //�������й�����ͨ�����ô˷������ı��̱߳��
 }
 public void run()
 {
  while(!STOP)    //ѭ��Ҫ�ñ������Ϊ�߳����е��б�����
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

�������еĽ��Ϊ
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

��main������������50�κ�����ͨ����Thread-0�߳����й�����ʹ�õ�һ����־��boolean�ͣ�ֵ��Ϊfalse����ʱ���̵߳��������ھͽ�����

---------------------
Java���߳����̼߳��ͨ��
---------------------
������������һ�����ӡ���������-���������⣬���������򻺳�ط������ݣ��������ɻ����ȡ����ʹ�ã����Ǽ���������ϢΪ���������Ա𣩶�Ԫ�飬�һ���صĳ���Ϊ1����ֻ�ܷ�һ����Ԫ��
���Ǽ�ʵ�����£�
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
  while(true)                 // ���������������������һ�γ�������ʵ���ڰ�����������ѭ�����뻺���
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
   res.get();                       //�����ߴӻ����ȡ����
  }

 }
};

class Resource
{
 private String ;
 private String sex = "unknown";
 private boolean bfull = false;

 public void put(String name,String sex)             //�����߷����ݵķ���
 {
  this.name = name;
  this.sex = sex;
 }

 public void get()                                  //������ȡ���ݵķ���
 {
  System.out.print(name);
  System.out.println(":"+sex);
 }
};

class Test                                                 //���Խ����main�������ڵ���
{
 public static void main (String [] args)
 {
  Resource t = new Resource();
  new Thread (new Producer(t)).start();
  new Thread (new Consumer(t)).start();
 }
};

�����������ǵõ�һ���������У���ȡһ�����£�
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

���Ƿ����˳����о�Ȼ��zhangsan:female��lisi:male�Ľ���������ǳ������������򻺳��ֻ����("zhangsan","male")��("lisi","female")��Ϣ������Ϊʲô��  ����Ҫ�����������߳����е�ʱ��CPUƵ���ؽ����л������������е� res.put("zhangsan","male");ʱ����ȥ��Ӧ��put�����������е�put������this.sex = sex;����ʱ��CPU�л������������̣߳���ʱ���������̻߳�û�����ü���zhangsan���Ա���Ϣ�ı䣬��������е��Ա���Ϣ����lisi��female,��ʱ�����ͷ����˴�����������Ҫ���������򻺳��Ͷ�����ݵĹ��̶���Ϊһ��ԭ�ӹ��̣���CPU��������һ�δ����ʱ����תȥ����߳�ִ�У�����һƪ�й�synchronized�ؼ��ֵ����£����ǿ�����ͬ��������ʵ�����£�

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
  while(true)             // ���������������������һ�γ�������ʵ���ڰ�����������ѭ�����뻺���
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
   res.get();                       //�����ߴӻ����ȡ����
  }

 }
};

class Resource
{
 private String ;
 private String sex = "unknown";
 private boolean bfull = false;

 public synchronized void put(String name,String sex)      //�����߷����ݵķ���,��������Ϊͬ������
 {
  this.name = name;
  this.sex = sex;
 }

 public synchronized void get()                                  //������ȡ���ݵķ�����ͬ������
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

�������к����Ƿ��ֽ���в��ٳ���������zhangsan:female��lisi:male�Ĵ����ˣ������������򻺳�ط����ݵ�ʱ�������߲��ܹ�ȡ���ݡ���ȡһ�����н�����£�
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

������ͬ������ʵ�����̼߳��ͬ�����������Ƿ������н���У������˶���������zhangsan:male��lisi:female��������һ��ϣ���������������Ͷ�������Ժ󣬵����������ʱ������������ȡ�����ݣ�������ز�����ʱ����������������Ͷ���ݡ�������̼߳��ͨ�����⡣���ǽ������д���£�

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
  while(true)                                 // ���������������������һ�γ�������ʵ���ڰ�����������ѭ�����뻺���
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
   res.get();                       //�����ߴӻ����ȡ����
  }

 }
};

class Resource
{
 private String ;
 private String sex = "unknown";
 boolean bfull = false;

 public synchronized void put(String name,String sex)       //�����߷����ݵķ���,��������Ϊͬ������
 {
  if(bfull)  
  try
  {
   wait();      //��������Ѿ�������ʱ���������̵߳ȴ������������ݸ���
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }  
  this.name = name;
  this.sex = sex;
  bfull = true;
  notify();      //�����߷������ݺ󣬻������ߵ��������̼߳���ȡ����  

 }

 public synchronized void get()                                  //������ȡ���ݵķ�����ͬ������
 {
  if(!bfull)
  try
  {
   wait();      //�����û�����ݾ�ʱ���������̵߳ȴ��������������ظ�����
  }
  catch (Exception e)
  {
   System.out.println (e.getMessage());
  }
  System.out.print(name);
  System.out.println(":"+sex);
  bfull = false;
  notify();      //������ȡ�����ݺ󣬻������ߵ��������߳��򻺳��Ͷ����
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

�������к�Ľ��Ϊ��
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

��ʱ�Ѿ��������ǵ�Ҫ��

--------------------------
synchronized�ڶ��߳��е�Ӧ��
--------------------------
�ȿ�һ�����ӣ�

//ģ�»���Ʊϵͳ��������4����Ʊ��ͬʱ��Ʊ�����ǿ��԰��ĸ���Ʊ�㶨��Ϊ�ĸ������߳�

class Test
{
 public static void main(String[] args) 
 {
  TestThread tt=new TestThread();
  new Thread(tt).start();        //ÿһ����Ʊ�㶨��Ϊһ���������̣߳���ͬ�������е�Ʊ
  new Thread(tt).start();
  new Thread(tt).start();
  new Thread(tt).start();
 }
}

class TestThread implements Runnable
{
 int ticket=10;                 //Ʊ������
 public void run()
 {
  while(ticket>0)
  {

     System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");

     ticket--;

  }

 }

}

���н��Ϊ��

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

���ǿ��Կ�����Ʊ���������ˣ��Σ������Բ���ȷ��������ϸ�۲���򣬿��Է����������

while(ticket>0)
  {
   System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");

   ticket--;

  }����

��ִ�е��У����̣߳���ģ����Ʊ�㣱��ִ��System.out.println(Thread.currentThread().getName()+":ticket "+ticket+"is saling");���ʱ��ģ����Ʊ�㣱����Ʊ��������ʱ��CPU�л������̣߳�����Ʊ�㣲��

����ʱ����Ʊ�㣰������Ʊ����������ʣ���Ʊ��û�м�ʱ��Ʊ����ȥ����ִ��ticket--;��䣩�����Դ�ʱ���̣߳���������֪��Ʊ���Ѿ��������������ְ�Ʊ��������ͬ����Ʊ�㣲�ͣ�Ҳ�ظ�������Ʊ����ԭ���������޷�ȷ��CPU�޷������߳��л���������ǿ��԰�һ��Ҫִ�е����ŵ�һ���޷��ֿ��Ŀ��У�ʹCPUû��ִ���������ʱ�޷�ȥִ�������Ŀ飬���ǾͿ�����ȷʵ��������Ʊģ���ˣ������synchronized�ؼ��ֵ����ã���

 

���ǰ�����ʵ�ָĶ�һ�£�������synchronized�����

 

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
   synchronized(str)                    //synchronized �����
   {
    if(ticket>0)
    {
     System.out.println(Thread.currentThread().getName()+":ticket "+ticket--+"is saling");
    }
   }
  }
 }
};

 

����һ�£����Ϊ��

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

 

�����ȷ

 

�����ٸĶ�һ�����ǵĳ���

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
 public synchronized void sale()      //synchronized����
 {
  if(ticket > 0)
   System.out.println(Thread.currentThread().getName()+":ticket "+ticket--+"is saling");

 }
};


���н��Ϊ��

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

���Ҳ��ȷ��

 

�ɴ����Ǹ���synchronized�ؼ����ڶ��߳��е����ã�

�����÷���synchronized ������ synchronized �顣
1. synchronized ������ͨ���ڷ��������м��� synchronized�ؼ��������� synchronized ����������������������
synchronized �������ƶ����Ա�����ķ��ʣ�ÿ����ʵ�������Ӧһ������ÿ�� synchronized �����������õ��ø÷�������ʵ�������������ִ�У����������߳�����������һ��ִ�У��Ͷ�ռ������ֱ���Ӹ÷�������ʱ�Ž����ͷţ��˺��������̷߳��ܻ�ø��������½����ִ��״̬�����ֻ���ȷ����ͬһʱ�̶���ÿһ����ʵ����������������Ϊ synchronized �ĳ�Ա����������ֻ��һ�����ڿ�ִ��״̬����Ϊ����ֻ��һ���ܹ���ø���ʵ�������Ӧ���������Ӷ���Ч���������Ա�����ķ��ʳ�ͻ��ֻҪ���п��ܷ������Ա�����ķ�����������Ϊ synchronized����
�� Java �У���������ʵ����ÿһ����Ҳ��Ӧһ��������������Ҳ�ɽ���ľ�̬��Ա��������Ϊ synchronized ���Կ��������ľ�̬��Ա�����ķ��ʡ�
synchronized ������ȱ�ݣ�����һ����ķ�������Ϊsynchronized ������Ӱ��Ч�ʣ����͵أ������߳���ķ��� run() ����Ϊ synchronized ���������̵߳���������������һֱ�����У���˽��������Ա����κ� synchronized �����ĵ��ö���Զ����ɹ�����Ȼ���ǿ���ͨ�����������Ա�����Ĵ���ŵ�ר�ŵķ����У���������Ϊ synchronized �������������е��ã������������е�sale()��������run()������������sale()�������������һ���⣬���� Java Ϊ�����ṩ�˸��õĽ���취���Ǿ��� synchronized �顣
2. synchronized �飺ͨ�� synchronized�ؼ���������synchronized �顣�﷨���£� 
synchronized(syncObject) {
//������ʿ��ƵĴ���
}
����synchronized ��������һ������飬���еĴ�������ö��� syncObject ����ǰ��������������ʵ�����࣬�������������Ƕ�����һ��String��Ķ��󣩵�������ִ�У��������ͬǰ���������ڿ�������������飬�ҿ�����ָ�������Ķ��󣬹�����Խϸߡ�

��synchronized(this)��һЩ���

 һ�������������̷߳���ͬһ������object�е����synchronized(this)ͬ�������ʱ��һ��ʱ����ֻ����һ���̵߳õ�ִ�С���һ���̱߳���ȴ���ǰ�߳�ִ�������������Ժ����ִ�иô���顣 

����Ȼ������һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ����һ���߳���Ȼ���Է��ʸ�object�еķ�synchronized(this)ͬ������顣 

��������ؼ����ǣ���һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�������̶߳�object����������synchronized(this)ͬ�������ķ��ʽ��������� 

�ġ���һ���̷߳���object��һ��synchronized(this)ͬ�������ʱ�����ͻ�������object�Ķ�����������������̶߳Ը�object��������ͬ�����벿�ֵķ��ʶ�����ʱ������ 

�塢���Ϲ��������������ͬ������. 


