package baekjoon.Quiz17135;

import java.util.*;
import java.io.*;

public class Quiz17135 {
	static int N;
	static int M;
	static int D;
	static int[][] arr;
	static int[][] temp;
	static boolean[] visit;
	static int max;
	static int[][] archer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 0);
		System.out.println(max);
	}
	private static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx == 3) {
			// 조합으로 궁수 배치
			max = Math.max(max, play());
			//System.out.println("max = " + max);
			return;
		}
		if(srcIdx == M) return;
		visit[srcIdx] = true;
		comb(srcIdx + 1, tgtIdx + 1);
		visit[srcIdx] = false;
		comb(srcIdx + 1, tgtIdx);
	}

	private static int play() {
		// 현재 조합좌표에 궁수 배치
		setArcher();
		arrToTemp();
		int kill = 0;
		while(existEnemy()) { // 적이 존재하는지 확인
			// 궁수 좌표 기준으로 적 탐색후 제거
			kill += attack();
			// 적들 한 칸 씩 전진
			moveEnemy();
			/** debug */
			//            for(int i=0; i<N; i++) {
			//                for(int j=0; j<M; j++) {
			//                    System.out.print(temp[i][j] + " ");
			//                }
			//                System.out.println();
			//            }
			//            System.out.println();
		}
		return kill;
	}

	private static int attack() {
		//        System.out.println();
		int kill = 0;
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		for(int i=0; i<3; i++) {
			int r = N;
			int c = archer[i][1];
			// 가까운 적 섬멸
			int[] e = shoot(r, c);
			if(e != null) ad.addLast(e);
		}
		// 처치된 적은 0으로 표시
		for (int[] point : ad) {
			if(temp[point[0]][point[1]] == 1) {
				temp[point[0]][point[1]] = 0;
				kill++;
			}
		}
		/** debug */
		//        System.out.println("kill = " + kill);
		return kill;
	}
	private static int[] shoot(int r, int c) {
		PriorityQueue<int[]> q = new PriorityQueue<>((int[] e1, int[] e2) -> {
			int e1Dist = Math.abs(r-e1[0]) + Math.abs(c-e1[1]);
			int e2Dist = Math.abs(r-e2[0]) + Math.abs(c-e2[1]);
			// 거리가 같다면 왼쪽 적을 처치
			if(e1Dist == e2Dist) return e1[1] - e2[1];
			// 거리가 다르다면 행이 더 큰 것
			// 거리가 다르고 행이 같다면 왼쪽 적?
			return e1Dist - e2Dist;
			//            return e2[0] - e1[0];
		});
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				if(temp[i][j] == 1 && Math.abs(r-i) + Math.abs(c-j)<=D) q.offer(new int[]{i, j});
			}
		}
		/** debug */
		//if(q.peek() != null) System.out.println("point = " + q.peek()[0] + ", " + q.peek()[1]);
		return q.isEmpty() ? null : q.poll();
	}
	private static void setArcher() {
		int[] arr = new int[3];
		int idx = 0;
		for(int i=0; i<M; i++) {
			if(visit[i]) {
				arr[idx++] = i;
			}
		}
		archer = new int[3][2];
		for(int i=0; i<3; i++) {
			archer[i][0] = N;
			archer[i][1] = arr[i];
		}
		/** debug */
		//        for(int i=0; i<3; i++) {
		//            System.out.print(archer[i][1]);
		//        }
	}
	private static void arrToTemp() {
		temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = arr[i][j];
			}
		}
	}
	private static boolean existEnemy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j] == 1) return true;
			}
		}
		return false;
	}
	private static void moveEnemy() {
		for(int i=N-2; i>=0; i--) {
			for(int j=0; j<M; j++) {
				temp[i+1][j] = temp[i][j];
			}
		}
		for(int i=0; i<M; i++) {
			temp[0][i] = 0;
		}
	}
}
