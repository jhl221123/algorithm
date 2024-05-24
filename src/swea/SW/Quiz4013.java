package swea.SW;

import java.util.*;
import java.io.*;

public class Quiz4013 {
	static int K;
	static Circle[] circles = new Circle[5];
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			K = Integer.parseInt(br.readLine());
			for(int i=1; i<=4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int[] arr = new int[8];
				for(int j=0; j<8; j++) {
					arr[j] = Integer.parseInt(st.nextToken());
				}
				Circle circle = new Circle(i, arr);
				circles[i] = circle;
			}

			while(K-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int target = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				if(target == 1) {
					int idx = target;
					boolean isSame = false;
					while(!isSame && idx < 5) {
						if(idx < 4 && circles[idx].arr[2] == circles[idx+1].arr[6]) isSame = true;
						circles[idx++].turn(dir);
						dir *= -1;
					}
				} else if(target == 2) {
					int tempDir = dir;
					int idx = target;
					boolean isSame = false;

					while(!isSame && idx < 5) {
						if(idx < 4 && circles[idx].arr[2] == circles[idx+1].arr[6]) isSame = true;
						circles[idx++].turn(tempDir);
						tempDir *= -1;
					}

					tempDir = dir;
					idx = target;
					isSame = false;
					circles[idx].turn(tempDir * -1);
					while(!isSame && idx > 0) {
						if(idx > 1 && circles[idx-1].arr[2] == circles[idx].arr[6]) isSame = true;
						circles[idx--].turn(tempDir);
						tempDir *= -1;
					}
				} else if(target == 3) {
					int tempDir = dir;
					int idx = target;
					boolean isSame = false;

					while(!isSame && idx < 5) {
						if(idx < 4 && circles[idx].arr[2] == circles[idx+1].arr[6]) isSame = true;
						circles[idx++].turn(tempDir);
						tempDir *= -1;
					}

					tempDir = dir;
					idx = target;
					isSame = false;
					circles[idx].turn(tempDir * -1);
					while(!isSame && idx > 0) {
						if(idx > 1 && circles[idx-1].arr[2] == circles[idx].arr[6]) isSame = true;
						circles[idx--].turn(tempDir);
						tempDir *= -1;
					}
				} else if(target == 4) {
					int idx = target;
					boolean isSame = false;
					while(!isSame && idx > 0) {
						if(idx > 1 && circles[idx-1].arr[2] == circles[idx].arr[6]) isSame = true;
						circles[idx--].turn(dir);
						dir *= -1;
					}
				}
			}

			int sum = 0;
			for(int i=1; i<=4; i++) {
				sum += circles[i].getScore();
			}
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.print(sb);
	}
	static class Circle {
		int num;
		int[] arr;

		public Circle(int num, int[] arr) {
			this.num = num;
			this.arr = arr;
		}

		public void turn(int d) {
			if(d==1) {
				int temp = arr[7];
				for(int i=7; i>=1; i--) {
					arr[i] = arr[i-1];
				}
				arr[0] = temp;
			} else {
				int temp = arr[0];
				for(int i=1; i<=7; i++) {
					arr[i-1] = arr[i];
				}
				arr[7] = temp;
			}
		}
		public int getScore() {
			if(arr[0] == 1 && num == 1) return 1;
			else if(arr[0] == 1 && num == 2) return 2;
			else if(arr[0] == 1 && num == 3) return 4;
			else if(arr[0] == 1 && num == 4) return 8;
			return 0;
		}
	}
}
