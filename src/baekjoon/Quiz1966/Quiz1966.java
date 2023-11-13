package baekjoon.Quiz1966;

import java.util.ArrayDeque;
import java.util.Scanner;

// 전체 시간 복잡도: O(T*N*9)
public class Quiz1966 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int N = sc.nextInt();
			int targetIdx = sc.nextInt();
			int[] priorityArr = new int[10];
			ArrayDeque<Element> ad = new ArrayDeque<>();
			// Element 이용, 중요도 배열 증가 후 큐 입력
			for(int i=0; i<N; i++) {
				int priority = sc.nextInt();
				priorityArr[priority]++;
				ad.addLast(new Element(i, priority));
			}
			int count = 0;
			while(!ad.isEmpty()) {
				Element e = ad.pop();
				if(!isTop(e.getPriority(), priorityArr)) {
					// 현재 요소(pop)보다 중요도가 더 큰 원소가 있다면 add
					ad.addLast(e);
					continue;
				}
				priorityArr[e.getPriority()]--;
				count++;
				if(e.getIdx() == targetIdx) {
					sb.append(count + "\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
	private static boolean isTop(int targetPriority, int[] priorityArr) {
		if(targetPriority < 9) {
			for(int i=targetPriority+1; i<=9; i++) {
				if(priorityArr[i]>0) return false;
			}
		}
		return true;
	}
}

class Element {
	private int idx;
	private int priority;

	public Element(int idx, int priority) {
		this.idx = idx;
		this.priority = priority;
	}

	public int getIdx() {
		return this.idx;
	}

	public int getPriority() {
		return this.priority;
	}
}