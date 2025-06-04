package baekjoon.Quiz5079;

import java.io.*;
import java.util.*;

/*
Gold5: Discounts / [dp]
*/
public class Quiz5079 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String productName = br.readLine();
			if ("#".equals(productName)) break;
			sb.append(productName).append("\n");

			String[] price = br.readLine().split(" ");
			int PD = Integer.parseInt(price[0]);
			int PC = Integer.parseInt(price[1]);
			int pricePerItem = PD * 100 + PC;

			int D = Integer.parseInt(br.readLine());
			List<int[]> deals = new ArrayList<>();
			for (int i = 0; i < D; i++) {
				String[] deal = br.readLine().split(" ");
				int B = Integer.parseInt(deal[0]);
				int F = Integer.parseInt(deal[1]);
				deals.add(new int[]{B, F});
			}

			int E = Integer.parseInt(br.readLine());
			for (int t = 0; t < E; t++) {
				int target = Integer.parseInt(br.readLine());

				int INF = Integer.MAX_VALUE / 2;
				int[] dp = new int[target + 101];
				Arrays.fill(dp, INF);
				dp[0] = 0;

				for (int i = 0; i <= target; i++) {
					if (dp[i] == INF) continue;
					for (int[] deal : deals) {
						int B = deal[0];
						int F = deal[1];
						int total = i + B + F;
						if (total < dp.length) {
							dp[total] = Math.min(dp[total], dp[i] + B * pricePerItem);
						}
					}
					if (i + 1 < dp.length) {
						dp[i + 1] = Math.min(dp[i + 1], dp[i] + pricePerItem);
					}
				}

				int minCost = INF;
				for (int i = target; i < dp.length; i++) {
					minCost = Math.min(minCost, dp[i]);
				}

				int originalCost = target * pricePerItem;
				int saving = originalCost - minCost;

				sb.append(String.format("Buy %d, save $%.2f\n", target, saving / 100.0));
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}
}
