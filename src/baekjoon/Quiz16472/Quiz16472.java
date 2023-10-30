package baekjoon.Quiz16472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 전체 시간 복잡도: O(L)
public class Quiz16472 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		int next = 0;
		int count = 0;
		int[] frequency = new int[26];
		int ans = 0;
		for(int i=0; i<arr.length; i++) {
			while(next<arr.length) {
				if(frequency[arr[next]-'a']==0) count++;
				frequency[arr[next++]-'a']++;
				// 원상 복구
				if(count>N) {
					count--;
					frequency[arr[--next]-'a']--;
					break;
				}
			}
			ans = Math.max(ans, next-i);
			frequency[arr[i]-'a']--;
			if(frequency[arr[i]-'a']==0) count--;
		}
		System.out.println(ans);
	}
}
