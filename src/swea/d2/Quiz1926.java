package swea.d2;

import java.util.Scanner;

// 전체 시간 복잡도: O(NL), L: 수의 길이
public class Quiz1926 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i=1; i<=N; i++) {
			String target;
			String num = String.valueOf(i);
			if(countClap(num)==1) target = "-";
			else if(countClap(num)==2) target = "--";
			else if(countClap(num)==3)target = "---";
			else target = num;
			System.out.print(target + " ");
		}
	}
	private static int countClap(String num) {
		int count = 0;
		for(int i=0; i<num.length(); i++) {
			char target = num.charAt(i);
			if(target == '3' || target == '6' || target == '9') count++;
		}
		return count;
	}
}
