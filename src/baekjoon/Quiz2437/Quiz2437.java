package baekjoon.Quiz2437;

import java.io.*;
import java.util.*;

public class Quiz2437 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int ans = 0;
		boolean flag = true;
		if(arr[0] > 1) ans = 1;
		else if(N == 1) ans = 2;
		else {
			for(int i=1; i<N; i++) {
				if(arr[i] > arr[i-1] + ans + 1) {
					ans = ans + arr[i-1] + 1;
					flag = false;
					break;
				}
				ans += arr[i-1];
			}
			if(flag) ans += (arr[N-1] + 1);
		}

		System.out.println(ans);
	}
}
