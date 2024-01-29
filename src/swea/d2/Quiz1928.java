package swea.d2;

import java.util.*;

public class Quiz1928 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String str = sc.next();
			// 각 문자를 표에 따라 숫자로 변환
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<str.length(); i++) {
				char target = str.charAt(i);
				int decodeNum = decodeChar(target);
				// 각 숫자를 6자리 2진수로 변환
				String binaryString = Integer.toBinaryString(decodeNum);
				while (binaryString.length() < 6) {
					binaryString = "0" + binaryString;
				}
				// 이진수 문자열 생성
				sb.append(binaryString);
			}
			String s = sb.toString();
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < s.length()/8; i++) {
				int a = Integer.parseInt(s.substring(8*i,(i+1)*8), 2);
				ans.append((char)a);
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
	private static int decodeChar(char target) {
		if('A' <= target && target <= 'Z') return target - 'A';
		if('a' <= target && target <= 'z') return target - 'a' + 26;
		if('0' <= target && target <= '9') return target - '0' + 52;
		if(target == '+') return 62;
		if(target == '/') return 63;
		return 0;
	}
}
