package baekjoon.Quiz10799;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

// 전체 시간 복잡도: O(L)
public class Quiz10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		ArrayDeque<Character> ad = new ArrayDeque<>();
		char prior = ' ';
		int ans = 0;
		// 1. (가 나오면 ad에 입력
		// 2. )가 나오면 remove 후 개수 확인
		// 2-1. 이전 괄호가 (였다면 현재 ad 사이즈를 더함
		// 2-2. 이전 괄효가 )였다면 +1
		for(int i=0; i<str.length(); i++) {
			char target = str.charAt(i);
			if(str.charAt(i)=='(') ad.addLast(target);
			else {
				ad.removeLast();
				if(prior=='(') ans += ad.size();
				else ans++;
			}
			prior = target;
		}
		bw.write(String.valueOf(ans));
		bw.flush();
	}
}
