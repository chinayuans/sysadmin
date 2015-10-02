package org.appfuse.common.util.datastructure;

/*  
 *@date:08-07-06  
 
 *@descript:队列的实现  
 
 **/  
  
public class SequentialQueue   
  
{   
  
    private static final int defaultSize=10;//默认大小   
  
    private int front;//队头   
  
    private int rear;//对尾    
  
    private int count;//元素个数统计器   
  
    private int maxQueueSize;//最大数据个数   
  
    private Object[] queue;//数组对象   
  
       
  
       
  
    //无参构造函数,创建默认大小的队列   
  
    public SequentialQueue()   
  
    {   
  
        this(defaultSize);     
  
    }   
  
    //带参构造函数，用来创建指定大小的队列   
  
    public SequentialQueue(int size)   
  
    {   
  
        queue=new Object[size];   
  
        maxQueueSize=size;   
  
        front=rear=0;//设置队列的队头和队尾指针   
  
        count=0;   
  
    }   
  
    //判断是否为空   
  
    public boolean isEmpty()   
  
    {   
  
        return count==0;   
  
    }   
  
    //判断队列是否已满   
  
    public boolean isFull()   
  
    {   
  
        return (count>0)&&(rear==front);   
  
    }   
  
    //入队列   
  
    public boolean append(Object obj)   
  
    {   
  
        //判断队列是否已满，不满则进行入队操作   
  
        if(!isFull())   
  
        {   
  
            queue[rear]=obj;//将新插入的元素放入队列   
  
            rear=(rear+1)%maxQueueSize;//重新设定队尾指针   
  
            count++;//元素计数器加 1   
  
            return true;   
  
        }else  
  
        {   
  
            System.out.println ("队列已满! "+obj+"无法插入!");   
  
            return false;   
  
        }   
  
    }   
  
    //出队列   
  
    public Object delete()   
  
    {   
  
        Object ret=null;   
  
        //判断是否为空   
  
        if(!isEmpty())   
  
        {   
  
            ret=queue[front];//获取队列元素   
  
            front=(front+1)%maxQueueSize;   
  
            count--;//元素计算器减 1   
  
        }else  
  
        {   
  
            System.out.println ("队列为空!");   
  
        }   
  
        return ret;   
  
    }   
  
    //获取队头元素   
  
    public Object getHead()   
  
    {   
  
        Object ret=null;   
  
        if(!isEmpty()) //队列非空   
  
        {   
  
            ret=queue[front];//获取队头元素   
  
               
  
        }else  
  
        {   
  
            System.out.println ("队列为空!");   
  
        }   
  
        return ret;   
  
    }   
  
    // 清空队列   
  
    public void clear()   
  
    {   
  
        count=0;//元素计数器清 0   
  
        front=rear=0; //队头指针指向队尾指针   
  
    }   
  
       
  
    public static void main(String[] args)   
  
    {   
  
        SequentialQueue myQueue=new SequentialQueue(5);//初始化队列的空间大小为 5   
  
        int[] data={1,3,5,7,9,11};   
  
        try{   
  
               
  
            System.out.println ("入队元素序列为：");   
  
            //将数据入队   
  
            for (int i = 0; i<data.length; i++)   
  
            {   
  
                myQueue.append(new Integer(data[i]));   
  
                System.out.print (data[i]+" ");   
  
            }   
  
            System.out.println ("");   
  
            System.out.println("当前队头元素为："+myQueue.getHead());   
  
            System.out.print ("出队元素序列为：");   
  
            //将数据出队   
  
            while(!myQueue.isEmpty())   
  
            {   
  
                System.out.print(myQueue.delete()+" ");   
  
            }       
  
               
  
        }   
  
        catch (Exception ex) {   
  
            ex.printStackTrace();   
  
        }   
  
           
  
    }   
  
}  
