package org.appfuse.common.util.datastructure;

/*  
 <A href="mailto:*@date:08-07-08">*@date:08-07-08</A>  
 <A href="mailto:*@descript">*@descript</A>:链栈的实现与应用  
 **/
public class ChainStack {
	StackNode top;// 栈顶指针

	public ChainStack()// 无参构造函数
	{
		top = null;
	}

	public boolean isEmpty()// 判断是否为空
	{
		return top == null;
	}

	public void push(Object obj)// 进栈
	{
		StackNode p = new StackNode(null);
		p.setElement(obj);
		p.setNext(top);// 将新结点p插入链栈头部
		top = p;

	}

	public Object pop()// 出栈
	{
		Object ret = null;
		StackNode p = new StackNode(null);
		if (!isEmpty())// 判断是否为空栈
		{
			p = top;// 保存栈顶指针
			ret = p.getElement();// 保存栈顶数据元素
			top = p.getNext();// 将当前栈顶指针从链上擦除
		} else {
			System.out.println("栈为空!");
		}
		return ret;
	}

	public Object getTop()// 取栈顶元素
	{
		Object ret = null;
		if (!isEmpty()) {
			ret = top.getElement();
		} else {
			System.out.println("栈为空!");
		}
		return ret;
	}

	public static void main(String[] args) {
		ChainStack myStack = new ChainStack();
		int[] data = { 1, 3, 5, 7, 9, 11 };
		try {
			// 数据入栈
			System.out.print("入栈元素序列为：");
			for (int i = 0; i < data.length; i++) {
				System.out.print(data[i] + " ");
				myStack.push(new Integer(data[i]));
			}
			System.out.println();
			System.out.print("当前栈顶元素为：" + myStack.getTop());
			System.out.println();
			System.out.print("出栈元素序列为：");
			// 栈内元素出栈
			while (!myStack.isEmpty()) {
				System.out.print(myStack.pop() + " ");
			}
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

class StackNode {
	Object element;// 数据元素

	StackNode next;// 下一个节点的对象引用

	public StackNode(StackNode nextval)// 用于头节点的构造函数
	{
		next = nextval;
	}

	public StackNode(Object obj, StackNode nextval) {
		element = obj;
		next = nextval;
	}

	public StackNode getNext()// 获取下个节点
	{
		return next;
	}

	public void setNext(StackNode nextval)// 置next
	{
		next = nextval;
	}

	public Object getElement()// 取element
	{
		return element;
	}

	public void setElement(Object obj)// 设置元素
	{
		element = obj;
	}

	public String toString()// 装换element为String 类型
	{
		return element.toString();
	}
}
