package programmers.basic.lv1;

public class MakeBurger {
	public static void main(String[] args) {
		int result = solution(new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1}); // 2
		System.out.println(result);
	}
	public static int solution(int[] ingredient) {
		int[] stack = new int[ingredient.length];
		int idx = 0;
		int answer = 0;
		for(int next : ingredient) {
			stack[idx++] = next;
			if(idx >= 4
				&& stack[idx - 1] == 1
				&& stack[idx - 2] == 3
				&& stack[idx - 3] == 2
				&& stack[idx - 4] == 1
			) {
				answer++;
				idx -= 4;
			}
		}
		return answer;
	}
}
