package baekjoon.Quiz9012;

import java.util.ArrayDeque;
import java.util.Scanner;

// 전체 시간 복잡도: O(T*50)
public class Quiz9012 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			String str = sc.nextLine();
			ArrayDeque<Character> ad = new ArrayDeque<>();
			boolean isVPC = true;
			for(int i=0; i<str.length(); i++) {
				char target = str.charAt(i);
				if(target=='(') ad.addLast(target);
				else {
					if(ad.isEmpty()) {
						isVPC = false;
						break;
					}
					ad.pop();
				}
			}
			if(!isVPC || !ad.isEmpty()) sb.append("NO\n");
			else sb.append("YES\n");
		}
		System.out.println(sb);
	}
}
