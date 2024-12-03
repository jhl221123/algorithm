package baekjoon.Quiz9519;

import java.io.*;

public class Quiz9519 {
	public static final int RIGHT = 1;
	public static final int LEFT = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String changed = String.valueOf(str);

		int cycle = 0;
		int dir = str.length() % 2 == 0 ? RIGHT : LEFT;
		while(true) {
			cycle++;
			changed = change(changed, dir);
			if(changed.equals(str)) break;
		}

		X %= cycle;

		for(int i=0; i<X; i++) {
			str = change(str, dir);
		}

		System.out.println(str);
	}

	private static String change(String before, int dir) {
		StringBuilder after = new StringBuilder();
		for(int i=before.length()-1; i>=0; i--) {
			if(dir == RIGHT) {
				after.append(before.charAt(i));
			} else {
				after.insert(0, before.charAt(i));
			}
			dir *= -1;
		}
		return after.toString();
	}
}
