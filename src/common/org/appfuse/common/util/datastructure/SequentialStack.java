package org.appfuse.common.util.datastructure;

/*  
 *@date:08-07-06  
 
 *@descript:ջ��ʵ����Ӧ��  
 
 **/  
  
public class SequentialStack   
  
{   
  
    private static final int defaultSize=10;//ȫ�ֱ�����Ĭ�ϴ�С   
  
    private int top;//ջ������Ԫ���±�   
  
    private int maxStackSize;//�������Ԫ�ظ���   
  
    private Object[] stack;   
  
       
  
       
  
    //�޲ι��캯��   
  
    public SequentialStack()   
  
    {   
  
        this(defaultSize);   
  
    }   
  
       
  
    //���ι��캯��   
  
    public SequentialStack(int size)   
  
    {   
  
        maxStackSize=size;//����ջ�����洢�ռ�   
  
        top=-1;//��Ϊ��ջ   
  
        stack=new Object[size];//��������   
  
    }   
  
    //�ж��Ƿ�Ϊ��   
  
    public boolean isEmpty()   
  
    {   
  
        return (top==-1);   
  
    }   
  
    //�ж��Ƿ�����   
  
    public boolean isFull()   
  
    {   
  
        return (top==maxStackSize-1);   
  
    }   
  
    //��ջ   
  
    public boolean push(Object obj)   
  
    {   
  
        //�ж�ջ�Ƿ�������������ִ����ջ����   
  
        if(!isFull())     
  
        {   
  
            top++;//�����µ�ջ��λ��   
  
            stack[top]=obj;//����Ԫ��   
  
            return true;   
  
        }else  
  
        {   
  
            System.out.println ("��ջ����! "+obj+"�޷���ջ!");   
  
            return false;   
  
        }   
  
    }   
  
    //��ջ   
  
    public Object pop()   
  
    {   
  
        Object ret=null;   
  
        //�ж��Ƿ�Ϊ��ջ���粻�ǣ���ִ�г�ջ����   
  
        if(!isEmpty())   
  
        {   
  
            ret=stack[top];   
  
            stack[top]=null;   
  
            top--;   
  
        }else  
  
        {   
  
            System.out.println ("��ջΪ��!");   
  
        }   
  
        return ret;   
  
    }   
  
    //ȡջ��Ԫ��   
  
    public Object getTop()   
  
    {   
  
        Object ret=null;   
  
        if(!isEmpty())   
  
        {   
  
            ret=stack[top];   
  
        }else  
  
        {   
  
            System.out.println ("��ջΪ��!");   
  
        }   
  
        return ret;   
  
    }   
  
    //���ջ��Ԫ��   
  
    public void clear()   
  
    {   
  
        top=-1;   
  
    }   
  
       
  
    public static void main(String[] args)   
  
    {   
  
        SequentialStack myStack=new SequentialStack(5);   
  
        int[] data={1,3,5,7,9,11};   
  
        try {   
  
            //������ջ   
  
                System.out.println ("��ջԪ������Ϊ��");   
  
                for (int i = 0; i<data.length; i++)   
  
                {   
  
                    System.out.print (data[i]+" ");   
  
                    myStack.push(new Integer(data[i]));   
  
                }   
  
                System.out.println ();   
  
                System.out.println ("��ǰջ��Ԫ��Ϊ��"+myStack.getTop());   
  
                System.out.println ("��ջԪ������Ϊ��");   
  
                //ջ��Ԫ�س�ջ   
  
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
