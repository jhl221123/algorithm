package baekjoon.Quiz14719;

import java.util.*;
import java.io.*;

public class Quiz14719 {
	static int H, W;
	static int[] rods;
	static int totalRod;
	public static void main(String[] args) throws IOException {
		input();

		// add left width
		int totalWidth = 0;
		int from = 0;
		int heightOfFrom = rods[0];
		int highestIdx = 0;
		for(int i=1; i<W; i++) {
			if(heightOfFrom < rods[i]) {
				totalWidth += (i - from) * heightOfFrom;
				from = i;
				heightOfFrom = rods[i];
				highestIdx = i;
			}
		}

		// add middle width
		totalWidth += heightOfFrom;

		// add right width
		from = W;
		heightOfFrom = rods[W-1];
		for(int i=W-2; i>=highestIdx; i--) {
			if(heightOfFrom <= rods[i]) {
				totalWidth += (from - (i + 1)) * heightOfFrom;
				from = i + 1;
				heightOfFrom = rods[i];
			}
		}

		System.out.println(totalWidth - totalRod);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		rods = new int[W];

		st = new StringTokenizer(br.readLine());
		totalRod = 0;
		for(int i=0; i<W; i++) {
			int height = Integer.parseInt(st.nextToken());
			totalRod += height;
			rods[i] = height;
		}
	}
}
