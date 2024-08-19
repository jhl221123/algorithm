package programmers.basic.lv2;

public class NormalSquare {
	public static void main(String[] args) {
		long result = solution(8, 12);// 80
		System.out.println(result);
	}
	public static long solution(int w, int h) { // 가로 + 세로 - 최대 공약수 만큼 선에 포함된다.
		return ((long)w * (long)h) - (w + h - gcd(Math.max(w, h), Math.min(w, h)));
	}
	private static int gcd(int w, int h) {
		if(h == 0) return w;
		return gcd(h, w % h);
	}
}
