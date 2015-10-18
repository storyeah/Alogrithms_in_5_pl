package com.alogrithm.sorting;

public class Quick {
	private Quick(){}
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a , int lo, int hi){
		if(hi <= lo) return ; //less(temp, a[--j]) would raise arrayIndexOutOfBounds if not so
		int mid = partition(a, lo, hi);
		sort(a, lo, mid-1);
		sort(a, mid+1, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo;
		int j = hi+1; // less(temp, a[--j]
		Comparable temp = a[lo];
		while(true){
			while(less(a[++i], temp))
				if(i == hi) break;
			while(less(temp, a[--j]))
				if(j == lo) break;
			// while(less(a[++j, temp) would raise error: arrayIndexOutOfBounds if i>j.
			if(i>= j) break; 
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	private static boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
	private static void exch(Object[] a, int i, int j){
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
}
