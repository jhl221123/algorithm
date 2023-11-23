package baekjoon.Quiz1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(2^N)
public class Quiz1182 {
	public static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int[] cnt = new int[1];
		recur(nums, 0, s, sum, cnt);
		System.out.println(cnt[0]);
	}
	private static void recur(int[] nums, int start, int s, int sum, int[] cnt) {
		if(start == nums.length) return;
		if(sum + nums[start] == s) cnt[0]++;
		recur(nums, start+1, s, sum + nums[start], cnt);
		recur(nums, start+1, s, sum, cnt);
	}
}
