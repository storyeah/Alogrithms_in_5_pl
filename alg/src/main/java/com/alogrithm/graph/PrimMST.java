package com.alogrithm.graph;

public class PrimMST {
	private static final double FLOATING_POINT_EPSILON = E1-12;
	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;
//	private double weight;
	
	private PrimMST(EdgeWeightedGraph G){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		
		pq = new IndexMinPQ<Double>(G.V());
		for(int v=0; v<G.V(); v++)
			distTo[v] = FLOATING_POINT_EPSILON;
		for(int v=0; v<G.V(); v++)
			if(!marked[v])
				prim(G, v);
		assert check(G);
	}
	private void prim(EdgeWeightedGraph G, int s){
		distTo[s] = 0.0;
		pq.insert(s, distTo[s]);
		while(!pq.isEmpty()){
			int v = pq.delMin();
			scan(G, v);
		}
	}
	private void scan(EdgeWeightedGraph G, int s){
		marked[s] = true;
		for(Edge e: G.adj(s)){
			int w = e.other(s);
			if(marked[w]) continue;
			if(distTo[w] > distTo[s]+ e.weight()){
				distTo[w] = distTo[s] + e.weight();
				edgeTo[w] = e;
				if(pa.contains(w)) pq.decreaseKey(w, distTo[w]);
				else 			   pq.insert(w, distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges(){
		Queue<Edge> mst = new Queue<Edge>();
		for(int v =0; v<edgeTo.length(); v++){
			Edge e = edgeTo[v];
			if(e != null)
				mst.enqueue(e);
		}
		return mst;
	}
	public double weight(){
		double weight = 0.0;
		for(Edge e: edges()){
			weight += e.weight();
		}
		return weight;
	}
	 private boolean check(EdgeWeightedGraph G) {

	        // check weight
	        double totalWeight = 0.0;
	        for (Edge e : edges()) {
	            totalWeight += e.weight();
	        }
	        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
	            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
	            return false;
	        }

	        // check that it is acyclic
	        UF uf = new UF(G.V());
	        for (Edge e : edges()) {
	            int v = e.either(), w = e.other(v);
	            if (uf.connected(v, w)) {
	                System.err.println("Not a forest");
	                return false;
	            }
	            uf.union(v, w);
	        }

	        // check that it is a spanning forest
	        for (Edge e : G.edges()) {
	            int v = e.either(), w = e.other(v);
	            if (!uf.connected(v, w)) {
	                System.err.println("Not a spanning forest");
	                return false;
	            }
	        }

	        // check that it is a minimal spanning forest (cut optimality conditions)
	        for (Edge e : edges()) {

	            // all edges in MST except e
	            uf = new UF(G.V());
	            for (Edge f : edges()) {
	                int x = f.either(), y = f.other(x);
	                if (f != e) uf.union(x, y);
	            }

	            // check that e is min weight edge in crossing cut
	            for (Edge f : G.edges()) {
	                int x = f.either(), y = f.other(x);
	                if (!uf.connected(x, y)) {
	                    if (f.weight() < e.weight()) {
	                        System.err.println("Edge " + f + " violates cut optimality conditions");
	                        return false;
	                    }
	                }
	            }

	        }

	        return true;
	    }

	 public static void main(String[] args){
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		PrimMST mst = new PrimMST(G);
		for(Edge e : mst.edges()){
			System.out.println(e);
		}
		System.out.println("%.5f\n", mst.weight());
	 }
}
