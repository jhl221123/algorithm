package baekjoon.Quiz6137;

import java.io.*;

public class Quiz6137 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = new char[N];

		for(int i=0; i<N; i++) {
			arr[i] = br.readLine().charAt(0);
		}

		int l = 0;
		int r = N-1;
		int line = 0;
		StringBuilder sb = new StringBuilder();
		while(l <= r) {
			if(arr[l] < arr[r]) {
				sb.append(arr[l]);
				l++;
			} else if(arr[l] > arr[r]){
				sb.append(arr[r]);
				r--;
			} else {
				int tl = l;
				int tr = r;
				while(true) {
					tl++;
					tr--;
					if(arr[tl] < arr[tr] || tl >= tr) {
						sb.append(arr[l]);
						l++;
						break;
					} else if(arr[tl] > arr[tr]) {
						sb.append(arr[r]);
						r--;
						break;
					}
				}
			}
			line++;
			if(line == 80) {
				line = 0;
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}
}
