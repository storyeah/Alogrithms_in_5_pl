package com.alogrithm.graph;

public class Edge implements Comparable<Edge>{
	private final int v;
	private final int w;
	private double weight;
	
	public Edge(int v, int w, double weight) {
		if(v<0 || w<0)
			throw new IndexOutOfBoundsException("v,w should be at least 1");
		if(Double.isNaN(weight))
			throw new IllegalArgumentException("weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public double weight(){
		return weight;
	}

	public int either(){
		return v;
	}
	public int other(int vertex){
		if(v == vertex) return w;
		else if(w == vertex) return v;
		else throw new IllegalArgumentException("Illegal endpoint");
	}

	 @Override
	 public int compareTo(Edge that) {
	        if      (this.weight() < that.weight()) return -1;
	        else if (this.weight() > that.weight()) return +1;
	        else                                    return  0;
	 }

	 public String toString(){
		 return String.format("%d-%d %.5f", v, w, weight);
	 }
	 public static void main(String[] args){
		 Edge e = new Edge(12, 34, 5.67);
		 System.out.println(e);
	 }
}
