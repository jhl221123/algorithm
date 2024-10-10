package baekjoon.Quiz2613;

import java.util.*;
import java.io.*;

public class Quiz2613 {

	static int N, M;
	static int[] biz;
	static List<Integer> temp = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		biz = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			biz[i] = Integer.parseInt(st.nextToken());
		}

		int l = 0;
		int r = 30001;
		int ans = 0;
		List<Integer> list = null;
		while(l <= r) {
			int m = (l + r) / 2;
			if(isPossible(m)) {
				ans = m;
				r = m - 1;
				list = new ArrayList<>(temp);
			} else {
				l = m + 1;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(ans).append("\n");
		for(int num : list) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}

	private static boolean isPossible(int limit) {
		int total = 0;
		for(int i=0; i<N; i++) {
			total++;
			if(biz[i] > limit) return false;
		}

		int sum = 0;
		int pieceCnt = 0;
		int group = 1;

		temp = new ArrayList<>();
		for(int i=0; i<N; i++) {
			// 만들 수 있는 그룹의 수가 남은 원소의 개수와 같다면 모두 자른다.
			if(total <= (M - group)) {
				if(pieceCnt > 0) temp.add(pieceCnt);
				pieceCnt = 0;
				for(int j=0; j<total; j++) {
					temp.add(1);
				}
				break;
			}
			// 만들 수 있는 그룹의 수보다 남은 원소가 많다면, 원소를 그룹으로 묶고 원소 개수를 누적
			if(sum + biz[i] > limit) {
				sum = biz[i];
				temp.add(pieceCnt);
				pieceCnt = 0;
				group++;
			} else {
				sum += biz[i];
			}
			pieceCnt++;
			total--;
		}
		if(pieceCnt > 0) temp.add(pieceCnt);

		return group <= M;
	}
}
