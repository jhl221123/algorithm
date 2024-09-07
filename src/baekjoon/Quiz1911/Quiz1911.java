package baekjoon.Quiz1911;

import java.io.*;
import java.util.*;

public class Quiz1911 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {s, e});
		}

		int start = 0;
		int answer = 0;
		while(!pq.isEmpty()) {
			int[] hole = pq.poll();
			if(hole[1] < start) continue; // 이미 덮힌 구멍이라면 패스
			start = Math.max(hole[0], start); // 이전에 덮고 남은 널판지가 현재의 구멍 일부를 덮어주는지 확인
			int l = hole[1] - start;
			int cnt = l % L == 0 ? l / L : l / L + 1;
			answer += cnt;
			start += cnt * L;
		}

		System.out.println(answer);
	}
}
