package baekjoon.Quiz22860;

import java.util.*;
import java.io.*;

/*
Gold3: 폴더 정리 (Small) / [String, Data structure]
*/
public class Quiz22860 {
	private static Map<String, Folder> directory = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		directory.put("main", new Folder());

		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		for (int i = 0; i < N + M; i++) {
			String[] info = br.readLine().split(" ");
			String parentName = info[0];
			String childName = info[1];
			int type = Integer.parseInt(info[2]);

			directory.putIfAbsent(parentName, new Folder());
			Folder parent = directory.get(parentName);

			if (type == 1) {
				directory.putIfAbsent(childName, new Folder());
				Folder child = directory.get(childName);
				parent.subFolders.put(childName, child);
			} else {
				parent.files.add(childName);
			}
		}

		dfs(directory.get("main"));

		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			String[] query = br.readLine().split("/");
			String target = query[query.length - 1];
			Folder folder = directory.get(target);

			sb.append(folder.uqFileTypes.size()).append(" ")
				.append(folder.totalFileCount)
				.append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(Folder folder) {
		if (folder.uqFileTypes != null) {
			return;
		}
		folder.uqFileTypes = new HashSet<>(folder.files);
		folder.totalFileCount = folder.files.size();
		for (Folder sub : folder.subFolders.values()) {
			dfs(sub);
			folder.uqFileTypes.addAll(sub.uqFileTypes);
			folder.totalFileCount += sub.totalFileCount;
		}
	}

	static class Folder {
		Map<String, Folder> subFolders = new HashMap<>();
		List<String> files = new ArrayList<>();
		Set<String> uqFileTypes = null;
		int totalFileCount = 0;
	}
}
