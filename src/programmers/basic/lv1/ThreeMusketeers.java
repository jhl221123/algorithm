package programmers.basic.lv1;

public class ThreeMusketeers {
	static int answer = 0;
	static int[] nums;
	public static void main(String[] args) {
		int result = solution(new int[]{-2, 3, 0, 2, -5}); // 2
		System.out.println(result);
	}
	public static int solution(int[] number) {
		nums = number;
		comb(0, 0, 0);
		return answer;
	}
	private static void comb(int idx, int cnt, int sum) {
		if(cnt == 3) {
			if(sum == 0) answer++;
			return;
		}
		if(idx >= nums.length) return;
		sum += nums[idx];
		comb(idx + 1, cnt + 1, sum);
		sum -= nums[idx];
		comb(idx + 1, cnt, sum);
	}
}
