package baekjoon.Quiz1920;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(M*logN)
public class Quiz1920Binary {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// O(N)
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// O(M*logN)
		for(int i=0; i<M; i++) {
			int a = 0;
			int b = N-1;
			boolean flag = false;
			int target = Integer.parseInt(st.nextToken());
			while(a<=b) {
				int m = (a+b)/2;
				if(A[m]>target) b = m-1;
				else if(A[m]<target) a = m+1;
				else if(A[m] == target) {
					flag = true;
					break;
				}
			}
			if(flag) bw.write(1 + "\n");
			else bw.write(0 + "\n");
		}
		bw.flush();
	}
}
