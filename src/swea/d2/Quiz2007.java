package swea.d2;

import java.util.*;

public class Quiz2007 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 각 문자열 0~9까지 반복하면서 마디 replace 수행
			// 교체된 문자열이 아닌 문자가 있다면 모두 교체될 때까지 반복
			String str = sc.next();
			for(int i=0; i<10; i++) {
				String tempWord = str.substring(0, i+1);
				if(isWord(str, tempWord)) {
					System.out.println("#" + test_case + " " + tempWord.length());
					break;
				}
			}
		}
	}
	private static boolean isWord(String str, String tempWord) {
		int wordLength = tempWord.length();
		String nextWord = str.substring(wordLength, wordLength * 2);
		return tempWord.equals(nextWord);
	}
}
