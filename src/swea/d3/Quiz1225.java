package swea.d3;

import java.util.*;

public class Quiz1225 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			ArrayDeque<Integer> ad = new ArrayDeque<>();
			for (int i = 0; i < 8; i++) {
				ad.addLast(sc.nextInt());
			}
			// base는 1씩 증가
			// removeFirst 후 base를 빼고 addLast
			// 연산 결과가 0이라면 맨뒤로 옮기고 종료
			int base = 1;
			int temp = 1;
			while (temp > 0) {
				temp = ad.removeFirst() - base++;
				base %= 6;
				if(base == 0) base = 1;
				if(temp < 0) temp = 0;
				ad.addLast(temp);
			}
			System.out.print("#" + test_case + " ");
			for (int target : ad) {
				System.out.print(target + " ");
			}
			System.out.println();
		}
	}
}
