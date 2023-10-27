package baekjoon.Quiz12891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(P+S)
public class Quiz12891 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 전체 문자, 조건 문자 수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		// 전체 문자 입력
		String dna = br.readLine();
		int[] condition = new int[4];
		// 조건 개수 입력, O(1)
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			condition[i] = Integer.parseInt(st.nextToken());
		}
		// 1.P에 맞는 window 생성
		// 1-1.초반 P-1개의 문자 개수 배열 생성, A(0), C(1), G(2), T(3)
		// 1-2.4가지 문자의 개수를 충족할 때까지 반복
		int[] counts = new int[4];
		int ans = 0;
		// P개의 윈도우 먼저 초기화, O(P)
		initWindow(counts, P, dna);
		// 조건에 충족하면 1 증가, O(S)
		for(int i=P-1; i<S; i++) {
			counts[charToInt(dna.charAt(i))]++;
			if(isMeet(condition, counts)) ans++;
			counts[charToInt(dna.charAt(i-P+1))]--;
		}
		System.out.println(ans);
	}

	static int charToInt(char target) {
		if(target=='A') return 0;
		else if(target=='C') return 1;
		else if(target=='G') return 2;
		else if(target=='T') return 3;
		return -1;
	}
	static void initWindow(int[] counts, int P, String dna) {
		for(int i=0; i<P-1; i++) {
			counts[charToInt(dna.charAt(i))]++;
		}
	}
	static boolean isMeet(int[] condition, int[] counts) {
		for(int i=0; i<4; i++) {
			if (condition[i] > counts[i])
				return false;
		}
		return true;
	}
}
