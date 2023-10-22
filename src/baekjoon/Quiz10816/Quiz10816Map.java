package baekjoon.Quiz10816;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N) + O(M)
public class Quiz10816Map {
	public static void main(String[] args) throws IOException {
		// 숫자 카드 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> aMap = new HashMap<>();
		// O(N)
		for(int i=0; i<N; i++) {
			int card = Integer.parseInt(st.nextToken());
			aMap.put(card, aMap.getOrDefault(card, 0) + 1);
		}
		// 찾아야할 숫자들 입력
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// O(M)
		for(int i=0; i<M; i++) {
			Integer count = aMap.get(Integer.parseInt(st.nextToken()));
			if(count == null) count = 0;
			sb.append(count + " ");
		}
		// 결과 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}
}
