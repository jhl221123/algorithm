package baekjoon.Quiz17609;

import java.util.Scanner;

// 전체 시간 복잡도: O(T*L)
public class Quiz17609 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		String[] arr = new String[T];
		for(int i=0; i<T; i++) {
			arr[i] = sc.next();
		}
		// T만큼 반복, O(T)
		for(int i=0; i<T; i++) {
			String pall = arr[i];
			int a = 0;
			int b = pall.length()-1;
			int ans = 0;
			// O(L)
			while(a<=b) {
				if(pall.charAt(a) != pall.charAt(b)) {
					if(isPallin(pall, a, b-1) || isPallin(pall, a+1, b)) ans = 1;
					else ans = 2;
					break;
				}
				a++;
				b--;
			}
			// 출력
			System.out.println(ans);
		}
	}
	static boolean isPallin(String pall, int a, int b) {
		while(a<=b) {
			if(pall.charAt(a) != pall.charAt(b)) return false;
			a++;
			b--;
		}
		return true;
	}
}
