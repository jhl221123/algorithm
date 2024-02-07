package baekjoon.Quiz1244;

import java.util.*;
import java.io.*;

public class Quiz1244 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] switchArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			switchArr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			turn(switchArr, gender, num);
		}
		for(int i=0; i<N; i++) {
			System.out.print(switchArr[i] + " ");
			if((i+1)%20==0) System.out.println();
		}
	}
	private static void turn(int[] switchArr, int gender, int number) {
		// 남자일 경우
		if(gender==1) {
			for(int i=0; i<switchArr.length; i++) {
				if((i+1)%number==0) switchArr[i] = switchArr[i] == 1 ? 0 : 1;
			}
		}
		// 여자일 경우
		else {
			int mid = number -1;
			switchArr[mid] = switchArr[mid] == 1 ? 0 : 1;
			int l = mid;
			int r = mid;
			int base = 1;
			while(true) {
				if(l-base<0 || r+base >= switchArr.length) break;
				if(switchArr[l-base]==switchArr[r+base]) {
					switchArr[l-base] = switchArr[l-base] == 1 ? 0 : 1;
					switchArr[r+base] = switchArr[r+base] == 1 ? 0 : 1;
					base += 1;
				}
				else break;
			}
		}
	}
}
