package baekjoon.Quiz2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(NlogN + N)
public class Quiz2470TwoPointer {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 용액 입력, O(N)
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 오름차순으로 용액 정렬, O(NlogN)
		Arrays.sort(arr);
		// 1.오른쪽이 왼쪽보다 크면 반복, O(N)
		// 1-1.정렬된 용액의 시작과 끝에서 두 수의 합이 양수면 오른쪽 감소, 음수면 왼쪽 감소, 같으면 반환
		// 1-2.현재 값보다 더 최적이면 교체
		int targetA = arr[0];
		int targetB = arr[1];
		int sum = targetA + targetB;
		int abs = Math.abs(sum);
		int i=0;
		int j=N-1;
		while(i<j) {
			int currentSum = arr[i] + arr[j];
			int currentAbs = Math.abs(currentSum);
			if(currentAbs<abs) {
				abs = currentAbs;
				targetA = arr[i];
				targetB = arr[j];
			}
			if(currentSum>0) j--;
			else if(currentSum<0) i++;
			else {
				targetA = arr[i];
				targetB = arr[j];
				break;
			}
		}
		System.out.println(targetA + " " + targetB);
	}
}
