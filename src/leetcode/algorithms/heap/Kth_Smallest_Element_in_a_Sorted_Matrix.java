package leetcode.algorithms.heap;

import java.util.PriorityQueue;

public class Kth_Smallest_Element_in_a_Sorted_Matrix {
	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Node> heap = new PriorityQueue<Node>(
				(o1, o2) -> o1.getValue() - o2.getValue());

		for (int j = 0; j < matrix.length; j++)
			heap.offer(new Node(0, j, matrix[0][j]));
		for (int i = 0; i < k - 1; i++) {
			Node node = heap.poll();
			if (node.getX() == matrix.length - 1)
				continue;
			heap.offer(new Node(node.getX() + 1, node.getY(), matrix[node
					.getX() + 1][node.getY()]));
		}

		return heap.poll().getValue();
	}

	private class Node {
		private int x;
		private int y;
		private int value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getValue() {
			return value;
		}

	}
}
