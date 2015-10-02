package org.appfuse.common.util.descartes;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * 实现笛卡尔乘积运算结果的输出：
 *  {n1,n2...}*{m1,m2,m3..}*{p1,p2,p3...}*....
 *  模型非常类似树的先根遍历算法或图的深度优先搜索算法(有些勉强),如果输入是:
 *     {n1,n2}*{m1,m2,m3}*{p1,p2,p3}
 *  那么搜索模型是:
 *   root-->n1--->m1--->p1
 *                  --->p2
 *                  --->p3
 *            --->m2--->p1
 *                  --->p2
 *                  --->p3
 *            --->m3--->p1
 *                  --->p2
 *                  --->p3
 *   root-->n2--->m1--->p1
 *                  --->p2
 *                  --->p3
 *            --->m2--->p1
 *                  --->p2
 *                  --->p3
 *            --->m3--->p1
 *                  --->p2
 *                  --->p3
 * 从上面可以看到从root出发,访问出来的模型就是一颗树,root表示第一次层次.
 * 每次从树的第二层节点出发到树的叶子节点结束,所访问过的节点记录就是一个笛卡尔乘积运算结果.
 * 
 */
public class DescartesMultiplication {
     
    public static void main(String[] args) {
        
        //initiate all of data:
        ArrayList list1= new ArrayList();
        ArrayList list2= new ArrayList();
        ArrayList list3= new ArrayList();
        ArrayList list4= new ArrayList();
        ArrayList root= new ArrayList();
        
//        list1.add("1");
//        list2.add("2");
//        list2.add("3");
//        list3.add("4");
//        list3.add("5");
//        list3.add("6");
//        list3.add("7");
//        list4.add("8");
//        list4.add("9");
        
        list1.add(1);
        list2.add(2);
        list2.add("1");
        list2.add(3.3);
        list3.add(4.4);
        list3.add(5);
        list3.add(6.5);
        list3.add(7.0);
        list4.add(8.2);
        list4.add(9.1);
        list4.add(new Object());
        
        root.add(list1);
        root.add(list2);
        root.add(list3);
        root.add(list4);
        
        //print all of data:
        for (int i = 0; i < root.size(); i++) {
            ArrayList list=new ArrayList();
            ArrayList item = (ArrayList) root.get(i);
            for (int j = 0; j < item.size(); j++) {
                Object element =  item.get(j);
                list.add(element);
            }
            printArrayList(list);
        }
        
        //begin to a depth-first search traverse from the root node.
        System.out.println("-----Below is a result of DescartesMultiplication-----");
        
        /**
         * 当树的总层次数>1时,注意树的总层次数=root.size()-1.
         */
        if(root.size()>0){
        	/**
        	 * 得到第二层次上所有的树节点.
        	 */
            ArrayList nodeList = (ArrayList) root.get(0);
            
            /**
             * stack主要用来记录，一次从树的第二层节点到树的叶子节点的时候，所访问过的所有节点.
             */
            Stack<String> stack= new Stack();
            
            /**
             * 从树的第二层次开始先序遍历整颗树.
             */
            traverse(root,0,nodeList,stack);
        }
    }
    
    public static void printArrayList(ArrayList list){
       StringBuffer sb=new StringBuffer();
       sb.append("{");
       for (int i = 0; i < list.size(); i++) {
           sb.append(list.get(i).toString()+",");
       }
       sb.deleteCharAt(sb.length()-1);
       sb.append("}");
       System.out.println(sb.toString());
    }
    
    public static void printArrayList(Object[] array){
        StringBuffer sb=new StringBuffer();
        sb.append("{");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].toString()+",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        System.out.println(sb.toString());
     }
    
    /**
     * 这个方法主要用来实现这种for的嵌套模型:
     * for()
     *    for()
     *       for()
     *         for()
     *           ...
     * @param root  表示整颗树
     * @param treeDepthCounter  记录第几层次,0表示第一层次,1表示第二层次,...
     * @param nodeList  记录在该树的层次上所有节点,就比如第二层的所有节点是{n1,n2}
     * @param stack     主要用来记录，一次从树的第二层节点到树的叶子节点的时候，所访问过的所有节点.
     */
    public static void traverse(ArrayList root,int treeDepthCounter,ArrayList nodeList, Stack stack){
        for (int i = 0; i < nodeList.size(); i++) {
        	/**
        	 * 取得一个树节点
        	 */
            Object element = nodeList.get(i);
            
            /**
             * 访问该层中的一个节点,记录该节点
             */
            stack.push(element);
            
            /**
             * root.size()=整颗树所拥有的层次数-1
             * treeDepthCounter >= root.size()-1 ,表示已经到达树的叶子节点.
             */
            if( treeDepthCounter >= root.size()-1) {
                /**
                 * 如果这次从树的第二层节点到树的叶子节点的时候结束，
                 * 那么打印此次搜索中访问的所有节点,stack保存该次访问过的所有节点
                 */
                Object[] one_traverse=new Object[root.size()];
                
                /**
                 * 取出和打印当前stack中的所有记录,但是不破坏stack中的所有记录.
                 */
                stack.copyInto(one_traverse);
                printArrayList(one_traverse);
            }else {
            	/**
            	 * 开始访问下一层次的树,nextDeepCounter为下一层次的层次数.
            	 */
                int nextDeepCounter=treeDepthCounter+1;
                
                /**
                 * 得到下一层次上所有的树节点,比如第3层次上的树节点是{m1,m2,m3}.
                 */
                ArrayList nextNodeList = (ArrayList) root.get(nextDeepCounter);
                /**
                 * 开始访问下一层次的树节点.
                 */
                traverse(root,nextDeepCounter,nextNodeList,stack);    
            }
            
            /**
             * 当该节点访问结束时,从记录中删除该节点.
             */
            stack.pop();
        }
    }
}