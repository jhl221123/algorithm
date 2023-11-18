package baekjoon.Quiz2841;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N)
public class Quiz2841 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		HashMap<Integer, ArrayDeque<Integer>> map = new HashMap<>();
		boolean[] arr = new boolean[7];
		int ans = 0;
		// 같은 줄이고, target이 최상단 프랫보다 크다면 push
		// 같은 줄, target이 현재 최상단 프랫보다 작다면 커질때까지 pop, push
		// 기본적으로 줄 확인 후 수행, pop, push 할때마다 ans++
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(st.nextToken());
			int plat = Integer.parseInt(st.nextToken());
			if(!arr[line]) {
				arr[line]=true;
				ArrayDeque<Integer> ad = new ArrayDeque<>();
				ad.push(plat);
				ans++;
				map.put(line, ad);
			} else {
				ArrayDeque<Integer> lineAd = map.get(line);
				if(plat > lineAd.peek()) {
					lineAd.push(plat);
					ans++;
				} else if(plat < lineAd.peek()) {
					while(plat < lineAd.peek()) {
						lineAd.pop();
						ans++;
						if(lineAd.isEmpty()) {
							lineAd.push(plat);
							ans++;
						}
					}
					if(plat > lineAd.peek()) {
						lineAd.push(plat);
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
