package baekjoon.Quiz1431;

import java.util.Arrays;
import java.util.Scanner;

// 정렬 순위
// 1. 짧은 것부터
// 2. 숫자의 합이 작은 것 부터
// 3. 사전순으로 더 빠른 것 부터
public class Quiz1431 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Serial[] arr = new Serial[N];
		for(int i=0; i<N; i++) {
			arr[i] = new Serial(sc.next());
		}
		// Tim-Sort: O(NlogN)
		Arrays.sort(arr);
		for(int i=0; i<N; i++) {
			System.out.println(arr[i]);
		}
	}
}

class Serial implements Comparable<Serial> {
	private String content;

	public Serial(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(Serial next) {
		if(content.length() < next.content.length()) return -1;
		else if(content.length() > next.content.length()) return 1;
		else {
			if(getSum(content) < getSum(next.content)) return -1;
			else if(getSum(content) > getSum(next.content)) return 1;
			else {
				return content.compareTo(next.content);
			}
		}
	}

	@Override
	public String toString() {
		return this.content;
	}

	private int getSum(String content) {
		int sum = 0;
		for(int i=0; i<content.length(); i++) {
			if(content.charAt(i) - '0' < 10) sum += content.charAt(i) - '0';
		}
		return sum;
	}
}
