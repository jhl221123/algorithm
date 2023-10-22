package baekjoon.Quiz14425;

import java.util.Arrays;
import java.util.Scanner;

// 전체 시간 복잡도: O(M*L*logN) L --> 문자열 길이
public class Quiz14425 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		String[] arr = new String[N];
		// O(N)
		for(int i=0; i<N; i++) {
			arr[i] = sc.next();
		}
		Arrays.sort(arr);
		int ans = 0;
		// O(M*logN)
		for(int i=0; i<M; i++) {
			String target = sc.next();
			boolean isExist = isExist(target, arr);
			if(isExist) ans++;
		}
		System.out.println(ans);
	}

	public static boolean isExist(String target, String[] arr) {
		int a = 0;
		int b = arr.length - 1;
		while(a<=b) {
			int m = (a+b)/2;
			if(arr[m].compareTo(target)>0) b = m - 1;
			else if(arr[m].compareTo(target)<0) a = m + 1;
			else return true;
		}
		return false;
	}
}
