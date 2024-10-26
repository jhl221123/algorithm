package baekjoon.Quiz19940;

import java.io.*;

public class Quiz19940 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] ans = new int[5];

			ans[0] = N / 60;
			N %= 60;

			if(N <= 35) {
				// '타겟을 초과한 후 빼는 경우 == 타겟을 초과하지 않고 더하는 경우' 를 만족하려면 후자가 5를 포함해야 한다.
				if(N % 10 <= 5) { // 10: -> 1: -> (5를 포함해야한다.)
					ans[1] = N / 10;
					ans[3] = N % 10;
				} else { // 10: -> 1: <- (5를 포함하지 않는다.)
					ans[1] = N / 10 + 1;
					ans[4] = 10 - (N % 10);
				}
			} else {
				ans[0]++;
				// '타겟 미만으로 내려간 후 더하는 경우 == 타겟 미만으로 내려가지 않고 빼는 경우' 를 만족하려면 후자가 5를 포함해야 한다.
				if(N % 10 < 5) { // 10: <- 1: -> (5를 포함하지 않는다.)
					ans[2] = 6 - (N / 10);
					ans[3] = N % 10;
				} else { // 10: <- 1: <- (5를 포함해야한다.)
					ans[2] = 6 - (N / 10 + 1);
					ans[4] = 10 - (N % 10);
				}
			}

			sb.append(ans[0]).append(" ")
				.append(ans[1]).append(" ")
				.append(ans[2]).append(" ")
				.append(ans[3]).append(" ")
				.append(ans[4]).append("\n");
		}
		System.out.print(sb);
	}
}
