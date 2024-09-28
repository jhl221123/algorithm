package baekjoon.Quiz1700;

import java.io.*;
import java.util.*;

public class Quiz1700 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[K];
		HashSet<Integer> set = new HashSet<>(N);
		int ans = 0;

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=0; i<K; i++) {
			if(set.size() < N) set.add(arr[i]);
			else {
				if(changeSocket(set, arr, i)) ans++;
			}
		}

		System.out.println(ans);
	}

	private static boolean changeSocket(HashSet<Integer> set, int[] arr, int s) {
		if(set.contains(arr[s])) return false;
		int maxIdx = -1;
		int removeTarget = 0;

		for(int socket : set) {
			boolean isUseNext = false;
			for(int i=s+1; i<arr.length; i++) {
				if(socket == arr[i]) {
					isUseNext = true;
					if(maxIdx < i) {
						maxIdx = i;
						removeTarget = arr[i];
					}
					break;
				}
			}
			if(!isUseNext) {
				removeTarget = socket;
				break;
			}
		}

		// 현재 소켓 모두 다음에 사용된다면, 가장 마지막에 사용되는 소켓 제거
		set.remove(removeTarget);
		set.add(arr[s]);
		return true;
	}
}
