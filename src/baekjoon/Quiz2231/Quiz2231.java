package baekjoon.Quiz2231;

import java.util.Scanner;

// 전체 시간 복잡도: O(N)
public class Quiz2231 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		for(int i=1; i<N; i++) {
			int temp = i;
			int sum = i;
			while(temp>0) {
				cnt++;
				sum += temp % 10;
				temp /= 10;
			}
			if(sum==N) {
				System.out.println("cnt: " + cnt);
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
}
