package baekjoon.Quiz20207;

import java.io.*;
import java.util.*;

public class Quiz20207 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Schedule[] schedules = new Schedule[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			schedules[i] = new Schedule(s, e + 1);
		}

		// 일정은 시작일 오름차순, 종료일 내림차순으로 정렬
		Arrays.sort(schedules, (o1, o2) -> {
			if(o1.s == o2.s) return o2.e - o1.e;
			return o1.s - o2.s;
		});

		// 최소힙으로 현재 블럭의 가장 빠른 종료일 관리
		// 블럭별 시작 지점과 끝 지점은 sl, el로 관리
		// 블럭의 높이는 max로 관리
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int sl = 400;
		int el = 0;
		int max = 0;
		int ans = 0;
		for(int i=0; i<N; i++) {
			if(pq.isEmpty() || pq.peek() > schedules[i].s) {
				pq.offer(schedules[i].e);
				sl = Math.min(sl, schedules[i].s);
				el = Math.max(el, schedules[i].e);
			}
			else if(pq.peek() == schedules[i].s) {
				pq.poll();
				pq.offer(schedules[i].e);
				el = Math.max(el, schedules[i].e);
			} else if(pq.peek() < schedules[i].s) {
				if(el < schedules[i].s) {
					ans += max * (el - sl);
					sl = schedules[i].s;
					max = 0;
					pq.clear();
				}
				pq.poll();
				pq.offer(schedules[i].e);
				el = Math.max(el, schedules[i].e);
			}
			max = Math.max(max, pq.size());
		}
		ans += max * (el - sl);
		System.out.println(ans);
	}

	static class Schedule {
		int s, e;

		public Schedule(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
}
