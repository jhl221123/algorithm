package baekjoon.Quiz1041;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1041 {
	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dice = new int[6];
		for(int i=0; i<6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		if(N == 1) {
			System.out.println(Arrays.stream(dice).sum() - Arrays.stream(dice).max().getAsInt());
		} else if(N == 2) {
			int s3 = minOfSum3(dice);
			int s2 = minOfSum2(dice);
			long min = (s3 * 4L) + (s2 * 4L);

			System.out.println(min);
		} else {
			int s3 = minOfSum3(dice);
			int s2 = minOfSum2(dice);
			int s1 = minOfSum1(dice);
			long min = s3 * 4L
				+ s2 * (((N-2) * 4) + ((N-1) * 4))
				+ s1 * (((N-2) * (N-2)) + ((N-2) * (N-1) * 4));

			System.out.println(min);
		}

	}

	private static int minOfSum3(int[] dice) {
		List<Integer> list = new ArrayList<>();
		list.add(dice[A] + dice[B] + dice[C]);
		list.add(dice[A] + dice[C] + dice[E]);
		list.add(dice[A] + dice[D] + dice[E]);
		list.add(dice[A] + dice[B] + dice[D]);
		list.add(dice[F] + dice[B] + dice[C]);
		list.add(dice[F] + dice[C] + dice[E]);
		list.add(dice[F] + dice[D] + dice[E]);
		list.add(dice[F] + dice[B] + dice[D]);

		return list.stream()
			.mapToInt(i -> i)
			.min()
			.getAsInt();
	}

	private static int minOfSum2(int[] dice) {
		List<Integer> list = new ArrayList<>();
		list.add(dice[A] + dice[B]);
		list.add(dice[A] + dice[C]);
		list.add(dice[A] + dice[D]);
		list.add(dice[A] + dice[E]);
		list.add(dice[F] + dice[B]);
		list.add(dice[F] + dice[C]);
		list.add(dice[F] + dice[D]);
		list.add(dice[F] + dice[E]);
		list.add(dice[B] + dice[D]);
		list.add(dice[D] + dice[E]);
		list.add(dice[C] + dice[E]);
		list.add(dice[B] + dice[C]);

		return list.stream()
			.mapToInt(i -> i)
			.min()
			.getAsInt();
	}

	private static int minOfSum1(int[] dice) {
		List<Integer> list = new ArrayList<>();
		list.add(dice[A]);
		list.add(dice[B]);
		list.add(dice[C]);
		list.add(dice[D]);
		list.add(dice[E]);
		list.add(dice[F]);

		return list.stream()
			.mapToInt(i -> i)
			.min()
			.getAsInt();
	}
}
