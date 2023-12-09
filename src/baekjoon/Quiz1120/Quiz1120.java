package baekjoon.Quiz1120;

import java.util.Scanner;

public class Quiz1120 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		int result = 0;
		for(int i=0; i<=B.length()-A.length(); i++) {
			int cnt = 0;
			for(int j=0; j<A.length(); j++) {
				if(A.charAt(j)==B.charAt(i+j)) cnt++;
			}
			result = Math.max(result, cnt);
		}
		System.out.println(A.length()-result);
	}
}
