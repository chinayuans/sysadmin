package org.appfuse.common.util.datastructure;

/*  

 *@date:08-07-06  
 
 *@descript:˳����ʵ����Ӧ��  
 
 **/  
  
public class SequentialList    
  
{   
  
    final int defaultSize=10;   
  
    int maxSize;//�����󳤶�   
  
    int size;//��ĵ�ǰ����   
  
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
  
       
  
    //����   
  
    public void insert(int i,Object obj)throws Exception   
  
    {   
  
        if(size==maxSize)   
  
        {   
  
            throw new Exception("˳����������޷�����!");   
  
        }      
  
        if(i<0||i>size)   
  
        {   
  
            throw new Exception("��������!");   
  
        }   
  
        for(int j=size;j>i;j--)   
  
        {   
  
            listArray[j]=listArray[j-1];//����λ�ú��Ԫ������   
  
        }   
  
        listArray[i]=obj;   
  
        size++;//����һ   
  
    }   
  
       
  
    //ɾ��   
  
    public Object delete(int i) throws Exception   
  
    {   
  
        if(size==0)   
  
        {   
  
            throw new Exception("˳����ѿ��޷�ɾ��!");   
  
        }   
  
        Object it=listArray[i];   
  
        for(int j=i;j<size-1;j++)   
  
        {   
  
            listArray[j]=listArray[j+1];//Ԫ������   
  
        }   
  
        size--;//����һ   
  
        return it;   
  
    }   
  
    //��ȡԪ��   
  
    public Object getData(int i)throws Exception   
  
    {   
  
        if(i<0||i>size)   
  
        {   
  
            throw new Exception("��������!");   
  
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
  
    //������   
  
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
  
            //ɾ���±�Ϊ4��Ԫ��   
  
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
