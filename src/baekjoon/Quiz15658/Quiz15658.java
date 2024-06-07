package baekjoon.Quiz15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1. 입력
//2. 조합으로 n-1개 연산자를 선택 -> 배열에 관리
//3. 현재 선택된 연산자들로 연산 수행 -> 이후 최소, 최대 갱신
public class Quiz15658 {
	static int N, operSize;
	static int[] nums;
	static int[] operator = new int[4];
	static int[] selected;
	static int max = -1000000000;
	static int min = 1000000000;
	public static void main(String[] args) throws IOException {
		input();
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		selected = new int[N-1];
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
			operSize += operator[i];
		}
	}

	private static void dfs(int d) {
		if(d == N-1) {
			int result = operation();
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		for(int i=0; i<4; i++) {
			if(operator[i] < 1) continue;
			operator[i] -= 1;
			selected[d] = i;
			dfs(d + 1);
			operator[i] += 1;
		}
	}
	private static int operation() {
		int prior = nums[0];
		for(int i=1; i<N; i++) {
			if(selected[i-1] == 0) prior += nums[i];
			else if(selected[i-1] == 1) prior -= nums[i];
			else if(selected[i-1] == 2) prior *= nums[i];
			else prior /= nums[i];
		}
		return prior;
	}
}
