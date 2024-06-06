package baekjoon.Quiz2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//1. 순열 생성 후, 부등호 만족하는지 확인
//2. 만족한다면 최대, 최소값을 갱신
public class Quiz2529 {
	static int k;
	static char[] oper;
	static long[] nums;
	static boolean[] visit;
	static long max = 0L;
	static long min = 9999999999L;
	static long [] maxArr;
	static long [] minArr;
	public static void main(String[] args) throws IOException {
		input();
		dfs(0);
		printResult();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		oper = new char[k];
		nums = new long[k+1];
		visit = new boolean[10];
		String[] arr = br.readLine().split(" ");
		for(int i=0; i<k; i++) {
			oper[i] = arr[i].charAt(0);
		}
	}

	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for (long num : maxArr) {
			sb.append(num);
		}
		sb.append("\n");
		for (long num : minArr) {
			sb.append(num);
		}
		System.out.println(sb);
	}

	private static void dfs(int depth) {
		if(depth == k+1) {
			if(isValid()) {
				long value = arrayToNumber();
				compareMaxAndMin(value);
			}
			return;
		}
		for(int i=0; i<10; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			nums[depth] = i;
			dfs(depth + 1);
			visit[i] = false;
		}
	}

	private static void compareMaxAndMin(long value) {
		if(max < value) {
			max = value;
			maxArr = Arrays.copyOf(nums, nums.length);
		}
		if(min > value) {
			min = value;
			minArr = Arrays.copyOf(nums, nums.length);
		}
	}

	private static long arrayToNumber() {
		long value = 0;
		int v = k;
		for(int i = 0; i< k+1; i++) {
			double base = Math.pow(10, v--);
			value += (long)(base * nums[i]);
		}
		return value;
	}

	private static boolean isValid() {
		for(int i=1; i<k+1; i++) {
			char currentOper = oper[i-1];
			if(currentOper == '<' && nums[i-1] > nums[i]) return false;
			else if(currentOper == '>' && nums[i-1] < nums[i]) return false;
		}
		return true;
	}
}
