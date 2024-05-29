package baekjoon.Quiz18111;

// 1. 입력
// 2. 제일 낮은, 높은 높이 탐색 -> start ~ end
// 3. 높이가 start일 때, 총 소요시간(totalTimeAtStart)과 추가로 생긴 블럭을 B에 더한다.
// 4. 높이(next)를 1씩 올리며 end까지 총 소요시간을 측정
// 4-1. 원래 높이(map)와 비교하며 블럭을 채운다. -> 매번 B가 0보다 큰지 확인
// 4-1-1. 원래 높이가 next보다 크거나 같다면 B에서 제거 후 totalTimeAtStart - 2
// 4-1-2. 원래 높이가 next보다 작다면 B에서 제거 후 totalTimeAtStart + 1
// 4-1-3. N, M에 도달하면 result = Math.min(result, totalTimeAtStart)
// 4-1-4. N, M에 도달하지 못하면 반복문 종료 -> 채울 수 있는 블럭이 부족
// 시간 복잡도: O(N*M*H), 500*500*256 = 64,000,000

import java.util.*;
import java.io.*;

public class Quiz18111 {
	static int N, M, B, start, end, totalTimeAtStart, totalTime, height;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		input();
		initStartPoint();
		moveNextLayer();
		System.out.println(totalTime + " " + height);
	}

	private static void moveNextLayer() {
		for(int next=start+1; next<=end; next++) {
			if(!isPossibleStackNextLayer(next)) break;
			if(totalTime >= totalTimeAtStart) {
				totalTime = totalTimeAtStart;
				height = next;
			}
		}
	}

	private static boolean isPossibleStackNextLayer(int next) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(B<1) return false;
				if(map[i][j] >= next) {
					B -= 1;
					totalTimeAtStart -= 2;
				} else if(map[i][j] < next) {
					B -= 1;
					totalTimeAtStart += 1;
				} else {
					System.out.println("error!");
				}
			}
		}
		return true;
	}

	private static void initStartPoint() {
		totalTimeAtStart = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int extraBlock = map[i][j] - start;
				B += extraBlock;
				totalTimeAtStart += (extraBlock * 2);
			}
		}
		totalTime = totalTimeAtStart;
		height = start;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		start = 300;
		end = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(start > map[i][j]) start = map[i][j];
				if(end < map[i][j]) end = map[i][j];
			}
		}
	}
}
