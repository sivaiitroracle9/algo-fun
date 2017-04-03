package leetcode.algorithms.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Find_Median_from_Data_Stream {

	PriorityQueue<Integer> left_max_heap = new PriorityQueue<>((o1, o2) -> o2
			- o1);
	PriorityQueue<Integer> right_min_heap = new PriorityQueue<>();

	public static void main(String[] args) {
		Find_Median_from_Data_Stream f = new Find_Median_from_Data_Stream();
		Arrays.asList(1, 2, 3).stream().forEach(n -> f.addNum(n));
	}
	
	
	/** initialize your data structure here. */
	public Find_Median_from_Data_Stream() {

	}

	public void addNum(int num) {

		if (left_max_heap.isEmpty())
			left_max_heap.offer(num);
		else {
			if (left_max_heap.peek() == num) {
				if (right_min_heap.size() < left_max_heap.size())
					right_min_heap.offer(num);
				else
					left_max_heap.offer(num);
			} else if (left_max_heap.peek() > num) {
				if (right_min_heap.size() < left_max_heap.size()) {
					right_min_heap.offer(left_max_heap.poll());
				}
				left_max_heap.offer(num);
			} else {

				if (left_max_heap.size() > right_min_heap.size()) {
					right_min_heap.offer(num);
				}

				if (right_min_heap.size() == left_max_heap.size()) {
					if (right_min_heap.peek() < num) {
						left_max_heap.offer(right_min_heap.poll());
						right_min_heap.offer(num);
					}

					else
						left_max_heap.offer(num);
				}
			}
		}
	}

	public double findMedian() {
		if (left_max_heap.size() > right_min_heap.size())
			return left_max_heap.peek() * 1.0;
		else if (left_max_heap.size() == 0 && right_min_heap.size() == 0)
			return 0.0;
		return (left_max_heap.peek() + right_min_heap.peek()) / 2.0;
	}

}
