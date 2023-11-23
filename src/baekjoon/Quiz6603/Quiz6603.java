package baekjoon.Quiz6603;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(K!/(K-6)!*6!)
public class Quiz6603 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		while(k != 0) {
			int[] s = new int[k];
			for(int i=0; i<k; i++) {
				s[i] = Integer.parseInt(st.nextToken());
			}
			int[] lotto = new int[6];
			recur(bw, s, 0, 0, lotto);
			bw.write("\n");
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
		}
		bw.flush();
	}
	private static void recur(BufferedWriter bw, int[] s, int start, int depth, int[] lotto) throws IOException {
		if(depth == 6) {
			for(int i=0; i<6; i++) {
				bw.write(lotto[i] + " ");
			}
			bw.write("\n");
			return;
		}
		for(int i=start; i<s.length; i++) {
			lotto[depth] = s[i];
			recur(bw, s, i+1, depth+1, lotto);
			lotto[depth] = 0;
		}
	}
}
