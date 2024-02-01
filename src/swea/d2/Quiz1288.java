package swea.d2;

import java.util.*;

public class Quiz1288 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 배수 돌면서 각 자리수에 맞는 boolean 자리에 true 할당
			int N = sc.nextInt();
			boolean[] check = new boolean[10];
			int ans = 0;
			int base = N;
			while(!isComplete(check)) {
				int currentNum = base;
				while(currentNum > 0) {
					int target = currentNum % 10;
					currentNum /= 10;
					check[target] = true;
				}
				base += N;
				ans++;
			}
			System.out.println("#"+test_case+" "+ans * N);
		}
	}
	private static boolean isComplete(boolean[] check) {
		for(boolean cond : check) {
			if(!cond) return false;
		}
		return true;
	}
}
