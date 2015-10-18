package com.alogrithm.sorting;

public class Merge {
	private Merge(){}
	public static void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
	}
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
		if(hi<= lo) return ;
		int mid = lo + (hi-lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	} 
	private static void merge(Comparable[] a, Comparable[] aux, int lo ,int mid, int hi){
		if(hi<= lo) return ;

		for(int i=lo; i<=hi; i++){
			aux[i] = a[i];
		}
		int i = lo;
		int j = mid+1;
		for(int k=lo; k<= hi;k++){
			if(i> mid) 						a[k] = aux[j++];
			else if(j>hi)  					a[k] = aux[i++]; 
			else if(less(aux[i], aux[j]))	a[k] = aux[i++] ;
			else 							a[k] = aux[j++];
		}
	}
	private static boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
}
