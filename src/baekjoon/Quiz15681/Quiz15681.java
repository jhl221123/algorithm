package baekjoon.Quiz15681;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N + M)
public class Quiz15681 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		List<Integer>[] tree = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			tree[i] = new ArrayList<>();
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			tree[nodeA].add(nodeB);
			tree[nodeB].add(nodeA);
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] sum = new int[N+1];
		boolean[] check = new boolean[N+1];
		getSum(tree, sum, check, R);
		for(int i=0; i<Q; i++) {
			int target = Integer.parseInt(br.readLine());
			bw.write(getSum(tree, sum, check, target) + "\n");
		}
		bw.flush();
	}
	private static int getSum(List<Integer>[] tree, int[] sum, boolean[] check, int target) {
		if(sum[target] != 0) return sum[target];
		check[target] = true;
		int result = 1;
		for(int child : tree[target]) {
			if(!check[child]) {
				result += getSum(tree, sum, check, child);
			}
		}
		sum[target] = result;
		return result;
	}
}
