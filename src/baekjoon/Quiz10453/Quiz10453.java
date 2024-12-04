package baekjoon.Quiz10453;

import java.io.*;
import java.util.*;

public class Quiz10453 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] A = st.nextToken().toCharArray();
			char[] B = st.nextToken().toCharArray();

			int count = 0;
			if(A.length != B.length) sb.append(-1);
			else {
				for(int i=0; i<A.length; i++) {
					if(A[i] != B[i]) {
						int cnt = 0;
						boolean changed = false;
						for(int j=i+1; j<A.length; j++) {
							cnt++;
							if(A[j] == B[i]) {
								char temp = A[i];
								A[i] = A[j];
								A[j] = temp;
								changed = true;
								break;
							}
						}
						if(!changed) {
							sb.append(-1);
							break;
						}
						count += cnt;
					}
				}
				sb.append(count);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
