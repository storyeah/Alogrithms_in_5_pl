package com.alogrithm.sorting;

import org.junit.Test;

public class sort_test {
	
	@Test
	public void IndexMinPQ_test(){
		System.out.println("---------IndexMinPq_test------------------");
		String[] str = {"hello", "world", "this", "is", "sh"}; 
		IndexMinPQ<String> pq = new IndexMinPQ<String>(str.length);
		for(int i=0;i<str.length; i++)
			pq.insert(i, str[i]);
		for (int i : pq) {
            System.out.println(i + " " + str[i]);
        }

		System.out.println("");
		
		while(!pq.isEmpty()){
			int cur = pq.delMin();
			System.out.printf("%d-%s\n", cur, str[cur]);
		}
	}

	@Test
	public void Heap_test(){
		System.out.println("-------------Heap_test------------------");
		String[] str = {"hello", "world", "this", "is", "sh"}; 
		Heap.sort(str);
		for(String str_: str)
			System.out.println(str_);
	}
	
	@Test
	public void Quick_test(){
		System.out.println("-------------Quick_test------------------");
		String[] str = {"hello", "world", "this", "is", "sh"}; 
		Quick.sort(str);
		for(String str_: str)
			System.out.println(str_);
	}
	
	@Test
	public void Merge_test(){
		System.out.println("-------------Merge_test------------------");
		String[] str = {"hello", "world", "this", "is", "sh"}; 
		Merge.sort(str);
		for(String str_: str)
			System.out.println(str_);
	}
}
