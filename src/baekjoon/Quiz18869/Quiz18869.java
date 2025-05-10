package baekjoon.Quiz18869;

import java.util.*;
import java.io.*;

/*
Gold5: 멀티버스 2 / [sort, binary search]
*/
public class Quiz18869 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] mn = br.readLine().split(" ");
		int M = Integer.parseInt(mn[0]);
		int N = Integer.parseInt(mn[1]);
		int[][] arr = new int[M][N];
		List<Integer>[] list = new ArrayList[M];

		for(int i=0; i<M; i++) {
			Set<Integer> set = new HashSet<>();
			String[] sizes = br.readLine().split(" ");

			for(int j=0; j<N; j++) {
				int size = Integer.parseInt(sizes[j]);
				arr[i][j] = size;
				set.add(size);
			}

			list[i] = new ArrayList<>(set);
			Collections.sort(list[i]);
		}

		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = Collections.binarySearch(list[i], arr[i][j]);
			}
		}

		int count = 0;
		for(int i=0; i<M; i++) {
			for(int j=i+1; j<M; j++) {
				if(Arrays.equals(arr[i], arr[j])) {
					count++;
				}
			}
		}

		System.out.println(count);
	}
}
