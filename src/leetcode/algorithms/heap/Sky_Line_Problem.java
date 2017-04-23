package leetcode.algorithms.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Sky_Line_Problem {

	public static void main(String[] args) {
		Sky_Line_Problem sk = new Sky_Line_Problem();
		int[][] buildings = { { 0, 2, 3 }, { 2, 5, 3 } };
		sk.getSkyline(buildings);
	}

	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> skyLine = new ArrayList<>();
		if (buildings.length == 0)
			return skyLine;

		List<int[]> building_points = new ArrayList<>(2 * buildings.length);
		for (int i = 0; i < buildings.length; i++) {
			building_points
					.add(new int[] { buildings[i][0], buildings[i][2], 1 });
			building_points
					.add(new int[] { buildings[i][1], buildings[i][2], 0 });
		}
		Collections.sort(building_points, (o1, o2) -> {
			if (o1[0] != o2[0])
				return o1[0] - o2[0];

			if (o1[2] != o2[2]) {
				if (o1[2] == 1)
					return -1;
				else
					return 1;
			}

			if (o1[2] == 1)
				return -(o1[1] - o2[1]);
			else
				return (o1[1] - o2[1]);
		});

		TreeMap<Integer, Integer> heightMap = new TreeMap<>(
				Collections.reverseOrder());
		int curr_height = 0;
		for (int[] point : building_points) {
			if (point[2] == 1) {
				if (heightMap.containsKey(point[1]))
					heightMap.put(point[1], heightMap.get(point[1]) + 1);
				else {
					heightMap.put(point[1], 1);
				}
			} else {
				if (heightMap.containsKey(point[1]))
					if (heightMap.get(point[1]) == 1)
						heightMap.remove(point[1]);
					else
						heightMap.put(point[1], heightMap.get(point[1]) - 1);
			}

			int newHeight = heightMap.keySet().size() == 0 ? 0 : heightMap
					.firstKey();
			if (curr_height != newHeight) {
				curr_height = newHeight;
				skyLine.add(new int[] { point[0], curr_height });
			}
		}
		return skyLine;
	}
}
