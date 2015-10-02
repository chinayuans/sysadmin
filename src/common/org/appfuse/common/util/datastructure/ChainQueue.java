package org.appfuse.common.util.datastructure;

/*  
 * @date:08-07-08  
 * @descript:链队列的实现与应用  有问题.
 **/

public class ChainQueue {
	private QueueNode front;// 队头

	private QueueNode rear;// 对尾

	public ChainQueue()// 构造函数，置空队列
	{
		front = rear = null;
	}

	public boolean isEmpty()// 判断是否为空队列
	{
		return front == null && rear == null;
	}

	public void EnQueue(Object obj)// 入队
	{
		QueueNode p = new QueueNode();
		p.setElement(obj);
		p.setNext(null);
		if (isEmpty())// 判断是否为空
		{
			front = rear = p;// 将数据插入到空队列
		} else {
			rear.setNext(p);// 将P链到队尾节点后
			rear = p;// 队尾指针指向新的队尾
		}
	}

	public Object DeQueue() {
		Object ret = null;
		QueueNode p = null;
		if (!isEmpty()) {
			p = front;
			ret = p.getElement();
			front = p.getNext();
			if (rear == p)// 如果原队中只有一个节点，删除后则变空
			{
				rear = null;
			}
		} else {
			System.out.println("队列为空!");
		}
		return ret;
	}

	public Object getHead() {
		Object ret = null;
		if (!isEmpty()) {
			ret = front.getElement();
		} else {
			System.out.println("队列为空!");
		}
		return ret;
	}

	public static void main(String[] args) {
		ChainQueue myQueue = new ChainQueue();// 初始化队列的空间大小为 5
		int[] data = { 1, 3, 5, 7, 9, 11 };
		try {
			System.out.print("入队元素序列为：");
			// 将数据入队
			for (int i = 0; i < data.length; i++) {
				myQueue.EnQueue(new Integer(data[i]));
				System.out.print(data[i] + " ");
			}
			System.out.println("");
			System.out.println("当前队头元素为：" + myQueue.getHead());
			System.out.print("出队元素序列为：");
			// 将数据出队
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
	Object element = null;// 数据元素

	QueueNode next = null;// 下一个节点的对象引用

	public QueueNode()// 用于头节点的构造函数
	{
	}

	public QueueNode getNext()// 获取下个节点
	{
		return next;
	}

	public void setNext(QueueNode nextval)// 置next
	{
		next = nextval;
	}

	public Object getElement()// 取element
	{
		return element;
	}

	public void setElement(Object _element)// 设置元素
	{
		element = _element;
	}

	public String toString()// 装换element为String 类型
	{
		return element.toString();
	}
}
