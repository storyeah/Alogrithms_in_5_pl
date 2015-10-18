package com.alogrithm.sorting;
/*
 * Max Heap, so the result will be in a ascending way.
 * sort in the input data itself .
 */
public class Heap {
	private Heap(){}
	public static void sort(Comparable[] pq){
		int N = pq.length;
		int k = N/2;
		for(int i=k; i>=1; i--)
			sink(pq, i, N);
		while(N>1){
			exch(pq, 1, N--); 
			sink(pq, 1, N); //the root index is not 0 but 1,mind the indexOutOfBounds error .
		}
	}
	private static void sink(Comparable[] pq, int k, int n){
		while(2*k<=n){
			int cur = 2*k; 
			if(cur<n && less(pq, cur, cur+1 )) cur++;
			if(!less(pq, k, cur)) break;
			exch(pq, k, cur);
			k = cur;
		}
	}
	private  static boolean less(Comparable[] pq, int i, int j){
		// the index should self-minus 1 to avoid indexOutOfBounds
		return pq[i-1].compareTo(pq[j-1]) < 0;
	}
	private static void exch(Object[] pq, int i, int j){
		// the index should self-minus 1 to avoid indexOutOfBounds
		Object swap = pq[i-1];
		pq[i-1] = pq[j-1];
		pq[j-1] = swap;
	}
}
