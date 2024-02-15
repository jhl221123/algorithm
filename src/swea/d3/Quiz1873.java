package swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1873 {
	static char[][] arr;
	static int H;
	static int W;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			arr = new char[H][W];
			for(int i=0; i<H; i++) {
				String str = br.readLine();
				for(int j=0; j<W; j++) {
					arr[i][j] = str.charAt(j);
				}
			}
			int N = Integer.parseInt(br.readLine());
			String cmd = br.readLine();
			for(int i=0; i<N; i++) {
				excute(cmd.charAt(i));
			}
			System.out.print("#" + test_case + " ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
		}
	}
	private static void excute(char type) {
		// 전차위치 파악
		int[] point = getTank();
		int r = point[0];
		int c = point[1];
		if(type == 'U') {
			arr[r][c] = '^';
			if(r-1 >= 0 && arr[r-1][c] == '.')  swap(r, c, r-1, c);
		} else if(type == 'D') {
			arr[r][c] = 'v';
			if(r+1 < H && arr[r+1][c] == '.') swap(r, c, r+1, c);
		} else if(type == 'L') {
			arr[r][c] = '<';
			if(c-1 >= 0 && arr[r][c-1] == '.') swap(r, c, r, c-1);
		} else if(type == 'R') {
			arr[r][c] = '>';
			if(c+1 < W && arr[r][c+1] == '.') swap(r, c, r, c+1);
		} else breakWall(arr[r][c], r, c);
	}
	private static int[] getTank() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(arr[i][j] == '<' || arr[i][j] == '^' || arr[i][j] == '>' || arr[i][j] == 'v') return new int[] {i, j};
			}
		}
		return null;
	}
	private static void swap(int r, int c, int mr, int mc) {
		char temp = arr[mr][mc];
		arr[mr][mc] = arr[r][c];
		arr[r][c] = temp;
	}
	private static void breakWall(char type, int r, int c) {
		boolean isBreak = false;
		if(type == '^') {
			while(!isBreak && r > 0 && arr[r-1][c] != '#') {
				r--;
				if (arr[r][c] == '-' || arr[r][c] == '.') continue;
				arr[r][c] = '.';
				isBreak = true;
			}
		} else if(type == '<') {
			while(!isBreak && c > 0 && arr[r][c-1] != '#') {
				c--;
				if (arr[r][c] == '-' || arr[r][c] == '.') continue;
				arr[r][c] = '.';
				isBreak = true;
			}
		} else if(type == '>') {
			while(!isBreak && c < W-1 && arr[r][c+1] != '#') {
				c++;
				if (arr[r][c] == '-' || arr[r][c] == '.') continue;
				arr[r][c] = '.';
				isBreak = true;
			}
		} else {
			while(!isBreak && r < H-1 && arr[r+1][c] != '#') {
				r++;
				if (arr[r][c] == '-' || arr[r][c] == '.') continue;
				arr[r][c] = '.';
				isBreak = true;
			}
		}
	}
}
