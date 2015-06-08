package miscellaneous.utils.sort;

import static miscellaneous.utils.collection.CollectionUtils.swap;
import static miscellaneous.utils.math.MathUtils.lowerThan;

import java.util.Random;

public class QuickSort<T extends Comparable<T>> implements SortAlgorithm<T> {
	private Random	rd;

	public QuickSort() {
		this.rd = new Random();
	}
	
	@Override
	public void sort(T[] arr) {
		sort(arr,0,arr.length - 1);
	}
	
	private void sort(T[] arr, int min, int max) {
		if (min < max) {
			int index = pivot(arr,min,max);
			sort(arr,min,index);
			sort(arr,index + 1,max);
		}
	}
	
	private int pivot(T[] arr, int min, int max) {
		T pivot    = arr[min + rd.nextInt(max - min)];
		int result = min;
		for (int i=min ; i<max ; i++) 
			if (lowerThan(arr[i],pivot)) swap(arr,i,result++);
		swap(arr,result,max);
		return result;
	}
	
	public T kthElement(T[] arr, int k) {
		return kthElement(arr,k,0,arr.length);
	}
	
	private T kthElement(T[] arr, int k, int min, int max) {
		if (min < max - 1) {
			int p = pivot(arr,min,max);
			return p == k - 1 ? arr[p]                      : 
				   p <  k - 1 ? kthElement(arr,k,p + 1,max) : kthElement(arr,k,min,p);
		}
		return arr[Math.min(min,max)];
 	}
	
	@Override
	public String getName() {
		return "Quicksort";
	}
}
