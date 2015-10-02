package org.appfuse.common.util.descartes;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * ʵ�ֵѿ����˻��������������
 *  {n1,n2...}*{m1,m2,m3..}*{p1,p2,p3...}*....
 *  ģ�ͷǳ����������ȸ������㷨��ͼ��������������㷨(��Щ��ǿ),���������:
 *     {n1,n2}*{m1,m2,m3}*{p1,p2,p3}
 *  ��ô����ģ����:
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
 * ��������Կ�����root����,���ʳ�����ģ�;���һ����,root��ʾ��һ�β��.
 * ÿ�δ����ĵڶ���ڵ����������Ҷ�ӽڵ����,�����ʹ��Ľڵ��¼����һ���ѿ����˻�������.
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
         * �������ܲ����>1ʱ,ע�������ܲ����=root.size()-1.
         */
        if(root.size()>0){
        	/**
        	 * �õ��ڶ���������е����ڵ�.
        	 */
            ArrayList nodeList = (ArrayList) root.get(0);
            
            /**
             * stack��Ҫ������¼��һ�δ����ĵڶ���ڵ㵽����Ҷ�ӽڵ��ʱ�������ʹ������нڵ�.
             */
            Stack<String> stack= new Stack();
            
            /**
             * �����ĵڶ���ο�ʼ�������������.
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
     * ���������Ҫ����ʵ������for��Ƕ��ģ��:
     * for()
     *    for()
     *       for()
     *         for()
     *           ...
     * @param root  ��ʾ������
     * @param treeDepthCounter  ��¼�ڼ����,0��ʾ��һ���,1��ʾ�ڶ����,...
     * @param nodeList  ��¼�ڸ����Ĳ�������нڵ�,�ͱ���ڶ�������нڵ���{n1,n2}
     * @param stack     ��Ҫ������¼��һ�δ����ĵڶ���ڵ㵽����Ҷ�ӽڵ��ʱ�������ʹ������нڵ�.
     */
    public static void traverse(ArrayList root,int treeDepthCounter,ArrayList nodeList, Stack stack){
        for (int i = 0; i < nodeList.size(); i++) {
        	/**
        	 * ȡ��һ�����ڵ�
        	 */
            Object element = nodeList.get(i);
            
            /**
             * ���ʸò��е�һ���ڵ�,��¼�ýڵ�
             */
            stack.push(element);
            
            /**
             * root.size()=��������ӵ�еĲ����-1
             * treeDepthCounter >= root.size()-1 ,��ʾ�Ѿ���������Ҷ�ӽڵ�.
             */
            if( treeDepthCounter >= root.size()-1) {
                /**
                 * �����δ����ĵڶ���ڵ㵽����Ҷ�ӽڵ��ʱ�������
                 * ��ô��ӡ�˴������з��ʵ����нڵ�,stack����ôη��ʹ������нڵ�
                 */
                Object[] one_traverse=new Object[root.size()];
                
                /**
                 * ȡ���ʹ�ӡ��ǰstack�е����м�¼,���ǲ��ƻ�stack�е����м�¼.
                 */
                stack.copyInto(one_traverse);
                printArrayList(one_traverse);
            }else {
            	/**
            	 * ��ʼ������һ��ε���,nextDeepCounterΪ��һ��εĲ����.
            	 */
                int nextDeepCounter=treeDepthCounter+1;
                
                /**
                 * �õ���һ��������е����ڵ�,�����3����ϵ����ڵ���{m1,m2,m3}.
                 */
                ArrayList nextNodeList = (ArrayList) root.get(nextDeepCounter);
                /**
                 * ��ʼ������һ��ε����ڵ�.
                 */
                traverse(root,nextDeepCounter,nextNodeList,stack);    
            }
            
            /**
             * ���ýڵ���ʽ���ʱ,�Ӽ�¼��ɾ���ýڵ�.
             */
            stack.pop();
        }
    }
}