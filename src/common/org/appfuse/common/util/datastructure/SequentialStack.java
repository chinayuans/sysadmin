package org.appfuse.common.util.datastructure;

/*  
 *@date:08-07-06  
 
 *@descript:栈的实现与应用  
 
 **/  
  
public class SequentialStack   
  
{   
  
    private static final int defaultSize=10;//全局变量，默认大小   
  
    private int top;//栈顶数据元素下标   
  
    private int maxStackSize;//最大数据元素个数   
  
    private Object[] stack;   
  
       
  
       
  
    //无参构造函数   
  
    public SequentialStack()   
  
    {   
  
        this(defaultSize);   
  
    }   
  
       
  
    //带参构造函数   
  
    public SequentialStack(int size)   
  
    {   
  
        maxStackSize=size;//设置栈的最大存储空间   
  
        top=-1;//置为空栈   
  
        stack=new Object[size];//创建数组   
  
    }   
  
    //判断是否为空   
  
    public boolean isEmpty()   
  
    {   
  
        return (top==-1);   
  
    }   
  
    //判断是否已满   
  
    public boolean isFull()   
  
    {   
  
        return (top==maxStackSize-1);   
  
    }   
  
    //入栈   
  
    public boolean push(Object obj)   
  
    {   
  
        //判断栈是否已满，不满则执行入栈操作   
  
        if(!isFull())     
  
        {   
  
            top++;//产生新的栈顶位置   
  
            stack[top]=obj;//保存元素   
  
            return true;   
  
        }else  
  
        {   
  
            System.out.println ("堆栈已满! "+obj+"无法入栈!");   
  
            return false;   
  
        }   
  
    }   
  
    //出栈   
  
    public Object pop()   
  
    {   
  
        Object ret=null;   
  
        //判断是否为空栈，如不是，则执行出栈操作   
  
        if(!isEmpty())   
  
        {   
  
            ret=stack[top];   
  
            stack[top]=null;   
  
            top--;   
  
        }else  
  
        {   
  
            System.out.println ("堆栈为空!");   
  
        }   
  
        return ret;   
  
    }   
  
    //取栈顶元素   
  
    public Object getTop()   
  
    {   
  
        Object ret=null;   
  
        if(!isEmpty())   
  
        {   
  
            ret=stack[top];   
  
        }else  
  
        {   
  
            System.out.println ("堆栈为空!");   
  
        }   
  
        return ret;   
  
    }   
  
    //清空栈内元素   
  
    public void clear()   
  
    {   
  
        top=-1;   
  
    }   
  
       
  
    public static void main(String[] args)   
  
    {   
  
        SequentialStack myStack=new SequentialStack(5);   
  
        int[] data={1,3,5,7,9,11};   
  
        try {   
  
            //数据入栈   
  
                System.out.println ("入栈元素序列为：");   
  
                for (int i = 0; i<data.length; i++)   
  
                {   
  
                    System.out.print (data[i]+" ");   
  
                    myStack.push(new Integer(data[i]));   
  
                }   
  
                System.out.println ();   
  
                System.out.println ("当前栈顶元素为："+myStack.getTop());   
  
                System.out.println ("出栈元素序列为：");   
  
                //栈内元素出栈   
  
                while(!myStack.isEmpty())   
  
                {   
  
                    System.out.print (myStack.pop()+" ");   
  
                }   
  
                System.out.println ();   
  
        }   
  
        catch (Exception ex) {   
  
         ex.printStackTrace();   
  
        }   
  
    }   
  
}  
