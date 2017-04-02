package leetcode.algorithms.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Find_K_Pairs_with_Smallest_Sums {

	public static void main(String[] args) {
		Find_K_Pairs_with_Smallest_Sums f = new Find_K_Pairs_with_Smallest_Sums();
		int[] nums1 = { 1, 1, 2 };
		int[] nums2 = { 1, 2, 3 };
		List<int[]> pairs = f.kSmallestPairs(nums1, nums2, 10);
	}

	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> pairs = new ArrayList<>();
		if (nums1.length == 0 || nums2.length == 0 || k==0)
			return pairs;
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((o1, o2) -> o1[0]
				+ o1[1] - o2[0] - o2[1]);
		for (int i = 0; i < Math.min(nums1.length, k); i++) {
			heap.offer(new int[] { nums1[i], nums2[0], 0 });
		}

		int i = k;
		while (i-- > 0 && !heap.isEmpty()) {
			int[] pair = heap.poll();
			System.out.println(pair[0]+ ", " + pair[1]);
			pairs.add(new int[] { pair[0], pair[1] });

			if (pair[2] == Math.min(nums2.length, k)-1)
				continue;
			heap.offer(new int[] { pair[0], nums2[pair[2] + 1], pair[2] + 1 });

		}

		return pairs;
	}
}
