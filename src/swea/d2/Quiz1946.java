package swea.d2;

import java.util.*;

public class Quiz1946 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int[] alphabet = new int[26];
			char[] order = new char[26];
			for(int i=0; i<N; i++) {
				String target = sc.next();
				int cnt = sc.nextInt();
				alphabet[i] += cnt;
				order[i] = target.charAt(0);
			}
			// StringBuilder로 10개씩 끊어서 출력
			StringBuilder sb = new StringBuilder();
			int cursor = 0;
			while(cursor<26) {
				for(int i=0; i<10; i++) {
					if(cursor >= 26) break;
					if(alphabet[cursor] <= 0) {
						cursor++;
						i -= 1;
						continue;
					}
					alphabet[cursor] -= 1;
					sb.append(order[cursor]);
				}
				if(cursor<26) sb.append("\n");
			}
			System.out.println("#"+test_case);
			System.out.println(sb);
		}
	}
}
