package baekjoon.Quiz2800;

import java.io.*;
import java.util.*;

public class Quiz2800 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		// 괄호 쌍 배정
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		int[] nums = new int[str.length()];
		int idx = 0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '(') {
				ad.addLast(++idx);
				nums[i] = idx;
			} else if(str.charAt(i) == ')') {
				nums[i] = ad.removeLast();
			}
		}

		// 괄호 쌍으로 만들 수 있는 모든 부분집합을 확인
		// set을 활용해서 중복제거
		boolean[] visited = new boolean[idx+1];
		Set<String> set = new TreeSet<>();
		subset(1, str, nums, visited, set);
		StringBuilder sb = new StringBuilder();
		boolean flag = true;
		for(String s : set) {
			if(flag) {
				flag = false;
				continue;
			}
			sb.append(s).append("\n");
		}
		System.out.print(sb);
	}

	private static void subset(int idx, String str, int[] nums, boolean[] visited, Set<String> set) {
		if(idx == visited.length) {
			StringBuilder temp = new StringBuilder();
			for(int i=0; i<str.length(); i++) {
				if(nums[i] > 0 && !visited[nums[i]]) continue;
				temp.append(str.charAt(i));
			}
			set.add(temp.toString());
			return;
		}

		visited[idx] = true;
		subset(idx + 1, str, nums, visited, set);
		visited[idx] = false;
		subset(idx + 1, str, nums, visited, set);
	}
}
