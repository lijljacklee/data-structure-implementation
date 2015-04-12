package Heap;

public class Heap {
	int[] nodes;
	int size;
	boolean isMaxHeap;
	
	public Heap (int capacity, boolean isMaxHeap) {
		this.nodes = new int[capacity];
		this.size = capacity;
		this.isMaxHeap = isMaxHeap;
	}
	
	public Heap (int[] A, boolean isMaxHeap) {
		this.nodes = new int[A.length];
		this.size = A.length;
		this.isMaxHeap = isMaxHeap;
		for (int i = 0; i < A.length; i++) {
			this.nodes[i] = A[i];
		}
		int start = A.length / 2;
		for (int i = start; i >= 0; i--) {
			shiftDown(i);
		}
	}
	
	private void shiftDown (int idx) {
		int left = idx * 2 + 1;
		int right = idx * 2 + 2;
		while (left < this.size || right < this.size) {
			if (this.isMaxHeap) {
				int leftVal = (left < this.size) ? this.nodes[left] : Integer.MIN_VALUE;
				int rightVal = (right < this.size) ? this.nodes[right] : Integer.MIN_VALUE;
				int next = (leftVal > rightVal) ? left : right;
				if (this.nodes[idx] > this.nodes[next]) {
					break;
				} else {
					swap(idx, next);
					idx = next;
					left = idx * 2 + 1;
					right = idx * 2 + 2;
				}
			} else {
				int leftVal = (left < this.size) ? this.nodes[left] : Integer.MAX_VALUE;
				int rightVal = (right < this.size) ? this.nodes[right] : Integer.MAX_VALUE;
				int next = (leftVal <= rightVal) ? left : right;
				if (this.nodes[idx] < this.nodes[next]) {
					break;
				} else {
					swap(idx, next);
					idx = next;
					left = idx * 2 + 1;
					right = idx * 2 + 2;
				}
			}
		}
	}
	
	private void swap(int x, int y) {
		int temp = this.nodes[x];
		this.nodes[x] = this.nodes[y];
		this.nodes[y] = temp;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public int peek() {
		return this.nodes[0];
	}
	
	public int pop() {
		int rootVal = this.nodes[0];
		swap(0, this.size - 1);
		this.size--;
		if (this.size == 0) {
			shiftDown(0);
		}
		return rootVal;
	}

	public boolean insert (int val) {
		if (this.size == this.nodes.length) {
			return false;
		}
		this.size++;
		this.nodes[this.size - 1] = val;
		int cur = this.size - 1;
		int parent = (cur - 1) / 2;
		while (parent >= 0 && ((isMaxHeap && this.nodes[parent] < this.nodes[cur]) ||(!isMaxHeap && this.nodes[parent] > this.nodes[cur]))) {
			swap(parent, cur);
			cur = parent;
			parent = (cur - 1) / 2;
		}
		return true;
	}
	
	public void getNodesValue (int[] A) {
		for (int i = 0; i < this.nodes.length; i++) {
			A[i] = this.nodes[i];
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.nodes.length; i++) {
			sb.append(this.nodes[i] + " ");
		}
		return sb.toString();
	}
	
	public static void main (String[] argv) {
		int[] num = {3, 2, 1, 4, 5};
		Heap hp = new Heap(num, false);
		System.out.println(hp.toString());
		hp.getNodesValue(num);
		for (int v : num) {
			System.out.print(v + " ");
		}
	}
}
