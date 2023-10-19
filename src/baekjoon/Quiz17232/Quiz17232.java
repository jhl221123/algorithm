package baekjoon.Quiz17232;

import java.util.Scanner;

// 전체 시간 복잡도: O(T*N*M)
public class Quiz17232 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int T = sc.nextInt();
		int K = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		// 바둑판 입력
		int[][] arr = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			String str = sc.next();
			for(int j=1; j<=M; j++) {
				if(str.charAt(j-1)=='*') arr[i][j]++;
			}
		}
		// 0.T만큼 반복, O(T)
		while(T-- > 0) {
			// 1.입력된 생명의 구간합 구하기, O(N*M)
			int[][] acc = new int[N+1][M+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					acc[i][j] = acc[i][j-1] + acc[i-1][j] - acc[i-1][j-1] + arr[i][j];
				}
			}
			// 2.좌표를 순회하면서 구간합을 활용해 주위 생명 개수 구하기, O(N*M)
			int sum;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					int r1 = Math.min(i+K, N);
					int c1 = Math.min(j+K, M);
					int r2 = Math.max(i-K, 1);
					int c2 = Math.max(j-K, 1);
					sum = acc[r1][c1] - acc[r1][c2-1] - acc[r2-1][c1] + acc[r2-1][c2-1];
					// 2-1.해당 좌표의 생명 존재유무에 따른 처리, O(1)
					if(arr[i][j] == 1) {
						sum--;
						// 고독, 과밀
						if(sum<a || sum>b) arr[i][j] = 0;
					} else {
						// 탄생
						if(sum>a && sum<=b) arr[i][j] = 1;
					}
				}
			}
		}
		// 결과 출력, O(N*M)
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(arr[i][j] == 1 ? "*" : ".");
			}
			System.out.println();
		}
	}
}
