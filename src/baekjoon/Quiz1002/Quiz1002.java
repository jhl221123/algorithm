package baekjoon.Quiz1002;

import java.io.*;

/*
Silver3: 터렛 / [geometry]
*/
public class Quiz1002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			String[] info = br.readLine().split(" ");
			double x1 = Integer.parseInt(info[0]);
			double y1 = Integer.parseInt(info[1]);
			double r1 = Integer.parseInt(info[2]);
			double x2 = Integer.parseInt(info[3]);
			double y2 = Integer.parseInt(info[4]);
			double r2 = Integer.parseInt(info[5]);
			sb.append(calculatePointCounts(x1, y1, r1, x2, y2, r2)).append("\n");
		}

		System.out.print(sb);
	}

	private static int calculatePointCounts(double x1, double y1, double r1, double x2, double y2, double r2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		double d = Math.sqrt(dx * dx + dy * dy);

		double sumR = r1 + r2;
		double subR = Math.abs(r1 - r2);
		if(d == 0) {
			return r1 == r2 ? -1 : 0;
		}

		if(sumR < d || subR > d) {
			return 0;
		}

		if(sumR == d || subR == d) {
			return 1;
		} else {
			return 2;
		}
	}
}
