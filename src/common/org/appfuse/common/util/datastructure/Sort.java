package org.appfuse.common.util.datastructure;

/* 
 *@date:08-07-11 
 *@descript:几种常用排序的实现与应用 
 **/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Sort
{
	public Sort()
	{
	}

	// 冒泡排序
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
	
	// 快速排序
	private int[] quickSortAlgorithm(int[] array, int low, int high)
	{
		int i = low;
		int j = high;
		int temp = array[low];
		while (i < j)
		{
			// 在数组右边进行比较
			while (i < j && temp <= array[j])
			{
				j--;
			}
			if (i < j)
			{
				array[i] = array[j];
				i++;
			}
			// 在数组左边进行比较
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
				quickSortAlgorithm(array, low, i - 1);// 对左端的数据进行递归
			}
			if (i < high)
			{
				quickSortAlgorithm(array, j + 1, high);// 对右端的数据进行递归
			}
		}
		return array;
	}

	// 直接插入排序
	public int[] insertSort(int[] _array)
	{
		//copy a original array to a new array.it won't impact the original array. 
		int[] array=new int[_array.length];
		System.arraycopy(_array,0,array,0,_array.length);
		
		for (int i = 0; i < array.length - 1; i++)
		{
			int temp = array[i + 1];
			int j = i;
			// arry[j]<temp则已有序，否则将temp插入到arry[j]前面
			while (j > -1 && temp <= array[j])
			{
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
		return array;
	}

	// 希尔排序
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

	// 直接选择排序
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
	
	// 归并排序
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
		// 将数组arryTemp 中的数据复制到 arry 中
		for (int t = left; t <= right; t++)
		{
			array[t] = arryTemp[t];
		}
	}

	// 输出数据元素
	public void display(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ",");
		}
		System.out.println();
	}

	// 显示排序名称
	public void display(String sortName)
	{
		System.out.println(sortName + ":");
	}

	// 菜单界面
	public void MenuShow(int[] array)
	{
		String str = null;
		try {
			do
			{
				System.out.println("\t" + "\t" + "\t" + "1 冒泡排序");
				System.out.println("\t" + "\t" + "\t" + "2 快速排序");
				System.out.println("\t" + "\t" + "\t" + "3 直接插入排序");
				System.out.println("\t" + "\t" + "\t" + "4 希尔排序");
				System.out.println("\t" + "\t" + "\t" + "5 选择排序");
				System.out.println("\t" + "\t" + "\t" + "6 归并排序");
				System.out.println("\t" + "\t" + "\t" + "请选择您要执行的排序方式：1-6");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				str = br.readLine();
				int choice = new Integer(str).intValue();
				switch (choice)
				{
				case 1:
					// 调用冒泡排序算法
					display("原始数据序列:");
					display(array);
					display("冒泡排序后:");
					display(bubbleSort(array));
					display("原始数据序列:");
					display(array);
					break;
				case 2:
					// 调用快速排序算法
					display("原始数据序列:");
					display(array);
					display("快速排序后");
					display(quickSort(array));
					display("原始数据序列:");
					display(array);
					break;
				case 3:
					// 调用直接插入排序算法
					display("原始数据序列:");
					display(array);
					display("直接插入排序后:");
					display(insertSort(array));
					display("原始数据序列:");
					display(array);
					break;
				case 4:
					// 调用希尔排序算法
					display("原始数据序列:");
					display(array);
					display("希尔排序后:");
					display(shellSort(array));
					display("原始数据序列:");
					display(array);
					break;
				case 5:
					// 调用选择排序算法
					display("原始数据序列:");
					display(array);
					display("选择排序后:");
					display(selectSort(array));
					display("原始数据序列:");
					display(array);
					break;
				case 6:
					// 调用归并排序算法
					display("原始数据序列:");
					display(array);
					display("归并排序后:");
					display(mergeSort(array));
					display("原始数据序列:");
					display(array);
					break;
				default:
					System.out.println("没有您要的选择! ");
				}
				System.out.println("继续/退出? y/n");
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
		
		// 如果要用上面的随机数进行排序，仅需将 largeArray 数组作为参数传过去
		sort.MenuShow(array);
	}
}
