package leetcode.algorithms.heap;

import java.util.PriorityQueue;

public class IPO {

	public static void main(String[] args) {
		IPO ipo = new IPO();
		int k = 2;
		int W = 0;
		int[] profits = { 1, 2, 3 };
		int[] capital = { 0, 1, 1 };
		
		ipo.findMaximizedCapital(k, W, profits, capital);
	}

	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {

		if (Profits.length == 0 || Capital.length == 0)
			return W;

		PriorityQueue<ProfitCapital> minCapital = new PriorityQueue<>((o1, o2) -> {
			return o1.getCapital() - o2.getCapital();
		});
		PriorityQueue<ProfitCapital> maxProfit = new PriorityQueue<>((o1, o2) -> {
			return o2.getProfit() - o1.getProfit();
		});

		for (int i = 0; i < Profits.length && i < Capital.length; i++) {
			minCapital.offer(new ProfitCapital(Profits[i], Capital[i]));
		}

		while (k > 0 && (!minCapital.isEmpty() || !maxProfit.isEmpty())) {
			while (!minCapital.isEmpty() && minCapital.peek().getCapital() <= W) {
				maxProfit.offer(minCapital.poll());
			}

			if (!maxProfit.isEmpty() && k > 0) {
				ProfitCapital pc = maxProfit.poll();
				W += pc.getProfit();
				k--;
			} else
				break;
		}

		return W;
	}

	private class ProfitCapital {
		int profit;
		int capital;

		public ProfitCapital(int profit, int capital) {
			this.profit = profit;
			this.capital = capital;
		}

		public int getProfit() {
			return profit;
		}

		public int getCapital() {
			return capital;
		}

	}
}
