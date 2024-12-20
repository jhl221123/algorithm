package baekjoon.Quiz2590;

import java.io.*;

public class Quiz2590 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		int[] papers = new int[7];
		for(int i=1; i<=6; i++) {
			papers[i] = Integer.parseInt(br.readLine());
		}

		ans += papers[6];

		ans += papers[5];
		papers[1] -= papers[5] * 11;

		ans += papers[4];
		papers[2] -= papers[4] * 5;
		if(papers[1] > 0 && papers[2] < 0) {
			papers[1] += papers[2] * 4;
		}

		ans += papers[3] / 4;
		int mod = papers[3] % 4;
		if(mod > 0) {
			ans++;
			papers[2] = Math.max(papers[2], 0);
			papers[2] -= 5 - (mod - 1) * 2;
			if(papers[2] < 0) {
				papers[1] += papers[2] * 4;
			}
			papers[1] -= 8 - mod;
		}

		if(papers[2] > 0) {
			ans += papers[2] / 9;
			int mod2 = papers[2] % 9;
			if(mod2 > 0) {
				ans++;
				papers[1] -= 36 - (mod2 * 4);
			}
		}

		while(papers[1] > 0) {
			ans++;
			papers[1] -= 36;
		}

		System.out.println(ans);
	}
}
