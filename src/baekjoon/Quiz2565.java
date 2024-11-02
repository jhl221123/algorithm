package baekjoon;

import java.io.*;
import java.util.*;

public class Quiz2565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[][] wires = new Integer[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			wires[i][0] = Integer.parseInt(st.nextToken());
			wires[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(wires, Comparator.comparingInt(o -> o[0]));

		// Bottom-Up 방식
		// 1. A 전기줄을 기준으로 매번 이전의 전기줄들과 비교
		// 2. 기준이 되는 전기줄의 B 지점이 이전 전기줄보다 크다면 교차하지 않는 전기줄, 최대값 갱신
		int[] dp = new int[N];
		for(int i=0; i<N; i++) {
			dp[i] = 1;

			for(int j=0; j<i; j++) {
				if(wires[i][1] > wires[j][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}

		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}

		System.out.println(N - max);
	}
}
