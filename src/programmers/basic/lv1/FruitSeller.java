package programmers.basic.lv1;

import java.util.*;

public class FruitSeller {
	public static void main(String[] args) {
		int result = solution(3, 4, new int[]{1, 2, 3, 1, 2, 3, 1}); // 8
		System.out.println(result);
	}
	public static int solution(int k, int m, int[] score) {
		Integer[] wrappedScore = getIntegerArr(score);
		Arrays.sort(wrappedScore, Collections.reverseOrder());
		int answer = 0;
		for(int i=0; i<score.length; i++) {
			if((i+1) % m == 0) answer+= wrappedScore[i] * m;
		}
		return answer;
	}
	private static Integer[] getIntegerArr(int[] intArr) {
		Integer[] integerArr = new Integer[intArr.length];
		for(int i=0; i<intArr.length; i++) {
			integerArr[i] = (Integer)intArr[i];
		}
		return integerArr;
	}
}
