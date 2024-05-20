package swea.d3;

import java.util.Scanner;
import java.io.FileInputStream;

public class Quiz6808 {
	static int[] temp = new int[9];
	static boolean[] visit = new boolean[9];
	static int loseCnt = 0;
	static int winCnt = 0;
	static int[] p1 = new int[9];
	static int[] p2 = new int[9];
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			boolean[] card = new boolean[19];

			for(int i=0; i<9; i++) {
				p1[i] = sc.nextInt();
				card[p1[i]] = true;
			}
			int idx = 0;
			for(int i=1; i<=18; i++) {
				if(!card[i]) p2[idx++] = i;
			}
			recur(0);
			System.out.println("#" + test_case + " " + winCnt + " " + loseCnt);
			loseCnt = 0;
			winCnt = 0;
		}
	}
	private static void recur(int idx) {
		if(idx == p2.length) {
			if(isWin()==1) winCnt++;
			else if(isWin()==2) loseCnt++;
		}
		for(int i=0; i<p2.length; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			temp[idx] = p2[i];
			recur(idx+1);
			visit[i] = false;
		}
	}
	private static int isWin() {
		int sum1 = 0;
		int sum2 = 0;
		for(int i=0; i<9; i++) {
			if(p1[i] > temp[i]) sum1+=(p1[i] + temp[i]);
			else sum2+=(p1[i] + temp[i]);
		}
		if(sum1>sum2) return 1;
		else if(sum2>sum1) return 2;
		else return 3;
	}
}
