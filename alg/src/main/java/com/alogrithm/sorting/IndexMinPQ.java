package com.alogrithm.sorting;
import java.util.Iterator;
import java.util.NoSuchElementException;
	public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
	private int maxN;
	private int N;
	private int[] pq;
	private int[] qp;
	private Key[] keys;

	public IndexMinPQ(int maxN){
		if(maxN <0 ) throw new IllegalArgumentException();
		this.maxN = maxN;
		keys = (Key[])new Comparable[maxN +1];
		pq = new int[maxN+1];
		qp = new int[maxN+1];
		for(int i=0; i<=maxN;i++)
			qp[i] = -1;
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public boolean contains(int i){
		if(i< 0 || i>=maxN) throw new IndexOutOfBoundsException();
		return qp[i] != -1;
	}
	public int size(){
		return N;
	}
	public void insert(int i, Key key){
		if(i<0 || i>=maxN)
			throw new IndexOutOfBoundsException();
		if(contains(i))
			throw new IllegalArgumentException();
		N++;
		qp[i] = N;
		pq[N] = i;
		keys[i] = key;
		swim(N);
	}
	public int minIndex(){
		if(N== 0) 
			throw new NoSuchElementException("priority queue underflow");
		return pq[1];
	}
	public Key minKey(){
		if(N == 0) 
			throw new NoSuchElementException("priority queue underflow");
		return keys[pq[1]];
	}
	public Key keyOf(int i){
		if(i< 0 || i>=maxN)
			throw new IndexOutOfBoundsException();
		if(!contains(i)) 
			throw new NoSuchElementException("index is not in the priority queu");
		else return keys[i];
	
	}
	public int delMin(){
		if(N == 0)
			throw new NoSuchElementException("priority queue underflow");
		int min = pq[1];
		exch(1, N--);
		sink(1);
		qp[min] = -1;
		pq[N+1] = -1;
		return min;
	}
	public void changeKey(int i, Key key){
		if(i<0 || i>=maxN)
			throw new IndexOutOfBoundsException();
		if(!contains(i)) 
			throw new NoSuchElementException("index is not in the priority queu");
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}
	public void change(int i, Key key){
		changeKey(i, key);
	}
	public void decreaseKey(int i, Key key){
		if(i<0 || i>=maxN)
			throw new IndexOutOfBoundsException();
		if(!contains(i)) 
			throw new NoSuchElementException("index is not in the priority queu");
		if(keys[i].compareTo(key)<=0)
			throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key ");
		keys[i] = key;
		swim(qp[i]);
	}
	public void increaseKey(int i, Key key){
		if(i<0 || i>=maxN)
			throw new IndexOutOfBoundsException();
		if(!contains(i)) 
			throw new NoSuchElementException("index is not in the priority queu");
		if(keys[i].compareTo(key)>=0)
			throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly increase the key ");
		keys[i] = key;
		sink(qp[i]);
	}
	public void delete(int i){
		int index = qp[i];
		exch(index, N--);
		swim(index);
		sink(index);
		keys[i] = null;
		qp[i] = -1;
	}
	public void exch(int i, int j){
		validate(i);
		validate(j);
		Integer swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	public void swim(int i){
		while(i>1 && greater(i/2, i)){
			exch(i,i/2);
			i=i/2;
		}
	}
	public void sink(int i){
		while(2*i<=N){
			int k = 2*i;
			if(k<N && greater(k,k+1)) k++;
			if(!greater(i, k)) break;
			exch(i, k);
			i = k;
		}
	} 
	public boolean greater(int i, int j){
		validate(i);
		validate(j);
		return keys[pq[i]].compareTo(keys[pq[j]])>0;
	}
	public void validate(int i){
	}
	public Iterator<Integer> iterator(){return new HeapIterator();}
	 private class HeapIterator implements Iterator<Integer> {
	        private IndexMinPQ<Key> copy;
	        public HeapIterator() {
	            copy = new IndexMinPQ<Key>(pq.length - 1);
	            for (int i = 1; i <= N; i++)
	                copy.insert(pq[i], keys[pq[i]]);
	        }

	        public boolean hasNext()  { return !copy.isEmpty();                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public Integer next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            return copy.delMin();
	        }
	 }
}