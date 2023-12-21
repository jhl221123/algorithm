package baekjoon.Quiz1764;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

// 전체 시간 복잡도: O(Max(N, M))
public class Quiz1764_Set {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		Set<String> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			set.add(sc.next());
		}
		List<String> list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			String target = sc.next();
			if(set.contains(target)) {
				list.add(target);
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for(String target : list) {
			System.out.println(target);
		}
	}
}
