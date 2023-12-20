package baekjoon.Quiz13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 같은 숫자가 나왔다면 해당 범위 내에서 부분 수열 개수 측정
// 전체 시간 복잡도: O(N)
public class Quiz13144 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] check = new boolean[100001];
		long ans = 0;
		int r=0;
		for(int l=0; l<N; l++) {
			while(r<N) {
				if(check[arr[r]]) break;
				check[arr[r++]] = true;
			}
			ans += r - l;
			check[arr[l]]=false;
		}
		System.out.println(ans);
	}
}
