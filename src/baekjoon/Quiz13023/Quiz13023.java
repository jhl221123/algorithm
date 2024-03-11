package baekjoon.Quiz13023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz13023 {
	static int N, M;
	static List<List<Integer>> friend = new ArrayList<>();
	static boolean[] visit;
	static boolean done;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			friend.add(new ArrayList<>());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friend.get(a).add(b);
			friend.get(b).add(a);
		}
		visit = new boolean[N];
		//
		for(int i=0; i<N; i++) {
			visit[i] = true;
			dfs(i, 0);
			if(done) {
				System.out.println(1);
				return;
			}
			visit[i] = false;
		}
		System.out.println(0);
	}
	private static void dfs(int num, int cnt) {
		// base
		if(cnt==4) {
			done = true;
			return;
		}
		// op
		if(num == N) return;
		List<Integer> numFriends = friend.get(num);
		int size = numFriends.size();
		for(int friend : numFriends) {
			if(!visit[friend]){
				visit[friend] = true;
				dfs(friend, cnt+1);
				visit[friend] = false;
			}
		}
	}
}
