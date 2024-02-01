package baekjoon.Quiz2961;

import java.io.*;
import java.util.*;

public class Quiz2961_binary_counting {
	static int N;
	static int[] sArr;
	static int[] bArr;
	static int count;
	public static void main(String[] args) throws IOException {
		// 신맛의 곱과 쓴맛의 합 차이가 최소가 되는 지점을 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		sArr = new int[N];
		bArr = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			sArr[i] = Integer.parseInt(st.nextToken());
			bArr[i] = Integer.parseInt(st.nextToken());
		}
		count = 1 << N;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i< count; i++) {
			int sSum=1;
			int bSum=0;
			for(int j=0; j<sArr.length; j++) {
				if((i & 1 << j) != 0) sSum *= sArr[j];
			}
			for (int k = 0; k < bArr.length; k++) {
				if ((i & 1 << k) != 0) bSum += bArr[k];
			}
			min = Math.min(min, Math.abs(sSum - bSum));
		}
		System.out.println(min);
	}
}
