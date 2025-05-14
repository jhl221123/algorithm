package baekjoon.Quiz17073;

import java.io.*;

/*
Gold5: 나무 위의 빗물 / [math]
1. Pi는 빗물(W) / 리프 노드 개수 가 된다.
*/
public class Quiz17073 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nw = br.readLine().split(" ");
		int N = Integer.parseInt(nw[0]);
		int W = Integer.parseInt(nw[1]);
		int[] edgeCount = new int[N + 1];
		for(int i=0; i<N-1; i++) {
			String[] ft = br.readLine().split(" ");
			int f = Integer.parseInt(ft[0]);
			int t = Integer.parseInt(ft[1]);
			edgeCount[f]++;
			edgeCount[t]++;
		}

		int leafCount = 0;
		for(int i=2; i<=N; i++) {
			if(edgeCount[i] == 1) leafCount++;
		}

		System.out.println((double) W / leafCount);
	}
}
