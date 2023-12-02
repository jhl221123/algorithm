package baekjoon.Quiz10597;

import java.util.LinkedList;
import java.util.Scanner;

public class Quiz10597 {
	static boolean[] check = new boolean[51];
	static LinkedList<Integer> list = new LinkedList<>();
	static boolean end = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		recur(0, str);
	}
	private static void recur(int start, String str) {
		if(start == str.length()) {
			StringBuilder sb = new StringBuilder();
			for(int target : list) {
				sb.append(target + " ");
			}
			System.out.println(sb);
			end = true;
			return;
		}
		for(int i=start; i<start+2; i++) {
			int L = str.length();
			int N;
			if(i== L) break;
			int target = 0;
			if(i==start) target = str.charAt(i)-'0';
			else if(i==start+1) target = (str.charAt(i-1)-'0')*10 + str.charAt(i)-'0';
			if(L <=9) N = L;
			else N = (L-9)/2 +9;
			if(target > N) continue;
			if(check[target]) continue;
			check[target] = true;
			list.addLast(target);
			recur(i+1, str);
			if(end) break;
			list.removeLast();
			check[target] = false;
		}
	}
}
