package baekjoon.Quiz2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 전체 시간 복잡도: O(NlogN)
public class Quiz2470TreeSet {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeSet<Integer> aSet = new TreeSet<>();
		// 입력과 동시에 최적값 계산,
		int pairAbs = 2000000001;
		int ansA = 0;
		int ansB = 0;
		// O(N*logN*2)
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(st.nextToken());
			// O(logN)
			Integer[] pairValues = {aSet.ceiling(-x), aSet.floor(-x)};
			// O(2)
			for(Integer pairValue : pairValues) {
				if(pairValue == null) continue;
				int sum = x + pairValue;
				int sumAbs = Math.abs(sum);
				if(sumAbs < pairAbs) {
					pairAbs = sumAbs;
					ansA = x;
					ansB = pairValue;
				}
			}
			// 용액 입력, O(logN)
			aSet.add(x);
		}
		// 최적의 쌍 출력
		System.out.println(ansA<ansB ? ansA + " " + ansB : ansB + " " + ansA);
	}
}
