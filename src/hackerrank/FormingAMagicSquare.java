package hackerrank;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FormingAMagicSquare {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		List<List<Integer>> s = new ArrayList<>();

		IntStream.range(0, 3).forEach(i -> {
			try {
				s.add(
					Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt)
						.collect(toList())
				);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.formingMagicSquare(s);

		System.out.println(result);
	}

	static class Result {
		private static int[][][] magicSquare = {
			{{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
			{{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
			{{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},
			{{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
			{{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
			{{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
			{{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
			{{8, 1, 6}, {3, 5, 7}, {4, 9, 2}}
		};

		public static int formingMagicSquare(List<List<Integer>> s) {
			int min = 100;

			for(int i=0; i<8; i++) {
				int[][] cur = magicSquare[i];
				int cost = 0;

				for(int r=0; r<3; r++) {
					for(int c=0; c<3; c++) {
						cost += Math.abs(s.get(r).get(c) - cur[r][c]);
					}
				}

				min = Math.min(min, cost);
			}
			return min;
		}
	}
}
