package swea.d4;

import java.io.*;
import java.util.*;

public class Quiz1210 {
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			// 2 위치 탐색 후 윗방향으로 이동
			// 이동할 때마다 좌, 우 탐색 후 1이 존재하면 해당 방향으로 이동
			// 길이 끊어지면 다시 윗방향으로 이동
			// 0행에 도달하면 해당 좌료의 열을 출력
			int[][] map = new int[100][100];
			int testNum = Integer.parseInt(br.readLine());
			int startX = 0;
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) startX = j;
				}
			}
			for(int r=99; r>=0; r--) {
				boolean flag = false;
				while(existRight(map, r, startX)) {
					flag = true;
					startX +=1;
				}
				while(!flag && existLeft(map, r, startX)) {
					startX -=1;
				}
			}
			System.out.println("#" + test_case + " " +startX);
		}
	}
	private static boolean existRight(int[][] map, int r, int startX) {
		if(startX + 1 >= 100) return false;
		return map[r][startX + 1] == 1;
	}

	private static boolean existLeft(int[][] map, int r, int startX) {
		if(startX - 1 < 0) return false;
		return map[r][startX - 1] == 1;
	}
}
