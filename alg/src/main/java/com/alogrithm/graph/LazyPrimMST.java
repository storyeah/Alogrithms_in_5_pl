package com.alogrithm.graph;

public class LazyPrimMST {
	private 
	public static void main(String[] args){
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		LazyPrimMST mst = new LazyPrimMST();
		for(Edge e: mst.edges())
			System.out.println(e);
		System.out.println("mst's min weight: %.5f", mst.weight());
	}
}
