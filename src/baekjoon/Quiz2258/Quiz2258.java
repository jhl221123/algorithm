package baekjoon.Quiz2258;

import java.io.*;
import java.util.*;

public class Quiz2258 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Meat[] meats = new Meat[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			meats[i] = new Meat(w, p);
		}

		Arrays.sort(meats, (o1, o2) -> {
			if(o1.p == o2.p) return o2.w - o1.w;
			return o1.p - o2.p;
		});

		int price = 0;
		int prior = -1;
		int total = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			total += meats[i].w;

			if(meats[i].p != prior) {
				// 가격이 다르면 교체
				price = meats[i].p;
				prior = meats[i].p;
			} else {
				// 가격이 같으면 덧셈
				price += meats[i].p;
			}

			// 무게가 넘으면 갱신
			if(total >= M) {
				min = Math.min(min, price);
			}
		}

		System.out.println(total >= M ? min : -1);
	}

	static class Meat {
		int w, p;

		public Meat(int w, int p) {
			this.w = w;
			this.p = p;
		}
	}
}
