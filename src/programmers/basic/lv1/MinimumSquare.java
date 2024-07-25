package programmers.basic.lv1;

public class MinimumSquare {
	public static void main(String[] args) {
		int result = solution(new int[][] {{60, 50}, {30, 70}, {60, 30}, {80, 40}}); // 4000
		System.out.println(result);
	}
	public static int solution(int[][] sizes) {
		int l = sizes[0][0];
		int r = sizes[0][1];
		int answer = l * r;
		for(int i=1; i<sizes.length; i++) {
			int cl = sizes[i][0];
			int cr = sizes[i][1];
			int next1 = max(l, cl) * max(r, cr);
			int next2 = max(r, cl) * max(l, cr);
			if(next1 < next2) {
				if(answer < next1) {
					answer = next1;
					l = max(l, cl);
					r = max(r, cr);
				}
			} else {
				if(answer < next2) {
					answer = next2;
					l = max(l, cr);
					r = max(r, cl);
				}
			}
		}
		return answer;
	}
	private static int max(int num1, int num2) {
		return Math.max(num1, num2);
	}
}
