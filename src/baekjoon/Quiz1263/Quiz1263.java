package baekjoon.Quiz1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz1263 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[][] arr = new Integer[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			arr[i][0] = T;
			arr[i][1] = S;
		}
		Arrays.sort(arr, ((o1, o2) -> o2[1] - o1[1]));

		int answer = arr[0][1] - arr[0][0];
		for(int i=1; i<N; i++) {
			if(answer > arr[i][1]) answer = arr[i][1];
			answer -= arr[i][0];
			if(answer < 0) {
				answer = -1;
				break;
			}
		}

		System.out.println(answer);
	}
}
