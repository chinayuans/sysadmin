package org.appfuse.common.util.datastructure;

/* 

 *@date:08-07-11 
 
 *@descript:折半查找，插入跟删除算法 
 
 **/  
  
public class BinaryInsertEX extends SequentialList  
  
{  
  
    public BinaryInsertEX()  
  
    {  
  
        this(0);  
  
    }  
  
    public BinaryInsertEX(int n)  
  
    {  
  
        super(n);  
  
        create();  
  
    }  
  
    public void create()  
  
    {  
  
        try{  
  
            this.insert(0,new Integer(3));  
  
            this.insert(1,new Integer(4));  
  
            this.insert(2,new Integer(6));  
  
            this.insert(3,new Integer(9));  
  
            this.insert(4,new Integer(19));  
  
        }  
  
        catch (Exception ex){  
  
            ex.printStackTrace();  
  
        }  
  
    }  
  
    public void display()  
  
    {  
  
        StringBuffer str=new StringBuffer("当前线性表的关键字列表:{");  
  
        try{  
  
            for (int i = 0; i<this.size(); i++)  
  
            {  
  
                str.append(((Integer)this.getData(i)).toString());  
  
                str.append(",");  
  
            }  
  
              
  
              
  
           }  
  
           catch (Exception ex){  
  
            ex.printStackTrace();  
  
           }  
  
        str.append("}");  
  
        System.out.println (str.toString());  
  
    }  
  
    //折半查找  
  
    public int search(int key)  
  
    {  
  
        int left,right,mid,ret;  
  
        ret=-1;  
  
        left=0;  
  
        right=this.size-1;  
  
        try{  
  
            while(left<=right)  
  
            {  
  
                mid=(left+right)/2;  
  
                //当key大于中间元素时，将查找区间缩小为后半部分  
  
                if(key>((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    left=mid+1;  
  
                //当key小于中间元素时，将查找区间缩小为前半部分  
  
                }else if(key<((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    right=mid-1;  
  
                //当两值相等时，保存该元素的位置信息并结束查找  
  
                }else  
  
                {  
  
                    ret=mid;  
  
                    break;  
  
                }  
  
            }  
  
          
  
        }  
  
        catch (Exception ex){  
  
            ex.printStackTrace();  
  
        }  
  
        return ret;  
  
    }  
  
    //折半插入  
  
    public void insert(int key)  
  
    {  
  
        int left,right,mid=0;  
  
        left=0;  
  
        right=this.size-1;  
  
        try{  
  
            while(left<=right)  
  
            {  
  
                mid=(left+right)/2;  
  
                if(key>((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    left=mid+1;  
  
                }else if(key<((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    right=mid-1;  
  
                }else  
  
                {  
  
                    break;  
  
                }  
  
            }  
  
            int i=this.size-1;  
  
            while(i>=mid)  
  
            {  
  
                this.listArray[i+1]=this.listArray[i];  
  
                i--;  
  
            }  
  
            this.listArray[mid]=new Integer(key);  
  
            this.size++;  
  
              
  
        }  
  
        catch (Exception ex){  
  
            ex.printStackTrace();  
  
        }  
  
    }  
  
    //折半删除  
  
    public Object delete(int key)  
    {  
        int left,right,mid=0;  
  
        int find=0;  
  
        left=0;  
  
        right=this.size-1;  
  
        try{  
  
            while(left<=right)  
  
            {  
  
                mid=(left+right)/2;  
  
                if(key>((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    left=mid+1;  
  
                }else if(key<((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    right=mid-1;  
  
                }else  
  
                {  
  
                    System.out.println ("表中有该数据!");  
  
                    break;  
  
                }  
  
            }  
  
            int i=mid;  
  
            find= ((Integer)this.listArray[mid]).intValue();  
  
            this.listArray[mid]=null;  
  
            while(i<this.size)  
  
            {  
  
                this.listArray[i]=this.listArray[i+1];  
  
                i++;  
  
            }  
  
            this.size--;  
   
        }  
  
        catch (Exception ex){  
  
            ex.printStackTrace();  
  
        }  
  
        return this.listArray[find];  
  
    }  
  
    public static void main(String[] args)  
  
    {  
  
        int n=9,k=8;  
  
        BinaryInsertEX bi=new BinaryInsertEX(n);  
  
        System.out.println ("原始数据序列: ");  
  
        bi.display();  
  
        System.out.println ("--------------------------------------");  
  
        System.out.println ("查找数据："+k);  
  
        int i=bi.search(k);  
  
        if(i!=-1)  
  
        {  
  
            System.out.println ("所查找的数据位置为: "+i);  
  
        }else  
  
        {  
  
            System.out.println ("没有找到该数据!");  
  
        }  
  
          
  
        System.out.println ("--------------------------------------");  
  
        System.out.println ("插入前的线性表列表如下: ");  
  
        bi.display();  
  
        System.out.println ("插入数据："+k);  
  
        bi.insert(k);  
  
        System.out.println ("插入后的线性表列表如下: ");  
  
        bi.display();  
  
        System.out.println ("--------------------------------------");  
  
        System.out.println ("删除前的线性表列表如下: ");  
  
        bi.display();  
  
        System.out.println ("删除数据："+k);  
  
        bi.delete(k);  
  
        System.out.println ("删除后的线性表列表如下: ");  
  
        bi.display();  
  
        System.out.println ("--------------------------------------");  
  
    }  
  
}  