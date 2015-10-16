package com.alogrithm.fundamentals;

public class UF {
	private int[] parents;
	private byte[] rank;
	private int count;

	public UF(int N){
		if(N<0) throw new IllegalArgumentException();
		parents = new int[N];
		rank = new byte[N];
		count = N;
		for(int i=0; i<N; i++){
			parents[i] = i;
			rank[i] = 0;
		}
	}
	public int find(int p){
		validate(p);
		if(p == parents[p]) return p;
		else return find(parents[p]);
	}
	public int count(){
		return count;
	}
	public boolean connected(int p, int q){
		return rank[p] == rank[q];
	}
	public boolean validate(int p){
		int N = rank.length;
		if(p<0 || p>=N) throw new IndexOutOfBoundsException("index"+ p+ "is not between 0 and N-1");
		return true;
	}
	public void Union(int p, int q){
		validate(p);
		validate(q);
		int pRoot = find(p);
		int qRoot = find(q);
		if(connected(pRoot, qRoot))        return ;
		else if(rank[pRoot] < rank[qRoot]) parents[pRoot] = qRoot;
		else if(rank[pRoot] > rank[qRoot]) parents[qRoot] = pRoot;
		else{
			parents[qRoot] = pRoot;
			rank[pRoot]++;
		}
		count--;
	}
}
