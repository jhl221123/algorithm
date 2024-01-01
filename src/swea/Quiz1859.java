package swea;

import java.util.*;

// 전체 시간 복잡도: O(N)
public class Quiz1859 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[] values = new int[N];
			for(int i=0; i<N; i++) {
				values[i] = sc.nextInt();
			}
			long ans = 0;
			int maxValue = 0;
			for(int i=N-1; i>=0; i--) {
				// 마지막 날 부터 가격을 확인하는 것이 핵심
				// 현재 최댓값 보다 크다면 최댓값 갱신
				// 작다면 수익 계산 후 합산
				if(maxValue<values[i]) maxValue = values[i];
				else ans += maxValue - values[i];
			}
			System.out.println("#" + test_case + " " + ans);
		}
	}
}
