package org.appfuse.common.util.datastructure;

/*  

 *@date:08-07-06  
 
 *@descript:顺序表的实现与应用  
 
 **/  
  
public class SequentialList    
  
{   
  
    final int defaultSize=10;   
  
    int maxSize;//表的最大长度   
  
    int size;//表的当前长度   
  
    Object[] listArray;   
  
    public SequentialList()   
  
    {   
  
        initiate(defaultSize);   
  
    }   
  
    public SequentialList(int size)   
  
    {   
  
        initiate(size);   
  
    }   
  
    public void initiate(int sz)   
  
    {   
  
        maxSize=sz;   
  
        size=0;   
  
        listArray=new Object[sz];   
  
    }   
  
       
  
    //插入   
  
    public void insert(int i,Object obj)throws Exception   
  
    {   
  
        if(size==maxSize)   
  
        {   
  
            throw new Exception("顺序表已满，无法插入!");   
  
        }      
  
        if(i<0||i>size)   
  
        {   
  
            throw new Exception("参数错误!");   
  
        }   
  
        for(int j=size;j>i;j--)   
  
        {   
  
            listArray[j]=listArray[j-1];//插入位置后的元素右移   
  
        }   
  
        listArray[i]=obj;   
  
        size++;//表长加一   
  
    }   
  
       
  
    //删除   
  
    public Object delete(int i) throws Exception   
  
    {   
  
        if(size==0)   
  
        {   
  
            throw new Exception("顺序表已空无法删除!");   
  
        }   
  
        Object it=listArray[i];   
  
        for(int j=i;j<size-1;j++)   
  
        {   
  
            listArray[j]=listArray[j+1];//元素左移   
  
        }   
  
        size--;//表长减一   
  
        return it;   
  
    }   
  
    //获取元素   
  
    public Object getData(int i)throws Exception   
  
    {   
  
        if(i<0||i>size)   
  
        {   
  
            throw new Exception("参数错误!");   
  
        }   
  
        return listArray[i];   
  
    }   
  
    public int size()   
  
    {   
  
        return size;   
  
    }   
  
    public boolean isEmpty()   
  
    {   
  
        return size==0;   
  
    }   
  
    public int MoreDataDelect(SequentialList L,Object x)throws Exception   
  
    {   
  
        int i,j;   
  
        int tag=0;   
  
        for(i=0;i<L.size;i++)   
  
        {   
  
            if(x.equals(L.getData(i)))   
  
            {   
  
                L.delete(i);   
  
                size--;   
  
                tag=1;   
  
            }   
  
        }   
  
        return tag;   
  
    }   
  
    public int locate(Object obj)   
  
    {   
  
        for(int i=0;i<size;i++)   
  
        {   
  
            if(listArray[i].equals(obj))   
  
            {   
  
                return i;   
  
            }   
  
        }   
  
        return -1;   
  
    }   
  
    //逆序存放   
  
    public void backformation (SequentialList L)   
  
    {   
  
        Object obj;   
  
        int j=L.size-1;   
  
        for (int i = 0; i<L.size/2; i++)   
  
        {   
  
            obj=listArray[i];   
  
            listArray[i]=listArray[j-i];   
  
            listArray[j-i]=obj;   
  
        }   
  
           
  
    }   
  
  
  
    public static void main(String[] args)   
  
    {   
        SequentialList seqList=new SequentialList(100);   
  
        int n=10;   
  
        try  
  
        {   
  
            for(int i=0;i<n;i++)   
  
            {   
  
                seqList.insert(i,new Integer(i+1));   
  
            }   
  
            //删除下标为4的元素   
  
            seqList.delete(4);   
  
            for(int i=0;i<seqList.size;i++)   
  
            {   
  
                System.out.println (seqList.getData(i)+" ");   
  
            }   
  
        }catch(Exception e)   
  
        {   
  
            e.printStackTrace();   
  
        }   
    }   
  
}  
