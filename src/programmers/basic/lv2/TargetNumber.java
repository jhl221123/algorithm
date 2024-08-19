package programmers.basic.lv2;

public class TargetNumber {
	public static void main(String[] args) {
		int result = solution(new int[] {4, 1, 2, 1}, 4); // 2
		System.out.println(result);
	}
	static int count;
	public static int solution(int[] numbers, int target) {
		dfs(numbers, 0, 0, target);
		return count;
	}

	private static void dfs(int[] numbers, int idx, int sum, int target) {
		if(idx == numbers.length) {
			if(sum == target) count++;
			return;
		}
		dfs(numbers, idx + 1, sum + numbers[idx], target);
		dfs(numbers, idx + 1, sum - numbers[idx], target);
	}
}
