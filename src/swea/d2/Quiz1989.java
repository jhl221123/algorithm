package swea.d2;

import java.util.*;

public class Quiz1989 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 문자열 길이 짝수, 홀수를 구분
			// 짝수는 base idx: str.length()/2, base idx-1 비교부터 시작
			// 홀수는 base idx +1, -1부터 바로 시작
			String word = sc.next();
			int wordLength =  word.length();
			boolean ans = false;
			if(wordLength % 2 ==0) ans = isPalindrome(wordLength/2-1, wordLength/2, word);
			else ans = isPalindrome(wordLength/2, wordLength/2, word);

			System.out.println("#" + test_case + " "+ (ans ? 1 : 0));
		}
	}
	private static boolean isPalindrome(int left, int right, String word) {
		for(int i=left, j=right; i>=0; i--, j++) {
			if(word.charAt(i) != word.charAt(j)) return false;
		}
		return true;
	}
}
