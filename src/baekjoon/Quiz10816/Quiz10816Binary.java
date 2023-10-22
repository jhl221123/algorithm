package baekjoon.Quiz10816;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(M*logN)
public class Quiz10816Binary {
	static int getTotalCount(Integer[] cards, int target) {
		// O(logN) + O(logN)
		return getEndIdx(cards, target) - getStartIdx(cards, target);
	}
	static int getStartIdx(Integer[] cards, int target) {
		int startIdx = cards.length;
		int a = 0;
		int b = cards.length-1;
		// O(logN)
		while(a<=b) {
			int m = (a+b)/2;
			if(cards[m]<target) a = m+1;
			else {
				b = m-1;
				startIdx=m;
			}
		}
		return startIdx;
	}
	static int getEndIdx(Integer[] cards, int target) {
		int endIdx = cards.length;
		int a = 0;
		int b = cards.length-1;
		// O(logN)
		while(a<=b) {
			int m = (a+b)/2;
			if(cards[m]<=target) a = m+1;
			else {
				b = m-1;
				endIdx = m;
			}
		}
		return endIdx;
	}
	public static void main(String[] args) throws IOException {
		// 숫자 카드 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer[] cards = new Integer[N];
		// O(N)
		for(int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		// primitive type: Tim Sort, O(NlogN) __ reference type: Dual-Pivot Quick Sort, O(N*N)
		Arrays.sort(cards);
		// 찾아야할 숫자들 입력
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 해당 숫자를 찾으면 개수 증가, O(M*logN)
		for(int i=0; i<M; i++) {
			int totalCount = getTotalCount(cards, Integer.parseInt(st.nextToken()));
			sb.append(totalCount + " ");
		}
		// 결과 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}
}
