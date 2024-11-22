package baekjoon.Quiz1593;

import java.io.*;
import java.util.*;

public class Quiz1593 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int g = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		String W = br.readLine();
		String S = br.readLine();
		int[] tmp1 = new int[52];
		int[] tmp2 = new int[52];
		int cnt = 0;

		for(int i = 0; i < W.length(); i++) {
			tmp1[alphaToIdx(W.charAt(i))]++;
		}

		for(int i=0; i<g; i++) {
			tmp2[alphaToIdx(S.charAt(i))]++;
		}

		if(check(tmp1, tmp2)) cnt++;

		for(int s=0, e=g; e<S.length(); s++, e++) {
			int sIdx = alphaToIdx(S.charAt(s));
			int eIdx = alphaToIdx(S.charAt(e));
			tmp2[sIdx]--;
			tmp2[eIdx]++;
			if(check(tmp1, tmp2)) cnt++;
		}

		System.out.println(cnt);
	}

	private static boolean check(int[] tmp1, int[] tmp2) {
		for(int i=0; i<tmp1.length; i++) {
			if(tmp1[i] != tmp2[i]) return false;

		}
		return true;
	}

	private static int alphaToIdx(char alphabet) {
		int idx = 0;
		if(alphabet >= 'A' && alphabet <= 'Z') {
			idx = alphabet - 'A' + 26;
		} else if(alphabet >= 'a' && alphabet <= 'z') {
			idx = alphabet - 'a';
		}
		return idx;
	}
}
