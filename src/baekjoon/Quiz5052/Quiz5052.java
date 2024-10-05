package baekjoon.Quiz5052;

import java.io.*;
import java.util.*;

public class Quiz5052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		re: while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			HashSet<String> set = new HashSet<>();
			String[] nums = new String[N];
			for(int i=0; i<N; i++) {
				String num = br.readLine();
				nums[i] = num;
				for(int j=1; j<num.length(); j++) {
					String substring = num.substring(0, j);
					set.add(substring);
				}
			}

			for(int i=0; i<N; i++) {
				if(set.contains(nums[i])) {
					sb.append("NO").append("\n");
					continue re;
				}
			}
			sb.append("YES").append("\n");
		}

		System.out.print(sb);
	}
}
