package baekjoon.Quiz19951;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// 전체 시간 복잡도: O(N + M)
public class Quiz19951 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		// 흙상태 입력, O(N)
		int[] origin = new int[N+1];
		for(int i=1; i<=N; i++) {
			origin[i] = sc.nextInt();
		}
		// 명령에 따른 변화 입력, O(M)
		int[] changes = new int[N+2];
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int k = sc.nextInt();
			changes[a] += k;
			changes[b+1] -= k;
		}
		// 변화에 대한 구간 합 계산, O(N)
		for(int i=0; i<=N; i++) {
			changes[i+1] += changes[i];
		}
		// 결과 도출, O(N)
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<=N; i++) {
			origin[i] += changes[i];
			bw.write(origin[i] + " ");
		}
		bw.flush();
	}
}
