package baekjoon.Quiz5397;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

// 전체 시간 복잡도: O(L)
public class Quiz5397 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		// 스택 두개 사용
		// < : right 스택으로 pop
		// > : left 스택으로 pop
		// - : remove만
		// 문자는 left에 push
		ArrayDeque<Character> left = new ArrayDeque<>();
		ArrayDeque<Character> right = new ArrayDeque<>();
		while(T-- > 0) {
			String str = br.readLine();
			for(int i=0; i<str.length(); i++) {
				char target = str.charAt(i);
				if(target == '<') {
					if(!left.isEmpty()) right.addLast(left.removeLast());
				}
				else if(target == '>') {
					if(!right.isEmpty()) left.addLast(right.removeLast());
				}
				else if(target =='-') {
					if(!left.isEmpty()) left.removeLast();
				}
				else left.addLast(target);
			}
			while(!left.isEmpty()) {
				right.addLast(left.removeLast());
			}
			while(!right.isEmpty()) {
				bw.write(String.valueOf(right.removeLast()));
			}
			bw.write("\n");
		}
		bw.flush();
	}
}
