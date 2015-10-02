package org.appfuse.common.util.datastructure;

/*  
 <A href="mailto:*@date:08-07-08">*@date:08-07-08</A>  
 <A href="mailto:*@descript">*@descript</A>:��ջ��ʵ����Ӧ��  
 **/
public class ChainStack {
	StackNode top;// ջ��ָ��

	public ChainStack()// �޲ι��캯��
	{
		top = null;
	}

	public boolean isEmpty()// �ж��Ƿ�Ϊ��
	{
		return top == null;
	}

	public void push(Object obj)// ��ջ
	{
		StackNode p = new StackNode(null);
		p.setElement(obj);
		p.setNext(top);// ���½��p������ջͷ��
		top = p;

	}

	public Object pop()// ��ջ
	{
		Object ret = null;
		StackNode p = new StackNode(null);
		if (!isEmpty())// �ж��Ƿ�Ϊ��ջ
		{
			p = top;// ����ջ��ָ��
			ret = p.getElement();// ����ջ������Ԫ��
			top = p.getNext();// ����ǰջ��ָ������ϲ���
		} else {
			System.out.println("ջΪ��!");
		}
		return ret;
	}

	public Object getTop()// ȡջ��Ԫ��
	{
		Object ret = null;
		if (!isEmpty()) {
			ret = top.getElement();
		} else {
			System.out.println("ջΪ��!");
		}
		return ret;
	}

	public static void main(String[] args) {
		ChainStack myStack = new ChainStack();
		int[] data = { 1, 3, 5, 7, 9, 11 };
		try {
			// ������ջ
			System.out.print("��ջԪ������Ϊ��");
			for (int i = 0; i < data.length; i++) {
				System.out.print(data[i] + " ");
				myStack.push(new Integer(data[i]));
			}
			System.out.println();
			System.out.print("��ǰջ��Ԫ��Ϊ��" + myStack.getTop());
			System.out.println();
			System.out.print("��ջԪ������Ϊ��");
			// ջ��Ԫ�س�ջ
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
	Object element;// ����Ԫ��

	StackNode next;// ��һ���ڵ�Ķ�������

	public StackNode(StackNode nextval)// ����ͷ�ڵ�Ĺ��캯��
	{
		next = nextval;
	}

	public StackNode(Object obj, StackNode nextval) {
		element = obj;
		next = nextval;
	}

	public StackNode getNext()// ��ȡ�¸��ڵ�
	{
		return next;
	}

	public void setNext(StackNode nextval)// ��next
	{
		next = nextval;
	}

	public Object getElement()// ȡelement
	{
		return element;
	}

	public void setElement(Object obj)// ����Ԫ��
	{
		element = obj;
	}

	public String toString()// װ��elementΪString ����
	{
		return element.toString();
	}
}
