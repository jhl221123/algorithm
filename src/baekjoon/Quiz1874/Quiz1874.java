package baekjoon.Quiz1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// 전체 시간 복잡도: O(N)
public class Quiz1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		int value = 0;
		boolean invalid = false;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int target = Integer.parseInt(br.readLine());
			while(ad.isEmpty() || ad.peek()<target) {
				ad.push(++value);
				sb.append("+\n");
			}
			if(ad.peek()==target) {
				ad.pop();
				sb.append("-\n");
			}
			else {
				invalid = true;
				break;
			}
		}
		if(!ad.isEmpty() || invalid) System.out.println("NO");
		else System.out.println(sb);
	}
}
