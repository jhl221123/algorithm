package baekjoon.Quiz1477;

import java.util.*;
import java.io.*;

public class Quiz1477 {
	static int N, M, L;
	static int[] restAreas;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		restAreas = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			restAreas[i] = Integer.parseInt(st.nextToken());
		}
		restAreas[N] = L;
		Arrays.sort(restAreas);

		int l=1;
		int r=L;
		int answer=0;
		while(l <= r) {
			int mid = (l + r) /2;
			int countOfRestArea = countRestArea(mid);
			if(countOfRestArea > M) {
				l = mid + 1;
			} else {
				answer = mid;
				r = mid - 1;
			}
		}
		System.out.println(answer);
	}

	private static int countRestArea(int target) {
		int cnt = 0;
		int current = 0;
		for(int next : restAreas) {
			int space = next - current;
			if (space > target) {
				cnt += (space / target);
				if (space % target == 0) cnt--;
			}
			current = next;
		}
		return cnt;
	}
}
