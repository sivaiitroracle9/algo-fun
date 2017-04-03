package leetcode.algorithms.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sliding_Window_Maximum {

	public int[] maxSlidingWindow(int[] nums, int k) {
		if(nums.length == 0 || k==0) return new int[0];
		PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.num
				- o1.num);
		Map<Integer, Node> map = new HashMap<>();
		int i = 0;
		for (; i < k - 1; i++) {
			Node node = new Node(nums[i], i);
			map.put(i, node);
			heap.offer(node);
		}
		int[] result = new int[nums.length - k + 1];
		while (i <= nums.length - 1) {
			Node node = new Node(nums[i], i);
			map.put(i, node);
			heap.offer(node);
			
			result[i- (k-1)] = heap.peek().num;
			heap.remove(map.get(i - (k-1)));
			i++;
		}
		return result;
	}

	public class Node {
		int num;
		int index;

		Node(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}

}
