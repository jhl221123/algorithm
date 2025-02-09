package baekjoon.Quiz4195;

import java.util.*;
import java.io.*;

/*
1. 친구 관계가 주어졌을 때, 각각 루트를 확인한다.
1-1. 루트가 같다면, 서로의 친구를 더하지 않고 현재 개수를 출력한다.
1-2. 루트가 다르다면, 서로의 친구를 더하고 더한 개수를 출력한다.
 */
public class Quiz4195 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while(T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			Map<String, Friend> map = new HashMap<>();

			for(int i=0; i<F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String n1 = st.nextToken();
				String n2 = st.nextToken();

				map.computeIfAbsent(n1, k -> new Friend(n1, n1, 1));
				map.computeIfAbsent(n2, k -> new Friend(n2, n2, 1));

				String p1 = find(n1, map);
				String p2 = find(n2, map);

				Friend pf1 = map.get(p1);
				Friend pf2 = map.get(p2);
				int currentCount = pf1.getCount();
				if(!pf1.getName().equals(pf2.getName())) {
					pf2.changeRoot(pf1.getName());
					currentCount = pf1.increaseCount(pf2.getCount());
				}

				sb.append(currentCount).append("\n");
			}
		}

		System.out.print(sb);
	}

	private static String find(String name, Map<String, Friend> map) {
		Friend f = map.get(name);
		if(f.isRoot()) {
			return f.getName();
		}
		return f.changeRoot(find(f.getRoot(), map));
	}

	static class Friend {
		private String name;
		private String root;
		private int count;

		public Friend(String name, String root, int count) {
			this.name = name;
			this.root = root;
			this.count = count;
		}

		public String changeRoot(String changedRoot) {
			return this.root = changedRoot;
		}

		public int increaseCount(int increment) {
			return this.count += increment;
		}

		public boolean isRoot() {
			return this.root.equals(this.name);
		}

		public String getName() {
			return this.name;
		}

		public String getRoot() {
			return this.root;
		}

		public int getCount() {
			return this.count;
		}
	}
}
