package programmers.basic.lv2;

import java.util.Arrays;

/*
	변수
	int[] count

	기능
	1. 좌표 압축: weights를 순회하며 count 배열에 각 무게별 개수 관리
	2. 균형 여부 확인: count에서 두 개의 무게를 받아 균형을 이룰 수 있다면 true 반환
		2-1. 두 무게 모두 count가 0 이상이라면 확인
		2-2. 이때, 같은 무게를 비교한다면, count는 2 이상이어야만 한다.
	3. 짝꿍 쌍 누적: 균형을 이루는 두 개의 무게, 각 개수의 곱을 짝꿍 수에 누적
		3-2. 같은 무게의 짝궁 수는 (count[target] - 1)의 누적합
*/
public class SeesawPairing {
	public static void main(String[] args) {
		long result = solution(new int[] {100,180,360,100,270}); // 4
		System.out.println(result);
	}
	public static long solution(int[] weights) {
		long[] count = new long[901];
		long answer = 0;

		for(int weight : weights) {
			count[weight-100]++;
		}
		for(int i=0; i<=900; i++) {
			if(count[i] == 0) continue;
			for(int j=i; j<=900; j++) {
				if(count[j] == 0) continue;
				if(isPair(i+100, j+100)) {
					if(i == j && count[i] > 1) answer += getCumulativeSum(count[i]-1);
					else if(i != j) answer += (count[i] * count[j]);
				}
			}
		}
		return answer;
	}
	private static boolean isPair(int num1, int num2) {
		for(int i=2; i<=4; i++) {
			for(int j=2; j<=4; j++) {
				if((num1 * i) == (num2 * j)) return true;
			}
		}
		return false;
	}
	private static long getCumulativeSum(long num) {
		if(num == 1) return 1;
		else if(num % 2 == 0) return (1 + num) * (num / 2);
		else return ((1 + num) * (num / 2)) + ((num + 1) / 2);
	}
}
