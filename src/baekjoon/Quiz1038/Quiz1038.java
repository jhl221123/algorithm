package baekjoon.Quiz1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1038 {
	static int N;
	static int[] tgt;
	static boolean[] visited;
	static int count;
	static long answer = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for(int i=1; i<=10; i++) {
			tgt = new int[i];
			visited = new boolean[10];
			if(dfs(0)) break;
		}

		System.out.println(answer);
	}

	private static boolean dfs(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			if(count == N) {
				answer = tgtToNumber();
				return true;
			}
			count++;
			return false;
		}
		for(int i=0; i<10; i++) {
			if(visited[i]) continue;
			if(tgtIdx > 0 && tgt[tgtIdx - 1] <= i) return false;
			tgt[tgtIdx] = i;
			visited[i] = true;
			if(dfs(tgtIdx+1)) return true;
			visited[i] = false;
		}
		return false;
	}

	private static long tgtToNumber() {
		long number = 0;
		long base = 0;
		for(int i=tgt.length-1; i>=0; i--) {
			number += (long)(tgt[i] * Math.pow(10, base++));
		}
		return number;
	}
}
