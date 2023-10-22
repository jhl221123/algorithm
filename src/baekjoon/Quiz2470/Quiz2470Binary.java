package baekjoon.Quiz2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(NlogN)
public class Quiz2470Binary {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer[] arr = new Integer[N];
		// 용액 입력, O(N)
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 입력받은 용액 오름차순 정렬(Tim Srot), O(NlogN)
		Arrays.sort(arr);
		// 정렬된 용액에서 합의 절대값의 크기가 가장 큰 첫 부분을 초기값으로 지정
		int ansA = arr[0];
		int ansB = arr[1];
		int optimalAbs = Math.abs(ansA + ansB);
		// 두 가지 용액의 합이 0에 가까운 쌍을 찾는다., O(N*logN)
		for(int i=0; i<N-1; i++) {
			// 현재 기준 용액과의 합이 최적인 용액을 찾는다., O(logN)
			int optimal = getOptimal(arr, i+1, N-1, arr[i]);
			// 기존 합의 절댓값과 현재 합의 절댓값을 비교 후, 더 최적인 쌍을 저장
			int currentOptimalAbs = Math.abs(arr[i] + optimal);
			if(optimalAbs>currentOptimalAbs) {
				optimalAbs = currentOptimalAbs;
				ansA = arr[i];
				ansB = optimal;
			}
		}
		// 최적의 쌍 출력
		System.out.println(ansA + " " + ansB);
	}

	public static int getOptimal(Integer[] arr, int start, int end, int base) {
		int optimal = arr[start];
		int optimalAbs = Math.abs(base+optimal);
		// 합이 최적이 되는 값을 찾는다., O(logN)
		while(start<=end) {
			int m = (start+end)/2;
			int sum = base + arr[m];
			// 합의 최적값이 더 작은 경우 변경
			int sumAbs = Math.abs(sum);
			if(optimalAbs>sumAbs) {
				optimal = arr[m];
				optimalAbs = sumAbs;
			}
			// 합이 양수라면 이후 더 작은 값이 나올 수 없기 때문에 끝 지점을 당긴다.
			if(sum > 0) end = m-1;
			// 합이 음수라면 이전에는 절대값이 더 작을 수 없기 때문에 시작 지점을 민다.
			else if(sum < 0) start = m+1;
			// 합이 0이라면 바로 반환한다.
			else return arr[m];
		}
		return optimal;
	}
}
