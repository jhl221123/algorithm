package baekjoon.Quiz1713;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

// 전체 시간 복잡도: O(N*totalCnt), totalCnt: 총 추천수
public class Quiz1713 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int totalCnt = sc.nextInt();
		Map<Integer, Candidate> map = new HashMap<>();
		for(int i=0; i<totalCnt; i++) {
			int number = sc.nextInt();
			// 자리가 비어있다면
			if(map.size() != N) {
				if(map.get(number) == null) {
					map.put(number, new Candidate(number, i, 1));
				} else {
					map.get(number).increaseCnt();
				}
			}
			// 자리가 가득 찼다면
			else {
				if(map.get(number) != null) {
					map.get(number).increaseCnt();
				} else {
					// 추천수가 가장 작고, 가장 먼저 추천된 후보 삭제
					removeCandidate(map);
					// 새 후보 등록
					map.put(number, new Candidate(number, i, 1));
				}
			}
		}
		// 출력
		List<Integer> numbers = new ArrayList<>(map.keySet());
		Collections.sort(numbers);
		System.out.println(numbers.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(" ")));
	}
	private static void removeCandidate(Map map) {
		Candidate removeTarget = new Candidate(0, 0, 10000);
		for(Object number : map.keySet()) {
			Candidate currentTarget = (Candidate)map.get(number);
			if(removeTarget.cnt > currentTarget.cnt) {
				removeTarget = currentTarget;
			} else if(removeTarget.cnt == currentTarget.cnt) {
				if(removeTarget.order > currentTarget.order) removeTarget = currentTarget;
			}
		}
		map.remove(removeTarget.number);
	}
}

class Candidate {
	int number;
	int order;
	int cnt;

	public Candidate(int number, int order, int cnt) {
		this.number = number;
		this.order = order;
		this.cnt = cnt;
	}

	public void increaseCnt() {
		this.cnt++;
	}
}
