package baekjoon.Quiz1013;

import java.io.*;

public class Quiz1013 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String base = "(100+1+|01)+";
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<T; i++) {
			String str = br.readLine();
			if(str.matches(base)) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}

		System.out.print(sb);
	}
}
