package hackerrank;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class ClimbingTheLeaderboard {
	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
		Integer[] ar = orderRank(ranked);
		List<Integer> list = new ArrayList<>();

		for(int s : player) {
			int rank = searchRank(ar, s);
			list.add(rank);
		}
		return list;
	}

	private static Integer[] orderRank(List<Integer> ranked) {
		LinkedHashSet<Integer> set = new LinkedHashSet<>(ranked);
		return new ArrayList<>(set).toArray(new Integer[set.size()]);
	}

	private static int searchRank(Integer[] ar, int s) {
		int l = 0;
		int r = ar.length - 1;
		int rank = 0;

		while(l <= r) {
			int m = (l + r) / 2;

			if(s > ar[m]) {
				r = m - 1;
				rank = m;
			} else if(s < ar[m]) {
				l = m + 1;
				rank = l;
			} else {
				return m + 1;
			}
		}

		return rank + 1;
	}
}

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
			.map(Integer::parseInt)
			.collect(toList());

		int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
			.map(Integer::parseInt)
			.collect(toList());

		List<Integer> result = ClimbingTheLeaderboard.climbingLeaderboard(ranked, player);

		bufferedWriter.write(
			result.stream()
				.map(Object::toString)
				.collect(joining("\n"))
				+ "\n"
		);

		bufferedReader.close();
		bufferedWriter.close();
	}
}