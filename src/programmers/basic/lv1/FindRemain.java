package programmers.basic.lv1;

public class FindRemain {
	public static void main(String[] args) {
		int result = solution(10); // 3
		System.out.println(result);
	}
	public static int solution(int n) {
		for(int i=1; i<n; i++) {
			if(n%i == 1) return i;
		}
		return -1;
	}
}
