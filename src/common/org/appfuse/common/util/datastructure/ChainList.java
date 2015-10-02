package org.appfuse.common.util.datastructure;




/* 
*@date:08-07-06  
  
 *@descript:单链表的实现与应用   
  
 **/   
  
public class ChainList   
  
{   
  
    ListNode head;//头指针   
  
    ListNode current;//当前节点位置   
  
    int size;//数据元素个数   
  
       
  
       
  
    public ChainList()//构造函数   
  
    {   
  
        head=current=new ListNode(null);   
  
        size=0;   
  
    }   
  
       
  
    //定位函数   
  
    public void index(int i) throws Exception   
  
    {   
  
        if(i<-1||i>size-1)   
  
        {   
  
            throw new Exception("参数错误!");   
  
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
  
    //插入函数   
  
    public void insert(int i,Object obj)throws Exception   
  
    {   
  
        if(i<0||i>size)   
  
        {   
  
               
  
            System.out.println ("size="+size);   
  
            System.out.println ("i="+i);   
  
            throw new Exception("参数错误!");   
  
        }   
  
  
  
        index(i-1);   
  
        //新节点的next域为current.next,而current的next域为新节点   
  
        current.setNext(new ListNode(obj,current.next));   
  
        size++;   
  
  
  
    }   
  
    //删除函数   
  
    public Object delete(int i)throws Exception   
  
    {   
  
        if(size==0)   
  
        {   
  
            throw new Exception("链表元素已空无法删除");   
  
        }   
  
        if(i<0||i>size-1)   
  
        {   
  
            throw new Exception("参数错误!");   
  
        }   
  
        index(i-1);   
  
        Object obj=current.next.getElement();   
  
        //i-1个节点的next域指向i个节点的next域,让i节点脱链   
  
        current.setNext(current.next.next);   
  
        size--;   
  
        return obj;   
  
           
  
    }   
  
    //获取数据元素   
  
    public Object getData(int i)throws Exception   
  
    {   
  
        if(i<0||i>size)   
  
        {   
  
            throw new Exception("参数错误!");   
  
        }   
  
        index(i);   
  
        return current.getElement();   
  
    }   
  
    //获取元素个数   
  
    public int size()   
  
    {   
  
        return size;   
  
    }   
  
    //判断是否为空   
  
    public boolean isEmpty()   
  
    {   
  
        return size==0;   
  
    }   
  
    //归并函数   
  
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
  
                        throw new Exception("不是Integer字段");   
  
                    }   
  
                    System.out.println ("执行到这2");   
  
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
  
    //主函数   
  
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
  
                  
  
                System.out.println ("aLinList表的值：");   
  
                for(int i=0;i<aLinList.size;i++)   
  
                {   
  
                    System.out.print (aLinList.getData(i)+" ");   
  
                }   
  
                System.out.println ();   
  
                System.out.println ("bLinList表的值：");   
  
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
  
    Object element;//数据元素   
  
    ListNode next;//下一个节点的对象引用   
  
       
  
    public ListNode(ListNode nextval)//用于头节点的构造函数   
  
    {   
  
        next=nextval;   
  
    }   
  
    public ListNode(Object obj,ListNode nextval)   
  
    {   
  
        element=obj;   
  
        next=nextval;   
  
    }   
  
    public ListNode getNext()//获取下个节点   
  
    {   
  
        return next;   
  
    }   
  
    public void setNext(ListNode nextval)//置next   
  
    {   
  
        next=nextval;   
  
    }   
  
    public Object getElement()//取element   
  
    {   
  
        return element;   
  
    }   
  
    public void setElement(Object obj)//设置元素   
  
    {   
  
        element=obj;   
  
    }   
  
    public String toString()//装换element为String 类型   
  
    {   
  
        return element.toString();   
  
    }   
  
}  

