package baekjoon.Quiz11509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11509 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] balloons = new int[1000002];
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			// 현재 높이 + 1 인 풍선이 앞에 있다면, 현재 풍선은 터지고 높이 + 1인 풍선이 현재 높이가 된다.
			// 따라서 현재 높이 + 1인 풍선 개수를 하나 차감하면 최소 화살 개수를 도출할 수 있다.
			int height = Integer.parseInt(st.nextToken());
			if(balloons[height+1] > 0) balloons[height+1]--;
			balloons[height]++;
		}

		for(int i=0; i<balloons.length; i++) {
			answer += balloons[i];
		}

		System.out.println(answer);
	}
}
