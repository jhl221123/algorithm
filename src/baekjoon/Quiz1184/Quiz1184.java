package baekjoon.Quiz1184;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 전체 시간 복잡도: O(N^4logN)
public class Quiz1184 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] acc = new int[N+1][N+1];
		// 누적합 배열 생성
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				acc[i][j] = acc[i][j-1] + acc[i-1][j] - acc[i-1][j-1] + sc.nextInt();
			}
		}
		// 각 좌표가 꼭짓점으로 사용될 때, 그때의 직사각형의 점수와 해당 점수의 개수를 관리하는 맵
		Map<Integer, Integer>[][] lt = new Map[N+1][N+1];
		Map<Integer, Integer>[][] rt = new Map[N+1][N+1];
		Map<Integer, Integer>[][] lb = new Map[N+1][N+1];
		Map<Integer, Integer>[][] rb = new Map[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				lt[i][j] = new HashMap<>();
				rt[i][j] = new HashMap<>();
				lb[i][j] = new HashMap<>();
				rb[i][j] = new HashMap<>();
			}
		}
		// 각 좌표별 꼭짓점의 점수와 점수 개수 측정
		for(int r1=1; r1<=N; r1++) {
			for(int c1=1; c1<=N; c1++) {
				for(int r2=r1; r2<=N; r2++) {
					for(int c2=c1; c2<=N; c2++) {
						int score = acc[r2][c2] - acc[r2][c1-1] - acc[r1-1][c2] + acc[r1-1][c1-1];
						lt[r1][c1].put(score, lt[r1][c1].getOrDefault(score, 0) + 1);
						rt[r1][c2].put(score, rt[r1][c2].getOrDefault(score, 0) + 1);
						lb[r2][c1].put(score, lb[r2][c1].getOrDefault(score, 0) + 1);
						rb[r2][c2].put(score, rb[r2][c2].getOrDefault(score, 0) + 1);
					}
				}
			}
		}
		int ans = 0;
		// 꼭짓점이 인접한 두 직사각형 중 점수가 같은 쌍을 구한다.
		// 오른쪽 아래와 왼쪽 위 꼭짓점이 인접하는 쌍의 개수
		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++) {
				for(Map.Entry<Integer, Integer> entry : rb[i][j].entrySet()) {
					int score = entry.getKey();
					int count = entry.getValue();
					ans += count * lt[i+1][j+1].getOrDefault(score, 0);
				}
			}
		}
		// 왼쪽 아래와 오른쪽 위 꼭짓점이 인접하는 쌍의 개수
		for(int i=1; i<N; i++) {
			for(int j=2; j<=N; j++) {
				for(Map.Entry<Integer, Integer> entry : lb[i][j].entrySet()) {
					int score = entry.getKey();
					int count = entry.getValue();
					ans += count * rt[i+1][j-1].getOrDefault(score, 0);
				}
			}
		}
		System.out.println(ans);
	}
}
