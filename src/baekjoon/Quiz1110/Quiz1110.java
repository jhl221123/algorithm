package baekjoon.Quiz1110;

import java.util.Scanner;

// 입력된 수의 각 자리수를 더한다. 0보다 작다면 0을 더한다
// 새로운 수를 만든다. 더한 수가 두 자리라면 뒷 자리를 사용한다.
// 처음 입력된 수가 나올때까지 반복한다.
// 총 반복 횟수를 구한다.
public class Quiz1110 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int target = N;
		int cnt = 0;
		do {
			int temp;
			temp = target < 10 ? target : target / 10 + target % 10;
			target = temp < 10 ? target % 10 * 10 + temp : target % 10 * 10 + temp % 10;
			cnt++;
		} while (target != N);
		System.out.println(cnt);
	}
}
