package programmers.basic.lv1;

public class NumberPair {
	public static void main(String[] args) {
		String result = solution("12321", "42531"); // 321
		System.out.println(result);
	}
	public static String solution(String X, String Y) {
		int[] xArr = countNumber(X);
		int[] yArr = countNumber(Y);
		int[] count = countSameNumber(xArr, yArr);
		return getMaxNumber(count);
	}

	private static int[] countNumber(String str) {
		int[] arr = new int[10];
		for(int i=0; i<str.length(); i++) {
			arr[str.charAt(i) - '0']++;
		}
		return arr;
	}

	private static int[] countSameNumber(int[] xArr, int[] yArr) {
		int[] arr = new int[10];
		for(int i=0; i<10; i++) {
			arr[i] = Math.min(xArr[i], yArr[i]);
		}
		return arr;
	}

	private static String getMaxNumber(int[] count) {
		StringBuilder num = new StringBuilder();
		boolean onlyZero = true;
		for(int i=9; i>=0; i--) {
			if(count[i] == 0) continue;
			if(i != 0) onlyZero = false;
			for(int j=0; j<count[i]; j++) {
				num.append(i);
				if(onlyZero && i == 0) break;
			}
		}
		return num.toString().isBlank() ? "-1" : num.toString();
	}
}
