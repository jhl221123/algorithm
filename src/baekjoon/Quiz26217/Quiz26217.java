package baekjoon.Quiz26217;

import java.io.*;

/*
Gold5: 별꽃의 세레나데 (Easy) / [math]
1. N까지 확률의 역수를 모두 더한다.
*/
public class Quiz26217 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		double res = 0;
		for(int i=1; i<=N; i++) {
			res += ((double)N / i);
		}

		System.out.println(res);
	}
}
