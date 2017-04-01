package leetcode.algorithms.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Charecters_By_Frequency {

	public static void main(String[] args) {
		Sort_Charecters_By_Frequency scf = new Sort_Charecters_By_Frequency();
		System.out.println(scf.frequencySort("hello"));
	}
	
	PriorityQueue<HeapNode> heap;
	
    public String frequencySort(String str) {
    	if(str.length() == 0) return "";
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	char[] carr = str.toCharArray();
    	
    	for(int i=0; i<carr.length; i++) {
    		if(map.containsKey(carr[i]))
    			map.put(carr[i], map.get(carr[i]) + 1);
    		else map.put(carr[i], 1);
    	}
    	
    	heap = new PriorityQueue<HeapNode>(map.keySet().size(), 
    			(o1, o2) -> o2.getValue() - o1.getValue());
    	map.keySet().stream().forEach(e -> heap.add(new HeapNode(e, map.get(e))));
    	StringBuffer sb = new StringBuffer(str.length());
    	while(!heap.isEmpty()) {
    		HeapNode node = heap.poll();
    		for(int i=1; i<=node.getValue(); i++)
    			sb.append(node.getCharecter());
    	}
    	return sb.toString();
    }
	
	private class HeapNode {
		private char charecter;
		private int value;
		
		@SuppressWarnings("unused")
		public HeapNode(char charecter, int value){
			this.charecter = charecter;
			this.value = value;
		}

		public char getCharecter() {
			return charecter;
		}

		public int getValue() {
			return value;
		}
		
	}
    
}
