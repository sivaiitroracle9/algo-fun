package leetcode.algorithms.heap;

import java.util.PriorityQueue;

public class Merge_k_Sorted_Lists {
	public ListNode mergeKLists(ListNode[] lists) {
		
		ListNode head = null;
		
		PriorityQueue<HeapNode> heap = new PriorityQueue<>(lists.length, (o1, o2)-> o1.node.val-o2.node.val);
		for(int i=0; i<lists.length; i++) {
			ListNode node = lists[i];
			if(node != null) {
				lists[i] = node.next;
				node.next = null;
				heap.offer(new HeapNode(node, i));
			}
		}
		
		ListNode curr = null;
		while(!heap.isEmpty()) {
			HeapNode node = heap.poll();
			if(head == null) {
				head = node.node;
				curr = head;
			}
			else {
				curr.next = node.node;
				curr = curr.next;
			}
			if(lists[node.index] != null) {
				ListNode next = lists[node.index];
				lists[node.index] = next.next;
				next.next = null;
				heap.offer(new HeapNode(next, node.index));
			}
		}
		return head;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public class HeapNode {
		ListNode node;
		int index;
		
		public HeapNode(ListNode node, int index) {
			this.node = node;
			this.index = index;
		}
	}
}
