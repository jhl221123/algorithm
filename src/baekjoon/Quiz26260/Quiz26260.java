package baekjoon.Quiz26260;

import java.util.*;
import java.io.*;

/*
Gold5: 이가 빠진 이진 트리 / [Tree]
*/
public class Quiz26260 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] tree = br.readLine().split(" ");
		int r = Integer.parseInt(br.readLine());

		int[] updatedTree = new int[N];
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(tree[i]);
			updatedTree[i] = num == -1 ? r : num;
		}
		Arrays.sort(updatedTree);

		StringBuilder ans = new StringBuilder();
		postorder(updatedTree, 0, N, ans);
		System.out.println(ans);
	}

	private static void postorder(int[] tree, int left, int right, StringBuilder sb) {
		int mid = (left + right) / 2;

		if(left < right) {
			postorder(tree, left, mid, sb);
			postorder(tree, mid + 1, right, sb);
			sb.append(tree[mid]).append(" ");
		}
	}
}
