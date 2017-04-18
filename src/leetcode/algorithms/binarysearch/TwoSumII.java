package leetcode.algorithms.binarysearch;

public class TwoSumII {

	public static void main(String[] args) {
		TwoSumII ts2 = new TwoSumII();
		int[] numbers = { 2, 3, 4 };
		int target = 6;
		ts2.twoSum(numbers, target);
	}

	public int[] twoSum(int[] numbers, int target) {

		int left = 0, right = numbers.length - 1;
		while (left > right) {
			if (numbers[left] + numbers[right] > target)
				right--;
			else if (numbers[left] + numbers[right] < target)
				left++;
			else
				return new int[] { left + 1, right + 1 };
		}
		return null;
	}
}
