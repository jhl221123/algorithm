package programmers.basic.lv1;

public class CokeProblem {
	public static void main(String[] args) {
		int result = solution(2, 1, 20); // 19
		System.out.println(result);
	}
	public static int solution(int a, int b, int n) {
		int answer = 0;
		while(n >= a) {
			int next = (n / a) * b;
			n = (n % a) + next;
			answer += next;
		}
		return answer;
	}
}
