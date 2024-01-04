package swea.d3;

import java.util.Scanner;

// 전체 시간 복잡도: O(N^2)
public class Quiz19113 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for(int test_case = 1; test_case<=TC; test_case++) {
			int N = sc.nextInt();
			long[] arr = new long[2*N];
			for(int i=0; i<2*N; i++) {
				arr[i] = sc.nextInt();
			}
			boolean[] check = new boolean[2*N];
			long[] ans = new long[N];
			int idx = 0;
			for(int i=0; i<2*N-1; i++) {
				if(check[i]) continue;
				for(int j=i+1; j<2*N; j++) {
					if(!check[j] && arr[i] * 4 / 3 == arr[j]) {
						check[j] = true;
						ans[idx++] = arr[i];
						break;
					}
				}
			}
			System.out.print("#" + test_case + " ");
			for(long price : ans) {
				System.out.print(price + " ");
			}
			System.out.println();
		}
	}
}
