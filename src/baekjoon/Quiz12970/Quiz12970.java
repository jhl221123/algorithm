package baekjoon.Quiz12970;

import java.io.*;
import java.util.*;

public class Quiz12970 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int aCount = (N+1)/2;
		int bCount = N/2;
		int maxCount = aCount * bCount;

		// 길이 N의 최대 쌍 개수보다 K가 크다면 -1을 반환
		int totalShift = maxCount - K;
		if(totalShift < 0) {
			System.out.println(-1);
			return;
		}

		int endToStart = totalShift/aCount;
		int remain = totalShift%aCount;
		StringBuilder sb = new StringBuilder();

		// B를 제일 앞으로 보낸다
		sb.append("B".repeat(Math.max(0, endToStart)));

		// 중간 지점에 B를 위치 시킨다.
		for(int i=1; i<=aCount; i++) {
			sb.append("A");
			if(sb.length() < N && remain + i == aCount) sb.append("B");
		}

		// 남은 B를 이어붙인다.
		sb.append("B".repeat(Math.max(0, bCount - endToStart - 1)));
		System.out.println(sb);
	}
}
