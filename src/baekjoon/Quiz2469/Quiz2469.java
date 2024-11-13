package baekjoon.Quiz2469;

import java.io.*;

public class Quiz2469 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		char[] top = new char[K];
		top[0] = 'A';
		for(int i=1; i<K; i++) {
			top[i] = (char)(top[i-1] + 1);
		}
		char[] bottom = br.readLine().toCharArray();
		char[][] ladder = new char[N][K];
		int targetLine = 0;
		for(int i=0; i<N; i++) {
			ladder[i] = br.readLine().toCharArray();
			if(ladder[i][0] == '?') targetLine = i;
		}

		char[] temp = new char[K];
		// top -> ???
		for(int i=0; i<targetLine; i++) {
			for(int j=0; j<K; j++) {
				// move right
				if(j < K-1 && ladder[i][j] == '-') {
					temp[j + 1] = top[j];
				}
				// move left
				else if(j > 0 && ladder[i][j-1] == '-') {
					temp[j - 1] = top[j];
				}
				// move x
				else {
					temp[j] = top[j];
				}
			}
			System.arraycopy(temp, 0, top, 0, K);
		}

		// bottom -> ???
		for(int i=N-1; i>targetLine; i--) {
			for(int j=0; j<K; j++) {
				// move right
				if(j < K-1 && ladder[i][j] == '-') {
					temp[j + 1] = bottom[j];
				}
				// move left
				else if(j > 0 && ladder[i][j-1] == '-') {
					temp[j - 1] = bottom[j];
				}
				// move x
				else {
					temp[j] = bottom[j];
				}
			}
			System.arraycopy(temp, 0, bottom, 0, K);
		}

		// search ???
		boolean[] checked = new boolean[K-1];
		subset(checked, 0, targetLine, top, bottom, ladder);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<K-1; i++) {
			sb.append(ladder[targetLine][i] == '?' ? 'x' : ladder[targetLine][i]);
		}
		System.out.println(sb);
	}

	private static void subset(boolean[] checked, int idx, int targetLine, char[] top, char[] bottom, char[][] ladder) {
		if(idx == checked.length) {
			if(isValid(checked)) {
				int K = top.length;
				char[] temp = new char[K];
				for(int j=0; j<K; j++) {
					// move right
					if(j < K-1 && checked[j]) {
						temp[j + 1] = top[j];
					}
					// move left
					else if(j > 0 && checked[j-1]) {
						temp[j - 1] = top[j];
					}
					// move x
					else {
						temp[j] = top[j];
					}
				}

				boolean isSame = true;
				for(int j=0; j<K; j++) {
					if(bottom[j] != temp[j]) {
						isSame = false;
						break;
					}
				}

				if(isSame) {
					for(int j=0; j<K-1; j++) {
						if(checked[j]) ladder[targetLine][j] = '-';
						else ladder[targetLine][j] = '*';
					}
				}
			}
			return;
		}

		checked[idx] = true;
		subset(checked, idx+1, targetLine, top, bottom, ladder);
		checked[idx] = false;
		subset(checked, idx+1, targetLine, top, bottom, ladder);
	}

	private static boolean isValid(boolean[] checked) {
		for(int i=0; i<checked.length; i++) {
			if(i > 0 && checked[i] && checked[i-1]) return false;
			if(i < checked.length-1 && checked[i] && checked[i+1]) return false;
		}
		return true;
	}
}
