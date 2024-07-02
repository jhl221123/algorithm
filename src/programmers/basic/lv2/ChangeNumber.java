package programmers.basic.lv2;

public class ChangeNumber {
	public static void main(String[] args) {
		int result = solution(10, 40, 5); // 2
		System.out.println(result);
	}
	static int MAX_VALUE = 2000000;
	public static int solution(int x, int y, int n) {
		int[] dp = new int[y+1];
		for(int i=0; i<x; i++) {
			dp[i] = MAX_VALUE;
		}
		dp[x] = 0;
		for(int i=x+1; i<=y; i++) {
			int num1 = i % 2 == 0 ? dp[i / 2] : MAX_VALUE;
			int num2 = i % 3 == 0 ? dp[i / 3] : MAX_VALUE;
			int num3 = i - n >= 0 ? dp[i - n]  : MAX_VALUE;
			dp[i] = Math.min(Math.min(num1 + 1, num2 + 1), num3 + 1);
		}
		return dp[y] >= MAX_VALUE ? -1 : dp[y];
	}
}
