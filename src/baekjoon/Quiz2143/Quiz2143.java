package baekjoon.Quiz2143;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 각 집합의 누적합 배열을 구한다.
// (T - 한쪽 부분 집합의 합)이 다른 한 쪽에 존재하는 지 이분 탐색으로 확인
// 이중 반복문으로 모든 부분 집합 확인
// 전체 시간 복잡도: O(N^2 * log(N^2))
public class Quiz2143 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int A = sc.nextInt();
		int[] An = getArr(A, sc);
		int B = sc.nextInt();
		int[] Bn = getArr(B, sc);
		// 누적합 배열 생성
		int[] acc = getSumArr(An);
		int[] bcc = getSumArr(Bn);
		// 각 배열에 존재하는 부 배열의 합, 그에 따른 개수를 맵으로 관리
		Map<Integer, Integer> mapA = arrToMap(acc);
		Map<Integer, Integer> mapB = arrToMap(bcc);
		// 키를 활용해서 쌍을 이루는지 확인
		Integer[] pairArr = mapB.keySet().toArray(new Integer[0]);
		Arrays.sort(pairArr);
		long ans = 0;
		for (Integer target : mapA.keySet()) {
			if (isExist(T - target, pairArr)) {
				ans += (long)mapA.get(target) * mapB.get(T - target);
			}
		}
		System.out.println(ans);
	}

	private static int[] getArr(int size, Scanner sc) {
		int[] arr = new int[size +1];
		for(int i = 1; i<= size; i++) {
			arr[i] = sc.nextInt();
		}
		return arr;
	}

	private static int[] getSumArr(int[] arr) {
		int[] newArr = new int[arr.length];
		for(int i=1; i<arr.length; i++) {
			newArr[i] = newArr[i-1] + arr[i];
		}
		return newArr;
	}

	private static Map<Integer, Integer> arrToMap(int[] acc) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i< acc.length; i++) {
			for(int j = i+1; j< acc.length; j++) {
				Integer currentCnt = map.getOrDefault(acc[j] - acc[i], 0);
				map.put(acc[j]- acc[i], currentCnt+1);
			}
		}
		return map;
	}

	private static boolean isExist(int target, Integer[] bcc) {
		int l = 0;
		int r = bcc.length - 1;
		int m;
		while(l<=r) {
			m = (l+r)/2;
			if(bcc[m] == target) return true;
			else if(bcc[m] > target) r = m-1;
			else l = m+1;
		}
		return false;
	}
}
