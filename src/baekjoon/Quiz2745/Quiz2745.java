package baekjoon.Quiz2745;

import java.util.Scanner;

// 각 자리수를 숫자로 변환
// 진법에 맞게 곱한 후 덧셈, a * 36^2 + b * 36^1 + c * 36^0
// 시간 복잡도: O(L)
public class Quiz2745 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.next();
		int B = sc.nextInt();
		int square = 1;
		int ans = 0;
		for(int i=0; i<N.length(); i++) {
			int point = N.length()-1-i;
			int num = getNumber(N.charAt(point));
			ans += num*square;
			square *= B;
		}
		System.out.println(ans);
	}
	private static int getNumber(char target) {
		if(target - '0' <= 9) return target - '0';
		else return target - 'A' + 10;
	}
}
