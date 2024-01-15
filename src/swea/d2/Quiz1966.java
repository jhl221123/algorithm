package swea.d2;

import java.util.*;

public class Quiz1966 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[] nums = new int[N];
			for(int i=0; i<N; i++) {
				nums[i] = sc.nextInt();
			}
			Arrays.sort(nums);
			System.out.print("#"+test_case+" ");
			for(int num : nums) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}
