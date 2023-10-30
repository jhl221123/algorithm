package baekjoon.Quiz15831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N)
public class Quiz15831 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		int j = 0;
		int ans = 0;
		int[] countArr = new int[2];
		// O(N)
		for(int i=0; i<N; i++) {
			// W를 충족하고 B를 초과하는 경우, 초과하기 전 길이로 ans 초기화
			while(j<N && countArr[0]<=B){
				countArr[charToInt(str.charAt(j))]++;
				if(isMeet(countArr, B, W)) ans = Math.max(ans, j-i+1);
				j++;
			}
			countArr[charToInt(str.charAt(i))]--;
		}
		System.out.println(ans);
	}
	static int charToInt(char target) {
		if(target=='B') return 0;
		return 1;
	}
	static boolean isMeet(int[] arr, int conditionB, int conditionW) {
		return arr[0]<=conditionB && arr[1]>=conditionW;
	}
}
