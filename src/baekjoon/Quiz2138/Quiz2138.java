package baekjoon.Quiz2138;

import java.io.*;

public class Quiz2138 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String origin = br.readLine();
		String target = br.readLine();
		char[] originArr1 = origin.toCharArray();
		char[] originArr2 = origin.toCharArray();
		switchOn(originArr2, 0);
		char[] targetArr = target.toCharArray();

		int cnt1 = 0;
		int cnt2 = 1;
		for(int i=1; i<N; i++) {
			if(originArr1[i-1] != targetArr[i-1]) {
				switchOn(originArr1, i);
				cnt1++;
			}
			if(originArr2[i-1] != targetArr[i-1]) {
				switchOn(originArr2, i);
				cnt2++;
			}
		}

		if(!String.valueOf(originArr1).equals(target)) cnt1 = -1;
		if(!String.valueOf(originArr2).equals(target)) cnt2 = -1;

		int ans;
		if(cnt1 == -1 || cnt2 == -1) ans = Math.max(cnt1, cnt2);
		else ans = Math.min(cnt1, cnt2);
		System.out.println(ans);
	}

	private static void switchOn(char[] arr, int idx) {
		if(idx > 0) change(arr, idx - 1);
		change(arr, idx);
		if(idx + 1 < arr.length) change(arr, idx + 1);
	}

	private static void change(char[] arr, int idx) {
		if(arr[idx] == '0') arr[idx] = '1';
		else arr[idx] = '0';
	}
}
