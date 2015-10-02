package org.appfuse.common.util.datastructure;




/* 
*@date:08-07-06  
  
 *@descript:�������ʵ����Ӧ��   
  
 **/   
  
public class ChainList   
  
{   
  
    ListNode head;//ͷָ��   
  
    ListNode current;//��ǰ�ڵ�λ��   
  
    int size;//����Ԫ�ظ���   
  
       
  
       
  
    public ChainList()//���캯��   
  
    {   
  
        head=current=new ListNode(null);   
  
        size=0;   
  
    }   
  
       
  
    //��λ����   
  
    public void index(int i) throws Exception   
  
    {   
  
        if(i<-1||i>size-1)   
  
        {   
  
            throw new Exception("��������!");   
  
        }   
  
        if(i==-1)   
  
        {   
  
            return;   
  
        }   
  
  
  
        current=head.next;   
  
        int j=0;   
  
        while((current!=null)&&j<i)   
  
        {   
  
  
  
            current=current.next;   
  
            j++;   
  
        }   
  
    }   
  
    //���뺯��   
  
    public void insert(int i,Object obj)throws Exception   
  
    {   
  
        if(i<0||i>size)   
  
        {   
  
               
  
            System.out.println ("size="+size);   
  
            System.out.println ("i="+i);   
  
            throw new Exception("��������!");   
  
        }   
  
  
  
        index(i-1);   
  
        //�½ڵ��next��Ϊcurrent.next,��current��next��Ϊ�½ڵ�   
  
        current.setNext(new ListNode(obj,current.next));   
  
        size++;   
  
  
  
    }   
  
    //ɾ������   
  
    public Object delete(int i)throws Exception   
  
    {   
  
        if(size==0)   
  
        {   
  
            throw new Exception("����Ԫ���ѿ��޷�ɾ��");   
  
        }   
  
        if(i<0||i>size-1)   
  
        {   
  
            throw new Exception("��������!");   
  
        }   
  
        index(i-1);   
  
        Object obj=current.next.getElement();   
  
        //i-1���ڵ��next��ָ��i���ڵ��next��,��i�ڵ�����   
  
        current.setNext(current.next.next);   
  
        size--;   
  
        return obj;   
  
           
  
    }   
  
    //��ȡ����Ԫ��   
  
    public Object getData(int i)throws Exception   
  
    {   
  
        if(i<0||i>size)   
  
        {   
  
            throw new Exception("��������!");   
  
        }   
  
        index(i);   
  
        return current.getElement();   
  
    }   
  
    //��ȡԪ�ظ���   
  
    public int size()   
  
    {   
  
        return size;   
  
    }   
  
    //�ж��Ƿ�Ϊ��   
  
    public boolean isEmpty()   
  
    {   
  
        return size==0;   
  
    }   
  
    //�鲢����   
  
    public void LinListOfunion(ChainList aList,ChainList bList,ChainList cLinList)throws Exception   
  
    {   
  
        try {   
  
                int i,j,k,a,b;   
  
                i=j=1;   
  
                k=0;   
  
                int aLen=aList.size-1;   
  
                int bLen=bList.size-1;   
  
                Object aElement,bElement;   
  
                while((i<=aLen)&&(j<=bLen))   
  
                {   
  
                    aElement=aList.getData(i);   
  
                    bElement=bList.getData(j);   
  
                    if((aElement instanceof Integer)&&(bElement instanceof Integer))   
  
                    {   
  
                        Integer i1=(Integer)aElement;   
  
                        Integer i2=(Integer)bElement;   
  
                        a=i1.intValue();   
  
                        b=i2.intValue();   
  
                        if(a<b)   
  
                        {   
  
                            System.out.println ("a="+a);   
  
                            System.out.println ("b="+b);   
  
                            System.out.println ("k="+k);   
  
                            cLinList.insert(k,aElement);   
  
                            i++;   
  
                        }else  
  
                        {   
  
                            cLinList.insert(k,bElement);   
  
                            j++;   
  
                        }   
  
                           
  
                    }else  
  
                    {   
  
                        throw new Exception("����Integer�ֶ�");   
  
                    }   
  
                    System.out.println ("ִ�е���2");   
  
                    System.out.println ("i="+i+",aLen="+aLen);   
  
                    System.out.println ("j="+j+",bLen="+bLen);   
  
  
  
                }   
  
                   
  
                while(i<=aLen)   
  
                {   
  
                    aElement=aList.getData(i);   
  
                       
  
                    cLinList.insert(k,aElement);   
  
                }   
  
                while(j<=bLen)   
  
                {   
  
                    bElement=bList.getData(j);   
  
                    cLinList.insert(k,bElement);   
  
                }   
  
                for (int t = 0; t<cLinList.size; t++)   
  
                {   
  
                    System.out.print (cLinList.getData(t)+" ");   
  
                }   
  
               
  
        }   
  
        catch (Exception ex) {   
  
            ex.printStackTrace();   
  
        }   
  
       
  
    }   
  
    //������   
  
    public static void main(String[] args)   
  
    {   
  
        ChainList aLinList=new ChainList();   
  
        ChainList bLinList=new ChainList();   
  
        ChainList cLinList=new ChainList();   
  
        int n=10;   
  
        try {   
  
                for (int i=0; i<n; i++)   
  
                {   
  
                    aLinList.insert(i,new Integer(i+1));   
  
                }    
  
                for(int j=0;j<n;j++)   
  
                {   
  
                    bLinList.insert(j,new Integer(j+1));   
  
                }   
  
               // cLinList.LinListOfunion(aLinList,bLinList,cLinList);    
  
           
  
               // aLinList.delete(4);   
  
                  
  
                System.out.println ("aLinList���ֵ��");   
  
                for(int i=0;i<aLinList.size;i++)   
  
                {   
  
                    System.out.print (aLinList.getData(i)+" ");   
  
                }   
  
                System.out.println ();   
  
                System.out.println ("bLinList���ֵ��");   
  
                for (int i = 0; i<bLinList.size; i++)   
  
                {   
  
                    System.out.print (bLinList.getData(i)+" ");   
  
                }   
  
                System.out.println ();   
  
        }   
  
        catch (Exception ex) {   
  
            ex.printStackTrace();   
  
        }   
  
    }   
  
  
  
}   
  
  
  
  
  
  
  
class ListNode   
  
{   
  
    Object element;//����Ԫ��   
  
    ListNode next;//��һ���ڵ�Ķ�������   
  
       
  
    public ListNode(ListNode nextval)//����ͷ�ڵ�Ĺ��캯��   
  
    {   
  
        next=nextval;   
  
    }   
  
    public ListNode(Object obj,ListNode nextval)   
  
    {   
  
        element=obj;   
  
        next=nextval;   
  
    }   
  
    public ListNode getNext()//��ȡ�¸��ڵ�   
  
    {   
  
        return next;   
  
    }   
  
    public void setNext(ListNode nextval)//��next   
  
    {   
  
        next=nextval;   
  
    }   
  
    public Object getElement()//ȡelement   
  
    {   
  
        return element;   
  
    }   
  
    public void setElement(Object obj)//����Ԫ��   
  
    {   
  
        element=obj;   
  
    }   
  
    public String toString()//װ��elementΪString ����   
  
    {   
  
        return element.toString();   
  
    }   
  
}  

