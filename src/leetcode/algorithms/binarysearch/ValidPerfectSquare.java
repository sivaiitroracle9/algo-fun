package leetcode.algorithms.binarysearch;

public class ValidPerfectSquare {

	public static void main(String[] args) {
		ValidPerfectSquare vps = new ValidPerfectSquare();
		vps.isPerfectSquare(9);
	}

	public boolean isPerfectSquare(int num) {

		if (num < 1)
			return false;

		int i = 2;
		int num2 = i * i;

		while (num / num2 >= 1) {

			while (num != 0 && num % num2 == 0) {
				num = num / num2;
			}

			i++;
			num2 = i * i;
		}
		if (num == 1)
			return true;

		return false;
	}
}
