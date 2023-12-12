package baekjoon.Quiz18310;

import java.util.Arrays;
import java.util.Scanner;

// 정렬 후 가운데 값 출력
// 집 개수가 짝수라면 작은 수를 출력: (N-1/2)
// 전체 시간 복잡도: O(1)
public class Quiz18310 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Integer[] arr = new Integer[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(arr[(N-1)/2]);
	}
}
