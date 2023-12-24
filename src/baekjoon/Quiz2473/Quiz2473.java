package baekjoon.Quiz2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 용액의 합을 먼저 구한다. -> sumOfTwoLiquids
// sumOfTwoLiquids에서 나머지 하나의 용액과 짝이 되는 값을 이분탐색으로 구한다.
// 전체 시간 복잡도: O(N^2logN)
public class Quiz2473 {
	private static int getPairValue(int start, int first, int second, int[] liquids) {
		long sumOfThreeLiquids = Long.MAX_VALUE;
		int l = start;
		int r = liquids.length-1;
		int pairValue = 0;
		while(l<=r) {
			int m = (l+r)/2;
			int currentSum = first + second + liquids[m];
			if(first != liquids[m] && second != liquids[m]) {
				if(Math.abs(currentSum) < Math.abs(sumOfThreeLiquids)) {
					pairValue = liquids[m];
					sumOfThreeLiquids = currentSum;
				}
			}
			if(currentSum > 0) r = m-1;
			else if(currentSum < 0) l = m+1;
			else return liquids[m];
		}
		return pairValue;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] liquids = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liquids);
		int ans1 = liquids[0];
		int ans2 = liquids[1];
		int ans3 = liquids[2];
		long ansAbs = Math.abs((long)liquids[0] + liquids[1] + liquids[2]);
		for(int i=0; i<N-2; i++) {
			for(int j=i+1; j<N-1; j++) {
				int pairValue = getPairValue(j+1, liquids[i], liquids[j], liquids);
				long currentAbs = (Math.abs((long)liquids[i] + liquids[j] + pairValue));
				if (currentAbs < ansAbs) {
					ansAbs = currentAbs;
					ans1 = liquids[i];
					ans2 = liquids[j];
					ans3 = pairValue;
				}
			}
		}
		System.out.println(ans1 + " " + ans2 + " " + ans3);
	}
}
