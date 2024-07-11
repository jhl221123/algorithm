package programmers.kakao_blind_recruitment_2023;

import java.util.Arrays;

public class EmoticonSale {
	private static int U, E;
	private static int[][] users = {{40, 10000}, {25, 10000}};
	private static int[] emoticons = {7000, 9000};
	private static int[] rate = {10, 20, 30, 40};
	private static int[] emotionRate;
	private static int[] answer;
	public static void main(String[] args) {
		int[] result = solution(users, emoticons); // [1, 5400]
		System.out.println(Arrays.toString(result));
	}
	public static int[] solution(int[][] users, int[] emoticons) {
		// 1. 할인율 4 ^ 7 만큼 순회
		// 2. 각 순열마다 서비스 가입 수와 가격 합의 최대를 비교
		// 2-1. 기준 할인율보다 크거나 같다면 구입
		// 2-2. 구입 합계가 서비스 구독 기준보다 크거나 같다면 총 가입수 증가, 구입비 0
		// 2-3. 모든 유저의 서비스 가입 여부와 구입 가격 계산
		U = users.length;
		E = emoticons.length;
		emotionRate = new int[E];
		answer = new int[2];
		perm(0);
		return answer;
	}

	private static void perm(int idx) {
		if(idx == E) {
			// 사용자 순회
			checkUserPurchase();
			return;
		}
		for(int i=0; i<4; i++) {
			emotionRate[idx] = rate[i];
			perm(idx+1);
		}
	}

	private static void checkUserPurchase() {
		int cj = 0;
		int ctp = 0;
		for(int[] user : users) {
			int cp = 0;
			for(int i=0; i<E; i++) {
				if(emotionRate[i] >= user[0]) cp += emoticons[i] * (100-emotionRate[i]) / 100;
				if(cp >= user[1]) {
					cj++;
					cp = 0;
					break;
				}
			}
			ctp += cp;
		}
		if((answer[0] < cj) || (answer[0] == cj && answer[1] < ctp)) {
			answer[0] = cj;
			answer[1] = ctp;
		}
	}
}
