package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz8458 {

	static int T, N, cnt;
	static int[] point;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			point = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			point[0] = Math.abs(a) + Math.abs(b);
			int maxLen = point[0];

			boolean stop = false;
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				point[i] = Math.abs(a) + Math.abs(b);
				maxLen = Math.max(maxLen, point[i]);

				if(point[i] % 2 != point[i-1] % 2) stop = true;
			}
			if(stop) {
				sb.append("#").append(t).append(" ").append(-1).append("\n");
				continue;
			}
			cnt = 0;
			int sum = 0;
			while(true) {
				if (sum == maxLen || (sum > maxLen && (sum - maxLen) % 2 == 0)) break;
				sum += ++cnt;
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}
}
