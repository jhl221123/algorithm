package baekjoon.Quiz2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2961_recursion {
	static int N;
	static int[] sArr;
	static int[] bArr;
	static boolean[] select;
	static int min = Integer.MAX_VALUE;
	static int count;
	public static void main(String[] args) throws IOException {
		// 신맛의 곱과 쓴맛의 합 차이가 최소가 되는 지점을 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		sArr = new int[N];
		bArr = new int[N];
		select = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			sArr[i] = Integer.parseInt(st.nextToken());
			bArr[i] = Integer.parseInt(st.nextToken());
		}
		recur(0);
		System.out.println(min);
	}
	private static void recur(int srcIdx) {
		if(srcIdx == N) {
			int sSum=1;
			int bSum=0;
			int count = 0;
			for(int i=0; i<N; i++) {
				if(select[i]) {
					sSum *= sArr[i];
					bSum += bArr[i];
					count++;
				}
			}
			if(count > 0) min = Math.min(min, Math.abs(sSum - bSum));
			return;
		}
		select[srcIdx] = true;
		recur(srcIdx + 1);
		select[srcIdx] = false;
		recur(srcIdx + 1);
	}
}

