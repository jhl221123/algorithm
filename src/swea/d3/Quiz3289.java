package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz3289 {
	static int T, n, m;
	static int[] parent; // index: 원소, value: 부모 원소, 최상위 부모 : index == value
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parent = new int[n+1];
			makeSet();
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int e1 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				if(cmd == 0) union(e1, e2);
				else if(cmd == 1) {
					if(findSet(e1)==findSet(e2)) sb.append("1");
					else sb.append("0");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	static void makeSet() {
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}
	}
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		//parent[py] = px;
		if(px<py) parent[py] = px;
		else parent[px] = py;
	}
}
