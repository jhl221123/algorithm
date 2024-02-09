package baekjoon.Quiz2493;

import java.util.*;
import java.io.*;

// 1. 타워 객체로 관리 -> 층수, 위치, 레이저를 수신하는 탑의 위치
// 2. 입력받은 탑은 스택에 저장 -> 순차적으로 임시 스택으로 이동
// 3. 이동 후 기존 스택 최상단 탑의 층수와 비교 -> 작다면 임시 스택에서 비우고 최상단 번호를 수신탑에 저장
// 4. 임시 스택에 값이 있다면 최상단 값보다 큰 값이 나올 때까지 반복
// 5. 모든 이동이 끝나고 임시 스택에 존재하는 남은 탑의 수신 위치는 모두 0으로 초기화

public class Quiz2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayDeque<Tower> left = new ArrayDeque<>();
		ArrayDeque<Tower> right = new ArrayDeque<>();
		Tower[] result = new Tower[N+1];
		for(int i=1; i<=N; i++) {
			int height = Integer.parseInt(st.nextToken());
			Tower aTower = new Tower(height, i, 0);
			left.addFirst(aTower);
			result[i] = aTower;
		}
		while(!left.isEmpty()) {
			right.addFirst(left.removeFirst());
			if(left.isEmpty()) break;
			while(!right.isEmpty() && (right.getFirst().getHeight() < left.getFirst().getHeight())) {
				right.removeFirst().addPairIdx(left.getFirst().getIdx());
			}
		}
		while(!right.isEmpty()) {
			right.removeFirst().addPairIdx(0);
		}
		for(int i=1; i<=N; i++) {
			System.out.print(result[i].getPairIdx() + " ");
		}
	}
}
class Tower {
	private int height;
	private int idx;
	private int pairIdx;

	public Tower(int height, int idx, int pairIdx) {
		this.height = height;
		this.idx = idx;
		this.pairIdx = pairIdx;
	}

	public int getHeight() {
		return this.height;
	}
	public int getIdx() {
		return this.idx;
	}
	public int getPairIdx() {
		return this.pairIdx;
	}
	public void addPairIdx(int pairIdx) {
		this.pairIdx = pairIdx;
	}
}
