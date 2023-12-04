package baekjoon.Quiz1233;

import java.util.Scanner;

// 3중 반복문으로 완전탐색
// 80크기의 배열에 빈도 체크
// 앞에서 부터 비교후 빈도가 클때만 교체
// 전체 시간 복잡도: O(S1*S2*S3)
public class Quiz1233 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int S1 = sc.nextInt();
		int S2 = sc.nextInt();
		int S3 = sc.nextInt();
		int[] sum = new int[81];

		for(int i=1; i<=S1; i++) {
			for(int j=1; j<=S2; j++) {
				for(int k=1; k<=S3; k++) {
					sum[i+j+k]++;
				}
			}
		}
		int ans = 0;
		sum[0] = -1;
		for(int i=2; i<=80; i++) {
			if(sum[i] > sum[ans]) ans = i;
		}
		System.out.println(ans);
	}
}
