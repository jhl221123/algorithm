package baekjoon.Quiz15666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Quiz15666 {
	static int N, M;
	static int[] arr, nums;
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		nums = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		perm(0, 0);
		System.out.print(sb);
	}
	private static void perm(int s, int v) {
		if(v==M) {
			StringBuilder temp = new StringBuilder();
			for(int num : nums) {
				temp.append(num).append(" ");
			}
			if(!set.contains(temp.toString())) {
				set.add(temp.toString());
				sb.append(temp).append("\n");
			}
			return;
		}
		for(int i=s; i<N; i++) {
			nums[v] = arr[i];
			perm(i, v+1);
		}
	}
}
