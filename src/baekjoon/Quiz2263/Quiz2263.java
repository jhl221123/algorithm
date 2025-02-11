package baekjoon.Quiz2263;

import java.io.*;

/*
1. postorder에서 root node를 찾는다.
2. inorder에서 root node를 기준으로 left tree, right tree로 분할한다.
3. 탐색 순서대로 root node를 기록해 preorder를 도출한다.
 */
public class Quiz2263 {
	private static int[] inorder;
	private static int[] postorder;
	private static int[] inorderIdx;
	private static StringBuilder preorder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		inorder = new int[n];
		postorder = new int[n];
		inorderIdx = new int[n+1];

		String[] inorderTokens = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			inorder[i] = Integer.parseInt(inorderTokens[i]);
		}

		String[] postorderTokens = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			postorder[i] = Integer.parseInt(postorderTokens[i]);
		}

		for(int i=0; i<n; i++) {
			inorderIdx[inorder[i]] = i;
		}

		buildPreorder(0, n-1, 0, n-1);
		System.out.println(preorder);
	}

	private static void buildPreorder(int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {
		if(inorderStart > inorderEnd || postorderStart > postorderEnd) {
			return;
		}

		int rootNode = postorder[postorderEnd];
		int rootNodeInorderIdx = inorderIdx[rootNode];
		preorder.append(rootNode).append(" ");

		int leftTreeLength = rootNodeInorderIdx - inorderStart;
		buildPreorder(inorderStart, rootNodeInorderIdx - 1, postorderStart, postorderStart + leftTreeLength - 1);
		buildPreorder(rootNodeInorderIdx + 1, inorderEnd, postorderStart + leftTreeLength, postorderEnd - 1);
	}
}
