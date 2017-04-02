package leetcode.algorithms.heap;

import java.util.PriorityQueue;

public class Ugly_Number_II {
	public int nthUglyNumber(int n) {
		if (n == 1)
			return 1;
		PriorityQueue<Long> heap = new PriorityQueue<>();
		
		heap.offer(1L);
		for (int i = 1; i < n; i++) {
			long temp = heap.poll();
			while (!heap.isEmpty() && heap.peek() == temp)
				temp = heap.poll();
			heap.offer(temp*2);
			heap.offer(temp*3);
			heap.offer(temp*5);
		}
		return heap.poll().intValue();
	}
}
