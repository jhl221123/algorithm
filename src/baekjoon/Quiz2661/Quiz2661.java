package baekjoon.Quiz2661;

import java.util.Scanner;

//base
//1.start가 N이라면 종료
//recur
//1.1~3 반복
//2.start+1로 재귀 호출
public class Quiz2661 {
	static int N;
	static int[] numbers = new int[80];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		recur(0);
	}

	private static boolean recur(int start) {
		if(start==N) {
			for(int i=0; i<start; i++) {
				System.out.print(numbers[i]);
			}
			return true;
		}
		for(int i=1; i<=3; i++) {
			numbers[start] = i;
			if(!isBad(start)) {
				if(recur(start+1)) return true;
			}
		}
		return false;
	}
	private static boolean isBad(int start) {
		for(int i=1; i<=(start +1)/2; i++) {
			boolean isSame = true;
			for(int j=0; j<i; j++) {
				if(numbers[start -j] != numbers[start-j-i]) {
					isSame =false;
					break;
				}
			}
			if(isSame) return true;
		}
		return false;
	}
}
