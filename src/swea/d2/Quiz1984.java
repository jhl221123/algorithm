package swea.d2;

import java.util.*;

public class Quiz1984 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[] arr = new int[10];
			int maxValue = -1;
			int minValue = 10001;
			for(int i=0; i<10; i++) {
				int num = sc.nextInt();
				arr[i] = num;
				maxValue = Math.max(maxValue, num);
				minValue = Math.min(minValue, num);
			}
			double sum = 0.0;
			int cnt = 0;
			for(int num : arr) {
				if(num == maxValue || num == minValue) {
					cnt++;
					continue;
				}
				sum += num;
			}
			int ans = Double.valueOf(Math.round(sum/(10-cnt))).intValue();
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
