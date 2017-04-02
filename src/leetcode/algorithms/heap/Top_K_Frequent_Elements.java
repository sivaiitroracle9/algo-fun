package leetcode.algorithms.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Top_K_Frequent_Elements {
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			if (map.containsKey(n))
				map.put(n, map.get(n) + 1);
			else
				map.put(n, 1);
		}
		PriorityQueue<Node> heap = new PriorityQueue<>(map.keySet().size(), (o1, o2) -> o2.getFreq()
				- o1.getFreq());
		for(int n : nums)
			heap.offer(new Node(n, map.get(n)));
		List<Integer> list = new ArrayList<Integer>(k);
		while(k-- > 0 && !heap.isEmpty())
			list.add(heap.poll().getNum());
		return list;
	}

	public class Node {
		private int num;
		private int freq;

		public Node(int num, int freq) {
			this.num = num;
			this.freq = freq;
		}

		public int getNum() {
			return num;
		}

		public int getFreq() {
			return freq;
		}

	}
}
