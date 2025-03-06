package baekjoon.Quiz3020;

import java.io.*;

/*
Gold5: 개똥벌레 / [prefix sum, binary search]
1. 석순과 종유석을 서로 다른 배열로 관리, 누적합을 활용해 높이별 장애물 개수 도출
2. 두 배열을 합해 높이별 전체 장애물 개수 도출
3. 높이를 순회하며 최소값과 구간 개수를 갱신
4. 최소값과 구간 개수를 출력
*/
public class Quiz3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NH = br.readLine().split(" ");
		int N = Integer.parseInt(NH[0]);
		int H = Integer.parseInt(NH[1]);
		int[] top = new int[H + 1];
		int[] bottom = new int[H + 1];

		for(int i=0; i<N; i++) {
			int l = Integer.parseInt(br.readLine());
			if(i % 2 == 0) {
				bottom[l]++;
			} else {
				top[H - l + 1]++;
			}
		}

		int additional = 0;
		for(int i=H; i>=1; i--) {
			bottom[i] += additional;
			additional = bottom[i];
		}

		additional = 0;
		for(int i=1; i<=H; i++) {
			top[i] += additional;
			additional = top[i];
		}

		int[] obstacle = new int[H + 1];
		for(int i=1; i<=H; i++) {
			obstacle[i] = top[i] + bottom[i];
		}

		int min = 2_000_000;
		int count = 0;
		for(int i=1; i<=H; i++) {
			if(obstacle[i] < min) {
				min = obstacle[i];
				count = 1;
			} else if(obstacle[i] == min) {
				count++;
			}
		}

		System.out.println(min + " " + count);
	}
}
