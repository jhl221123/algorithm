package baekjoon.Quiz2295;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// 전체 시간 복잡도: O(N*N*1)
public class Quiz2295Set {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Integer[] arr = new Integer[N];
		// O(N)
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr, (o1, o2) -> o2 - o1);
		// x+y = k-z
		Set<Integer> twoSum = twoSum(arr);
		int ans = 0;
		// O(N*N*1)
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				if(twoSum.contains(arr[i] - arr[j]) && ans<arr[i]) ans = arr[i];
			}
		}
		System.out.println(ans);
	}

	public static Set<Integer> twoSum(Integer[] arr) {
		int N = arr.length;
		Set<Integer> aSet = new HashSet<>();
		// O(N*N)
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				aSet.add(arr[i] + arr[j]);
			}
		}
		return aSet;
	}
}
