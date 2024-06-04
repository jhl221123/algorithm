package swea.d3;

import java.util.Scanner;

public class Quiz1289 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String origin = sc.next();
			// 처음부터 돌면서 바뀔때마다 카운트 증가
			char prior = '0';
			int count = 0;
			for(int i=0; i<origin.length(); i++) {
				if(origin.charAt(i) != prior) {
					count++;
					prior = origin.charAt(i);
				}
			}
			System.out.println("#" + test_case + " " + count);
		}
	}
}
