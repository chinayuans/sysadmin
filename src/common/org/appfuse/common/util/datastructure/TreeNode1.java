package org.appfuse.common.util.datastructure;

/*  
 *@date:08-07-06  
 
 *@descript:树的实现与应用  
 
 **/  
  
public class TreeNode1   
  
{   
  
    public String data;//数据元素   
  
    public TreeNode1 left,right;//指向左，右孩子的链   
  
       
  
       
  
    public TreeNode1()//无参构造函数   
  
    {   
  
        this("?");     
  
    }   
  
    public TreeNode1(String d)   
  
    {   
  
        data=d;   
  
        left=right=null;   
  
    }   
  
    public void preorder(TreeNode1 p)//先根次序遍历二叉树    
  
    {   
  
        if(p!=null)   
  
        {   
  
            System.out.print (p.data+" ");   
  
            preorder(p.left);   
  
            preorder(p.right);   
  
        }   
  
    }   
  
    public void inorder(TreeNode1 p)//中根次序遍历二叉树   
  
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
  
    protected TreeNode1 root;//根节点   
  
       
  
    public Tree1()   
  
    {   
  
        root=null;   
  
    }   
  
    public Tree1(String prestr,String instr)//构造函数   
  
    {   
  
        root=enter(prestr,instr);   
  
    }   
  
    public void preorderTraversal()   
  
    {   
  
        System.out.print("先根次序:");   
  
        if(root!=null)   
  
        {   
  
            root.preorder(root);   
  
        }   
  
        System.out.println ();   
  
    }   
  
    public void inorderTraversal()   
  
    {   
  
        System.out.print("中根次序:");   
  
        if(root!=null)   
  
        {   
  
            root.inorder(root);   
  
        }   
  
        System.out.println ();   
  
    }   
  
    public void postorderTraversal()   
  
    {   
  
        System.out.print("后根次序:");   
  
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
  
            first=prestr.substring(0,1);//取串的第1个字符作为根   
  
            p=new TreeNode1(first);//建立节点   
  
            k=instr.indexOf(first);//确定根在中根序列中的位置   
  
            System.out.println ("\t first="+first+"\t k="+k);   
  
            presub=prestr.substring(1,k+1);   
  
            insub=instr.substring(0,k);   
  
            p.left=enter(presub,insub);//建立左子树   
  
            presub=prestr.substring(k+1,n);   
  
            insub=instr.substring(k+1,n);   
  
            p.right=enter(presub,insub);//建立右子树   
  
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

