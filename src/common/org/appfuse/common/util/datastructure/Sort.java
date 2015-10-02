package org.appfuse.common.util.datastructure;

/* 
 *@date:08-07-11 
 *@descript:���ֳ��������ʵ����Ӧ�� 
 **/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Sort
{
	public Sort()
	{
	}

	// ð������
	public int[] bubbleSort(int[] _array)
	{
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		
		int len = array.length;
		for (int i = 1; i < len; i++)
		{
			for (int j = 0; j < len - 1; j++)
			{
				if (array[j] > array[j + 1])
				{
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		return array;
	}

	public int[] quickSort(int[] _array){
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		return quickSortAlgorithm(array,0,array.length-1);
	}
	
	// ��������
	private int[] quickSortAlgorithm(int[] array, int low, int high)
	{
		int i = low;
		int j = high;
		int temp = array[low];
		while (i < j)
		{
			// �������ұ߽��бȽ�
			while (i < j && temp <= array[j])
			{
				j--;
			}
			if (i < j)
			{
				array[i] = array[j];
				i++;
			}
			// ��������߽��бȽ�
			while (i < j && array[i] < temp)
			{
				i++;
			}
			if (i < j)
			{
				array[j] = array[i];
				j--;
			}
			array[i] = temp;
			if (low < i)
			{
				quickSortAlgorithm(array, low, i - 1);// ����˵����ݽ��еݹ�
			}
			if (i < high)
			{
				quickSortAlgorithm(array, j + 1, high);// ���Ҷ˵����ݽ��еݹ�
			}
		}
		return array;
	}

	// ֱ�Ӳ�������
	public int[] insertSort(int[] _array)
	{
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		
		for (int i = 0; i < array.length - 1; i++)
		{
			int temp = array[i + 1];
			int j = i;
			// arry[j]<temp�������򣬷���temp���뵽arry[j]ǰ��
			while (j > -1 && temp <= array[j])
			{
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
		return array;
	}

	// ϣ������
	public int[] shellSort(int[] _array)
	{
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		
		int d = array.length / 2;
		while (d > 0)
		{
			for (int i = d; i < array.length; i++)
			{
				int temp = array[i];
				int j = i - d;
				while (j > -1 && temp <= array[j])
				{
					array[j + d] = array[j];
					j -= d;
				}
				array[j + d] = temp;
			}
			d = d / 2;
		}
		return array;
	}

	// ֱ��ѡ������
	public int[] selectSort(int[] _array)
	{
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		
		for (int i = 0; i < array.length - 1; i++)
		{
			int small = i;
			for (int j = i + 1; j < array.length; j++)
			{
				if (array[j] < array[small])
				{
					small = j;
				}
			}
			if (small != i)
			{
				int temp = array[small];
				array[small] = array[i];
				array[i] = temp;
			}
		}
		return array;
	}

	public int[] mergeSort(int[] _array){
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		return mergeSortAlgorithm(array, 0, array.length - 1);
	}
	
	private int[] mergeSortAlgorithm(int[] array, int left, int right)
	{
		int[] arryTemp = new int[array.length];
		if (left < right)
		{
			int mid = (left + right) / 2;
			mergeSortAlgorithm(array, left, mid);
			mergeSortAlgorithm(array, mid + 1, right);
			mergeSortAlgorithmFiner(array, arryTemp, left, mid, right);
		}
		return array;
	}
	
	// �鲢����
	private void mergeSortAlgorithmFiner(int[] array, int[] arryTemp, int left, int mid,int right)
	{
		int i = left;
		int j = mid + 1;
		int k = left;
		while ((i <= mid) && (j <= right))
		{
			if (array[i] < array[j])
			{
				arryTemp[k++] = array[i++];
			} else
			{
				arryTemp[k++] = array[j++];
			}
		}
		while (i <= mid)
		{
			arryTemp[k++] = array[i++];
		}
		while (j <= right)
		{
			arryTemp[k++] = array[j++];
		}
		// ������arryTemp �е����ݸ��Ƶ� arry ��
		for (int t = left; t <= right; t++)
		{
			array[t] = arryTemp[t];
		}
	}

	// �������Ԫ��
	public void display(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ",");
		}
		System.out.println();
	}

	// ��ʾ��������
	public void display(String sortName)
	{
		System.out.println(sortName + ":");
	}

	// �˵�����
	public void MenuShow(int[] array)
	{
		String str = null;
		try {
			do
			{
				System.out.println("\t" + "\t" + "\t" + "1 ð������");
				System.out.println("\t" + "\t" + "\t" + "2 ��������");
				System.out.println("\t" + "\t" + "\t" + "3 ֱ�Ӳ�������");
				System.out.println("\t" + "\t" + "\t" + "4 ϣ������");
				System.out.println("\t" + "\t" + "\t" + "5 ѡ������");
				System.out.println("\t" + "\t" + "\t" + "6 �鲢����");
				System.out.println("\t" + "\t" + "\t" + "��ѡ����Ҫִ�е�����ʽ��1-6");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				str = br.readLine();
				int choice = new Integer(str).intValue();
				switch (choice)
				{
				case 1:
					// ����ð�������㷨
					display("ԭʼ��������:");
					display(array);
					display("ð�������:");
					display(bubbleSort(array));
					display("ԭʼ��������:");
					display(array);
					break;
				case 2:
					// ���ÿ��������㷨
					display("ԭʼ��������:");
					display(array);
					display("���������");
					display(quickSort(array));
					display("ԭʼ��������:");
					display(array);
					break;
				case 3:
					// ����ֱ�Ӳ��������㷨
					display("ԭʼ��������:");
					display(array);
					display("ֱ�Ӳ��������:");
					display(insertSort(array));
					display("ԭʼ��������:");
					display(array);
					break;
				case 4:
					// ����ϣ�������㷨
					display("ԭʼ��������:");
					display(array);
					display("ϣ�������:");
					display(shellSort(array));
					display("ԭʼ��������:");
					display(array);
					break;
				case 5:
					// ����ѡ�������㷨
					display("ԭʼ��������:");
					display(array);
					display("ѡ�������:");
					display(selectSort(array));
					display("ԭʼ��������:");
					display(array);
					break;
				case 6:
					// ���ù鲢�����㷨
					display("ԭʼ��������:");
					display(array);
					display("�鲢�����:");
					display(mergeSort(array));
					display("ԭʼ��������:");
					display(array);
					break;
				default:
					System.out.println("û����Ҫ��ѡ��! ");
				}
				System.out.println("����/�˳�? y/n");
				str = br.readLine();
			} while ("y".equals(str) || "Y".equals(str));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Random rand = new Random();
		int[] largeArray = new int[500];
		for (int i = 0; i < 500; i++)
		{
			largeArray[i] = rand.nextInt();
		}
		
		Sort sort = new Sort();
		int[] array = { 38, 5, 19, 26, 49, 97, 1, 66 };
		
		// ���Ҫ�������������������򣬽��轫 largeArray ������Ϊ��������ȥ
		sort.MenuShow(array);
	}
}
