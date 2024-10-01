package baekjoon.Quiz9576;

import java.io.*;
import java.util.*;

public class Quiz9576 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			boolean[] check = new boolean[N+1];
			WishBook[] wishBooks = new WishBook[M];
			int cnt = 0;

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				wishBooks[i] = new WishBook(s, e);
			}

			Arrays.sort(wishBooks, (o1, o2) -> {
				if(o1.e == o2.e) return o1.s - o2.s;
				return o1.e - o2.e;
			});

			for(int i=0; i<M; i++) {
				WishBook wishBook = wishBooks[i];
				for(int base=wishBook.s; base<=wishBook.e; base++) {
					if(check[base]) continue;
					check[base] = true;
					cnt++;
					break;
				}
			}

			sb.append(cnt).append("\n");
		}

		System.out.print(sb);
	}

	static class WishBook {
		int s, e;

		public WishBook(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
