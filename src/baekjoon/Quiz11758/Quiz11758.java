package baekjoon.Quiz11758;

import java.util.*;
import java.io.*;

public class Quiz11758 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] point = new int[4][2];
		for(int i=1; i<=3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		int result = ((point[2][0] - point[1][0]) * (point[3][1] - point[1][1])) - ((point[3][0] - point[1][0])*(point[2][1] - point[1][1]));
		int ans = 0;
		if(result > 0) ans = 1;
		else if(result < 0) ans = -1;
		System.out.println(ans);
	}
}
