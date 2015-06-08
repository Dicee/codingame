package miscellaneous.utils.test.sort;

import static miscellaneous.utils.math.MathUtils.lowerThan;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import miscellaneous.utils.sort.InsertionSort;
import miscellaneous.utils.sort.MergeSort;
import miscellaneous.utils.sort.QuickSort;
import miscellaneous.utils.sort.SelectionSort;
import miscellaneous.utils.sort.SortAlgorithm;

public class CompareSort {
	public static void main(String[] args) {
		int n = 10;
		Random rd = new Random();
		Integer[] arr = new Integer[n];
		for (int i=0 ; i<n ; i++)
			arr[i] = rd.nextInt(n);
		
		List<SortAlgorithm<Integer>> algorithms = Arrays.asList(new SelectionSort<>(),new InsertionSort<>(),new QuickSort<>(),new MergeSort<>());
		for (SortAlgorithm<Integer> algorithm : algorithms) {
			Integer[] test = Arrays.copyOf(arr,arr.length);
			long start     = System.nanoTime();
			algorithm.sort(test);
			checkSorted(test);
			System.out.println(String.format("Time elapsed with %s : %f ms",
					algorithm.getName(),(System.nanoTime() - start)/1e6));
		}
	}

	private static void checkSorted(Integer[] arr) {
		for (int i=1 ; i<arr.length ; i++) 
			if (!lowerThan(arr[i - 1],arr[i]))
				throw new AssertionError("Array not well sorted " + Arrays.toString(arr));
	}
}
