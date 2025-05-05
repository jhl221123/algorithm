package baekjoon.Quiz15922;

import java.io.*;

/*
Gold5: 아우으 우아으이야!! / [sweeping]
1. 이전 r이 현재 l보다 크거나 같다면 두 r 중 큰 값을 유지한다.
2. 이전 r이 현재 l보다 작다면 현재까지의 길이를 합하고 l, r을 교체한다.
*/
public class Quiz15922 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) - 1;

		String[] lr = br.readLine().split(" ");
		int l = Integer.parseInt(lr[0]);
		int r = Integer.parseInt(lr[1]);
		int sum = 0;
		while(N-- > 0) {
			String[] nlnr = br.readLine().split(" ");
			int nl = Integer.parseInt(nlnr[0]);
			int nr = Integer.parseInt(nlnr[1]);

			if(nl <= r) {
				r = Math.max(r, nr);
			} else {
				sum += (r - l);
				l = nl;
				r = nr;
			}
		}

		sum += (r - l);
		System.out.println(sum);
	}
}
