package baekjoon.Quiz16198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//1. LinkedList remove(i) -> num += get(i-1) * get(i+1);
//2. exit: list.size(2)
public class Quiz16198 {
	static int N, max;
	static LinkedList<Integer> list = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		dfs(0);
		System.out.println(max);
	}
	private static void dfs(int temp) {
		if(list.size() == 2) {
			max = Math.max(max, temp);
			return;
		}
		for(int i=1; i<N-1; i++) {
			if(list.size() == i+1) return;
			int energy = list.get(i-1) * list.get(i+1);
			int target = list.remove(i);
			temp += energy;
			dfs(temp);
			temp -= energy;
			list.add(i, target);
		}
	}
}
