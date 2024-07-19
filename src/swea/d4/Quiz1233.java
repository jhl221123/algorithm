package swea.d4;

import java.util.*;
import java.io.*;

public class Quiz1233 {
	static Node[] tree;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=10;

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			tree = new Node[N+1];
			StringTokenizer st;
			for(int i=1; i<=N; i++) {
				String str = br.readLine();
				st = new StringTokenizer(str);
				st.nextToken();
				char v = st.nextToken().charAt(0);
				tree[i] = new Node(i, i*2, i*2+1, v);
			}
			// 아래로 재귀타면서 부모가 연산자라면 자식 노드 두개가 모두 숫자인지 확인
			recur(1);
			// 모두 체크되었는지 확인
			boolean flag = true;
			for(int i=1; i<=N; i++) {
				if(!tree[i].visited) {
					flag = false;
					break;
				}
			}
			int result = flag ? 1 : 0;
			System.out.println("#" + test_case + " " +result);
		}
	}
	private static void recur(int start) {
		if(start > N) return;
		Node parent = tree[start];
		// 리프노드가 연산자인 경우
		if(parent == null) return;
		// 숫자가 나오면 리턴
		if(Character.isDigit(parent.v)) {
			parent.visit();
			return;
		}
		// 연산자인 경우 숫자를 찾을 때까지 재귀
		parent.visit();
		recur(parent.l);
		recur(parent.r);
	}
}
class Node {
	int idx;
	int l;
	int r;
	char v;
	boolean visited=false;
	public Node(int idx, int l, int r, char v) {
		this.idx = idx;
		this.l = l;
		this.r = r;
		this.v = v;
	}
	public void visit() {
		this.visited = true;
	}
}
