package org.appfuse.common.util.datastructure;

/*  
 *@date:08-07-06  
 
 *@descript:����ʵ����Ӧ��  
 
 **/  
  
public class TreeNode1   
  
{   
  
    public String data;//����Ԫ��   
  
    public TreeNode1 left,right;//ָ�����Һ��ӵ���   
  
       
  
       
  
    public TreeNode1()//�޲ι��캯��   
  
    {   
  
        this("?");     
  
    }   
  
    public TreeNode1(String d)   
  
    {   
  
        data=d;   
  
        left=right=null;   
  
    }   
  
    public void preorder(TreeNode1 p)//�ȸ��������������    
  
    {   
  
        if(p!=null)   
  
        {   
  
            System.out.print (p.data+" ");   
  
            preorder(p.left);   
  
            preorder(p.right);   
  
        }   
  
    }   
  
    public void inorder(TreeNode1 p)//�и��������������   
  
    {   
  
        if(p!=null)   
  
        {   
  
            inorder(p.left);   
  
            System.out.print (p.data+" ");   
  
            inorder(p.right);              
  
        }   
  
    }   
  
    public void postorder(TreeNode1 p)   
  
    {   
  
        if(p!=null)   
  
        {   
  
            postorder(p.left);   
  
            postorder(p.right);   
  
            System.out.print (p.data+" ");   
  
        }   
  
    }   
  
}   
  
  
  
class Tree1   
  
{   
  
    protected TreeNode1 root;//���ڵ�   
  
       
  
    public Tree1()   
  
    {   
  
        root=null;   
  
    }   
  
    public Tree1(String prestr,String instr)//���캯��   
  
    {   
  
        root=enter(prestr,instr);   
  
    }   
  
    public void preorderTraversal()   
  
    {   
  
        System.out.print("�ȸ�����:");   
  
        if(root!=null)   
  
        {   
  
            root.preorder(root);   
  
        }   
  
        System.out.println ();   
  
    }   
  
    public void inorderTraversal()   
  
    {   
  
        System.out.print("�и�����:");   
  
        if(root!=null)   
  
        {   
  
            root.inorder(root);   
  
        }   
  
        System.out.println ();   
  
    }   
  
    public void postorderTraversal()   
  
    {   
  
        System.out.print("�������:");   
  
        if(root!=null)   
  
        {   
  
            root.postorder(root);   
  
        }   
  
        System.out.println ();   
  
    }   
  
       
  
    public TreeNode1 enter(String prestr,String instr)   
  
    {   
  
        TreeNode1 p=null;   
  
        int k,n;   
  
        String first,presub,insub;   
  
        n=prestr.length();   
  
        if(n>0)   
  
        {   
  
            System.out.print("prestr="+prestr+"\t instr="+instr);   
  
            first=prestr.substring(0,1);//ȡ���ĵ�1���ַ���Ϊ��   
  
            p=new TreeNode1(first);//�����ڵ�   
  
            k=instr.indexOf(first);//ȷ�������и������е�λ��   
  
            System.out.println ("\t first="+first+"\t k="+k);   
  
            presub=prestr.substring(1,k+1);   
  
            insub=instr.substring(0,k);   
  
            p.left=enter(presub,insub);//����������   
  
            presub=prestr.substring(k+1,n);   
  
            insub=instr.substring(k+1,n);   
  
            p.right=enter(presub,insub);//����������   
  
        }   
  
        return p;   
  
    }   
  
       
  
    public static void main(String[] args)   
  
    {   
  
        String prestr="ABDGCEFH";   
  
        String instr="DGBAECHF";   
  
        if(args.length>1)   
  
        {   
  
            prestr=args[0];   
  
            instr=args[1];   
  
        }   
  
        Tree1 t1=new Tree1(prestr,instr);   
  
        t1.preorderTraversal();   
  
        t1.inorderTraversal();   
  
        t1.postorderTraversal();   
  
    }   
  
}  

