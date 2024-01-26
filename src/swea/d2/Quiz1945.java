package swea.d2;

import java.util.*;

public class Quiz1945 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[] N = new int[1];
			N[0] = sc.nextInt();
			int e = getSuperScript(N, 11);
			int d = getSuperScript(N, 7);
			int c = getSuperScript(N, 5);
			int b = getSuperScript(N, 3);
			int a = getSuperScript(N, 2);
			System.out.println("#"+test_case+" "+a+" "+b+" "+c+" "+d+" "+e);
		}
	}
	private static int getSuperScript(int[] N, int value) {
		int target = N[0];
		int cnt = 0;
		while(target >= value) {
			if(target%value !=0) break;
			target /=value;
			cnt++;
		}
		N[0] = target;
		return cnt;
	}
}
