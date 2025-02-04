package baekjoon.Quiz2668;

import java.util.*;
import java.io.*;

/*
1. 배열을 순회하며 시작 번호로 끝나는 모든 사이클을 구한다.
1-1. 이미 다른 사이클에 포함된 숫자라면 탐색 종료
1-2. 현재 사이클에 포함된 숫자라면 탐색 종료
1-3. 사이클 시작 번호와 동일한 번호를 만나면 방문 배열에 기록 후 탐색 종료
2. 모든 사이클에 포함된 번호들을 출력한다.
 */
public class Quiz2668 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] sequence = new int[N + 1];

		for(int i=1; i<=N; i++) {
			sequence[i] = Integer.parseInt(br.readLine());
		}

		int max = 0;
		boolean[] visited = new boolean[N + 1];
		for(int i=1; i<=N; i++) {
			HashSet<Integer> set = new HashSet<>();

			int p = i;
			while(true) {
				if(visited[p]) break;
				if(set.contains(p)) break;
				set.add(p);

				if(sequence[p] == i) {
					for(Integer num : set) {
						visited[num] = true;
						max++;
					}
					break;
				}
				p = sequence[p];
			}
		}

		System.out.println(max);
		for(int i=1; i<=N; i++) {
			if(visited[i]) {
				System.out.println(i);
			}
		}
	}
}
