package baekjoon.Quiz1920;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N+M)
public class Quiz1920Set {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> aSet = new HashSet<>();
		// O(N)
		for(int i=0; i<N; i++) {
			aSet.add(Integer.parseInt(st.nextToken()));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// O(M*1)
		for(int i=0; i<M; i++) {
			if(aSet.contains(Integer.parseInt(st.nextToken()))) bw.write(1 + "\n");
			else  bw.write(0 + "\n");
		}
		bw.flush();
	}
}
