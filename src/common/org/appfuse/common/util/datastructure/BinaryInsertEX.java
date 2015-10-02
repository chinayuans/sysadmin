package org.appfuse.common.util.datastructure;

/* 

 *@date:08-07-11 
 
 *@descript:�۰���ң������ɾ���㷨 
 
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
  
        StringBuffer str=new StringBuffer("��ǰ���Ա�Ĺؼ����б�:{");  
  
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
  
    //�۰����  
  
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
  
                //��key�����м�Ԫ��ʱ��������������СΪ��벿��  
  
                if(key>((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    left=mid+1;  
  
                //��keyС���м�Ԫ��ʱ��������������СΪǰ�벿��  
  
                }else if(key<((Integer)this.getData(mid)).intValue())  
  
                {  
  
                    right=mid-1;  
  
                //����ֵ���ʱ�������Ԫ�ص�λ����Ϣ����������  
  
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
  
    //�۰����  
  
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
  
    //�۰�ɾ��  
  
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
  
                    System.out.println ("�����и�����!");  
  
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
  
        System.out.println ("ԭʼ��������: ");  
  
        bi.display();  
  
        System.out.println ("--------------------------------------");  
  
        System.out.println ("�������ݣ�"+k);  
  
        int i=bi.search(k);  
  
        if(i!=-1)  
  
        {  
  
            System.out.println ("�����ҵ�����λ��Ϊ: "+i);  
  
        }else  
  
        {  
  
            System.out.println ("û���ҵ�������!");  
  
        }  
  
          
  
        System.out.println ("--------------------------------------");  
  
        System.out.println ("����ǰ�����Ա��б�����: ");  
  
        bi.display();  
  
        System.out.println ("�������ݣ�"+k);  
  
        bi.insert(k);  
  
        System.out.println ("���������Ա��б�����: ");  
  
        bi.display();  
  
        System.out.println ("--------------------------------------");  
  
        System.out.println ("ɾ��ǰ�����Ա��б�����: ");  
  
        bi.display();  
  
        System.out.println ("ɾ�����ݣ�"+k);  
  
        bi.delete(k);  
  
        System.out.println ("ɾ��������Ա��б�����: ");  
  
        bi.display();  
  
        System.out.println ("--------------------------------------");  
  
    }  
  
}  