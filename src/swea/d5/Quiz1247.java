package swea.d5;

import java.util.*;
import java.io.*;

public class Quiz1247 {
	static int N;
	static int[][] customer;
	static int[][] tgt;
	static boolean[] visit;
	static int[] start;
	static int[] end;
	static int sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			sum = Integer.MAX_VALUE;
			// 우선순위 큐에 좌표 입력, 마지막 좌표는 집을 추가
			// 큐에서 좌표하나씩 꺼내서 현재 좌표와의 거리를 합계에 누적한다.
			// 현재 좌표는 회사부터 시작해서 다음 고객 좌표로 갱신
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			//            PriorityQueue<int[]> customer = new PriorityQueue<>((int[] p1, int[] p2) -> {
			//                int a = Math.abs(start[0] - p1[0]) + Math.abs(start[1] - p1[1]);
			//                int b = Math.abs(start[0] - p2[0]) + Math.abs(start[1] - p2[1]);
			//                if(a > b)  return Arrays.compare(p2, p1);
			//                else return Arrays.compare(p1, p2);
			//            });
			customer = new int[N][2];
			tgt = new int[N][2];
			visit = new boolean[N];
			for(int i=0; i<N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			System.out.println("#" + test_case + " " + sum);
		}
	}
	private static void dfs(int idx) {
		if(idx == N) {
			sum = Math.min(sum, getSum());
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				tgt[idx] = customer[i];
				dfs(idx+1);
				visit[i] = false;
			}
		}
	}

	private static int getSum() {
		int sum = 0;
		int sr = start[0];
		int sc = start[1];
		for(int i=0; i<N; i++) {
			sum += Math.abs(sr - tgt[i][0]) + Math.abs(sc - tgt[i][1]);
			sr = tgt[i][0];
			sc = tgt[i][1];
		}
		sum += Math.abs(sr - end[0]) + Math.abs(sc - end[1]);
		return sum;
	}
}
