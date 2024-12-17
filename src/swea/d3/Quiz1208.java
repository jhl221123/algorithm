package swea.d3;

import java.util.*;

public class Quiz1208 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			int[] arr = new int[100];
			int totalDump = sc.nextInt();
			for(int i=0; i<100; i++) {
				int target = sc.nextInt();
				arr[i] = target;
			}

			int maxIdx = 0;
			int minIdx = 0;
			for(int i=1; i<100; i++) {
				if(arr[maxIdx] < arr[i]) maxIdx = i;
				if(arr[minIdx] > arr[i]) minIdx = i;
			}

			while(totalDump-- > 0) {
				arr[maxIdx]--;
				arr[minIdx]++;
				for(int j=0; j<100; j++) {
					if(arr[maxIdx] < arr[j]) maxIdx = j;
					if(arr[minIdx] > arr[j]) minIdx = j;
				}
			}
			System.out.println("#"+test_case+" "+(arr[maxIdx]-arr[minIdx]));
		}
	}
}
