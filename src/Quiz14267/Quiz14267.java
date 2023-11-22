package Quiz14267;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 전체 시간 복잡도: O(N)
public class Quiz14267 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] parents = new int[n+1];
		List<Integer>[] tree = new ArrayList[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			tree[i] = new ArrayList<>();
			parents[i] = Integer.parseInt(st.nextToken());
			if(parents[i]==-1) continue;
			tree[parents[i]].add(i);
		}
		int[] scores = new int[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			scores[target] += score;
		}
		praise(tree, scores, 1);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<=n; i++) {
			bw.write(scores[i] + " ");
		}
		bw.flush();
	}
	private static void praise(List<Integer>[] tree, int[] scores, int node) {
		for(int child : tree[node]) {
			scores[child] += scores[node];
			praise(tree, scores, child);
		}
	}
}
