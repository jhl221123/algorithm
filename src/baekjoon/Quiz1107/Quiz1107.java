package baekjoon.Quiz1107;

import java.util.Scanner;

// 전체 시간 복잡도: O(N*logN)
public class Quiz1107 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		boolean[] isBroken = new boolean[10];
		for(int i=0; i<M; i++) {
			isBroken[sc.nextInt()] = true;
		}
		int ans = (N > 100) ? N - 100 : 100 - N;
		for(int i=0; i<ans; i++) {
			int[] numberToMake = {N + i, N - i};
			for(int number : numberToMake) {
				if(number >= 0 && canMakeNumber(number, isBroken)) {
					ans = Math.min(ans, Integer.toString(number).length() + i);
				}
			}
		}
		System.out.println(ans);
	}
	private static boolean canMakeNumber(int target, boolean[] isBroken) {
		if(target == 0) return !isBroken[0];
		while(target > 0) {
			if(isBroken[target % 10]) return false;
			target /= 10;
		}
		return true;
	}
}
