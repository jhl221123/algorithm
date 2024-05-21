package swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz3307 {
	static int T, N, len;
	static int[] input;
	static int[] LIS;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			input = new int[N];
			LIS = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			len = 0;
			for(int i=0; i<N; i++) {
				LIS[i] = 1;

				for(int j=0; j<i; j++) {
					if(input[j] < input[i] && LIS[j] >= LIS[i]) LIS[i] = LIS[j] + 1;
				}

				len = Math.max(len, LIS[i]);
			}
			sb.append("#").append(t).append(" ").append(len).append("\n");
		}
		System.out.println(sb);
	}
}
