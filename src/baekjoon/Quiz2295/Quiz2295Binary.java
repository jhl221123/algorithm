package baekjoon.Quiz2295;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// 전체 시간 복잡도: O(N*N*logN)
public class Quiz2295Binary {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Integer[] arr = new Integer[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		// O(N*N)
		Integer[] twoSum = twoSum(arr);
		// Tim Sort, O(NlogN)
		Arrays.sort(twoSum);
		Arrays.sort(arr, (o1, o2) -> o2 - o1);
		// x+y = k-z
		int ans = 0;
		// O(N*N*logN)
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				if(isExist(twoSum, arr[i] - arr[j]) && ans<arr[i]) ans = arr[i];
			}
		}
		System.out.println(ans);
	}

	public static Integer[] twoSum(Integer[] arr) {
		int N = arr.length;
		Integer[] newArr = new Integer[N*(N+1)/2];
		int idx = 0;
		// O(N*N)
		for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				newArr[idx++] = arr[i] + arr[j];
			}
		}
		return newArr;
	}

	public static boolean isExist(Integer[] arr, int target) {
		int a = 0;
		int b = arr.length-1;
		// O(logN)
		while(a<=b) {
			int m = (a + b)/2;
			if(arr[m]<target) a = m+1;
			else if(arr[m]>target) b = m-1;
			else return true;
		}
		return false;
	}
}
