package programmers.basic.lv1;

public class CalculateShortfall {
	public static void main(String[] args) {
		long result = solution(3, 20, 4); // 10
		System.out.println(result);
	}
	public static long solution(int price, int money, int count) {
		for(int i=1; i<=count; i++) {
			money -= price * i;
		}
		return (money < 0) ? Math.abs(money) : 0;
	}
}
