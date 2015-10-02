package org.appfuse.common.util.datastructure;

/*  
 *@date:08-07-06  
 
 *@descript:���е�ʵ��  
 
 **/  
  
public class SequentialQueue   
  
{   
  
    private static final int defaultSize=10;//Ĭ�ϴ�С   
  
    private int front;//��ͷ   
  
    private int rear;//��β    
  
    private int count;//Ԫ�ظ���ͳ����   
  
    private int maxQueueSize;//������ݸ���   
  
    private Object[] queue;//�������   
  
       
  
       
  
    //�޲ι��캯��,����Ĭ�ϴ�С�Ķ���   
  
    public SequentialQueue()   
  
    {   
  
        this(defaultSize);     
  
    }   
  
    //���ι��캯������������ָ����С�Ķ���   
  
    public SequentialQueue(int size)   
  
    {   
  
        queue=new Object[size];   
  
        maxQueueSize=size;   
  
        front=rear=0;//���ö��еĶ�ͷ�Ͷ�βָ��   
  
        count=0;   
  
    }   
  
    //�ж��Ƿ�Ϊ��   
  
    public boolean isEmpty()   
  
    {   
  
        return count==0;   
  
    }   
  
    //�ж϶����Ƿ�����   
  
    public boolean isFull()   
  
    {   
  
        return (count>0)&&(rear==front);   
  
    }   
  
    //�����   
  
    public boolean append(Object obj)   
  
    {   
  
        //�ж϶����Ƿ������������������Ӳ���   
  
        if(!isFull())   
  
        {   
  
            queue[rear]=obj;//���²����Ԫ�ط������   
  
            rear=(rear+1)%maxQueueSize;//�����趨��βָ��   
  
            count++;//Ԫ�ؼ������� 1   
  
            return true;   
  
        }else  
  
        {   
  
            System.out.println ("��������! "+obj+"�޷�����!");   
  
            return false;   
  
        }   
  
    }   
  
    //������   
  
    public Object delete()   
  
    {   
  
        Object ret=null;   
  
        //�ж��Ƿ�Ϊ��   
  
        if(!isEmpty())   
  
        {   
  
            ret=queue[front];//��ȡ����Ԫ��   
  
            front=(front+1)%maxQueueSize;   
  
            count--;//Ԫ�ؼ������� 1   
  
        }else  
  
        {   
  
            System.out.println ("����Ϊ��!");   
  
        }   
  
        return ret;   
  
    }   
  
    //��ȡ��ͷԪ��   
  
    public Object getHead()   
  
    {   
  
        Object ret=null;   
  
        if(!isEmpty()) //���зǿ�   
  
        {   
  
            ret=queue[front];//��ȡ��ͷԪ��   
  
               
  
        }else  
  
        {   
  
            System.out.println ("����Ϊ��!");   
  
        }   
  
        return ret;   
  
    }   
  
    // ��ն���   
  
    public void clear()   
  
    {   
  
        count=0;//Ԫ�ؼ������� 0   
  
        front=rear=0; //��ͷָ��ָ���βָ��   
  
    }   
  
       
  
    public static void main(String[] args)   
  
    {   
  
        SequentialQueue myQueue=new SequentialQueue(5);//��ʼ�����еĿռ��СΪ 5   
  
        int[] data={1,3,5,7,9,11};   
  
        try{   
  
               
  
            System.out.println ("���Ԫ������Ϊ��");   
  
            //���������   
  
            for (int i = 0; i<data.length; i++)   
  
            {   
  
                myQueue.append(new Integer(data[i]));   
  
                System.out.print (data[i]+" ");   
  
            }   
  
            System.out.println ("");   
  
            System.out.println("��ǰ��ͷԪ��Ϊ��"+myQueue.getHead());   
  
            System.out.print ("����Ԫ������Ϊ��");   
  
            //�����ݳ���   
  
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
