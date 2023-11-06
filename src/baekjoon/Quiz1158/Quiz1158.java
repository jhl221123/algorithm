package baekjoon.Quiz1158;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// 전체 시간 복잡도: O(N*N)
public class Quiz1158 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			list.add(i);
		}

		int[] ans = new int[N];
		int idx = 0;
		// O(N*N)
		for(int i=0; i<N; i++) {
			idx = (idx + K - 1) % list.size();
			ans[i] = list.remove(idx);
		}

		String result = Arrays.stream(ans)
			.mapToObj(String::valueOf)
			.collect(Collectors.joining(", "));
		System.out.println("<" + result + ">");
	}
}
