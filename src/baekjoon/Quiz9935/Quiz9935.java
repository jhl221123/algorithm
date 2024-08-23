package baekjoon.Quiz9935;

import java.util.*;
import java.io.*;

public class Quiz9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String boom = br.readLine();
		char[] stack = new char[str.length()];
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		int stackIdx = 0;
		int boomIdx = 0;

		for(int i=0; i<str.length(); i++) {
			char current = str.charAt(i);
			if(current == boom.charAt(boomIdx)) {
				stack[stackIdx++] = current;
				boomIdx++;
				if(boomIdx == boom.length()) {
					stackIdx -= boom.length();
					if(!ad.isEmpty()) boomIdx = ad.removeLast();
					else boomIdx = 0;
				}
			} else if(current == boom.charAt(0)) {
				stack[stackIdx++] = current;
				if(boomIdx == 0) boomIdx++;
				else {
					ad.addLast(boomIdx);
					boomIdx = 1;
				}
			} else {
				stack[stackIdx++] = current;
				boomIdx = 0;
				ad = new ArrayDeque<>();
			}
		}

		String answer = new String(stack, 0, stackIdx);
		if(stackIdx == 0) answer = "FRULA";
		System.out.println(answer);
	}
}
