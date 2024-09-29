package baekjoon.Quiz2812;

import java.io.*;
import java.util.*;

// 1. 스택으로 관리
// 2. 첫번째 원소 스택 추가
// 3. 다음 원소가 스택의 최상단보다 크다면, 작거나 l이 비워지거나, K를 소진할 때까지 탐색
// 4. 다음 원소가 스택의 최상단 보다 작다면, 그냥 l에 입력
// 5. K가 소진된다면, 그대로 모두 주입
public class Quiz2812 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String nums = br.readLine();
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		StringBuilder ans = new StringBuilder();

		for(int i=0; i<N; i++) {
			int num = nums.charAt(i) - '0';
			if(K > 0) {
				if(ad.isEmpty() || ad.getLast() > num) ad.addLast(num);
				else {
					int prior = ad.getLast();
					while(K > 0 && prior < num) {
						ad.removeLast();
						K--;
						if(ad.isEmpty()) break;
						prior = ad.getLast();
					}
					ad.addLast(num);
				}
			} else {
				ad.addLast(num);
			}
		}

		while(!ad.isEmpty()) {
			if(ad.size() == K) break; // 남은 K 만큼 제거
			ans.append(ad.removeFirst());
		}

		System.out.println(ans);
	}
}
