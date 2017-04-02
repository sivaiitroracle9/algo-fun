package leetcode.algorithms.heap;

import java.util.PriorityQueue;

public class Kth_Largest_Element_in_an_Array {
	public int findKthLargest(int[] nums, int k) {
				
		PriorityQueue<Integer> min_heap = new PriorityQueue<>(k);
		for (int i = 0; i < nums.length; i++) {
			if (min_heap.size() < k) {
				min_heap.add(nums[i]);
			} else if(min_heap.peek() <= nums[i]){
				min_heap.poll();
				min_heap.add(nums[i]);
			}
		}
		return min_heap.peek();
	}
}
