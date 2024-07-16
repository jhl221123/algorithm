package programmers.basic.lv1;

public class TemplarWeapon {
	public static void main(String[] args) {
		int result = solution(5, 3, 2); // 10
		System.out.println(result);
	}
	public static int solution(int number, int limit, int power) {
		int[] arr = new int[100001];
		for(int i=1; i<=number; i++) {
			for(int j=i; j<=number; j+=i) {
				arr[j]++;
			}
		}
		int answer = 0;
		for(int i=1; i<=number; i++) {
			if(arr[i] > limit) answer += power;
			else answer += arr[i];
		}
		return answer;
	}
}
