package org.appfuse.common.util.datastructure;

/*  
 * @date:08-07-08  
 * @descript:�����е�ʵ����Ӧ��  ������.
 **/

public class ChainQueue {
	private QueueNode front;// ��ͷ

	private QueueNode rear;// ��β

	public ChainQueue()// ���캯�����ÿն���
	{
		front = rear = null;
	}

	public boolean isEmpty()// �ж��Ƿ�Ϊ�ն���
	{
		return front == null && rear == null;
	}

	public void EnQueue(Object obj)// ���
	{
		QueueNode p = new QueueNode();
		p.setElement(obj);
		p.setNext(null);
		if (isEmpty())// �ж��Ƿ�Ϊ��
		{
			front = rear = p;// �����ݲ��뵽�ն���
		} else {
			rear.setNext(p);// ��P������β�ڵ��
			rear = p;// ��βָ��ָ���µĶ�β
		}
	}

	public Object DeQueue() {
		Object ret = null;
		QueueNode p = null;
		if (!isEmpty()) {
			p = front;
			ret = p.getElement();
			front = p.getNext();
			if (rear == p)// ���ԭ����ֻ��һ���ڵ㣬ɾ��������
			{
				rear = null;
			}
		} else {
			System.out.println("����Ϊ��!");
		}
		return ret;
	}

	public Object getHead() {
		Object ret = null;
		if (!isEmpty()) {
			ret = front.getElement();
		} else {
			System.out.println("����Ϊ��!");
		}
		return ret;
	}

	public static void main(String[] args) {
		ChainQueue myQueue = new ChainQueue();// ��ʼ�����еĿռ��СΪ 5
		int[] data = { 1, 3, 5, 7, 9, 11 };
		try {
			System.out.print("���Ԫ������Ϊ��");
			// ���������
			for (int i = 0; i < data.length; i++) {
				myQueue.EnQueue(new Integer(data[i]));
				System.out.print(data[i] + " ");
			}
			System.out.println("");
			System.out.println("��ǰ��ͷԪ��Ϊ��" + myQueue.getHead());
			System.out.print("����Ԫ������Ϊ��");
			// �����ݳ���
			while (!myQueue.isEmpty()) {
				System.out.print(myQueue.DeQueue() + " ");
			}
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

class QueueNode {
	Object element = null;// ����Ԫ��

	QueueNode next = null;// ��һ���ڵ�Ķ�������

	public QueueNode()// ����ͷ�ڵ�Ĺ��캯��
	{
	}

	public QueueNode getNext()// ��ȡ�¸��ڵ�
	{
		return next;
	}

	public void setNext(QueueNode nextval)// ��next
	{
		next = nextval;
	}

	public Object getElement()// ȡelement
	{
		return element;
	}

	public void setElement(Object _element)// ����Ԫ��
	{
		element = _element;
	}

	public String toString()// װ��elementΪString ����
	{
		return element.toString();
	}
}
