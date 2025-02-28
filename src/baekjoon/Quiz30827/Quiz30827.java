package baekjoon.Quiz30827;

import java.util.*;
import java.io.*;

/*
Gold4: 회의실 배정 / [greedy, sort]
1. 끝 시간 오름차순으로 회의를 정렬, 시작 시간은 상관 없음
2. 각 회의실의 마지막 회의 종료 시간이 후보 회의의 시작 시간보다 작다면 배정
2-1. 후보 회의의 시작 시간이 마지막 회의 종료 시간보다 작거나 같다면 패스
2-2. 여러 곳에 배정이 가능하다면, 두 시간의 차이가 가장 작은 곳에 배정
*/
public class Quiz30827 {

	private static int INF = 2_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Meeting> meetings = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings.add(new Meeting(start, end));
		}

		meetings.sort(Comparator.comparingInt(o -> o.end));

		int[] rooms = new int[K + 1];
		int max = 0;
		for(int i=0; i<N; i++) {
			Meeting meeting = meetings.get(i);
			int idx = 0;
			int min = INF;

			for(int j=1; j<=K; j++) {
				if(rooms[j] < meeting.start) {
					if(min > meeting.start - rooms[j]) {
						min = meeting.start - rooms[j];
						idx = j;
					}
				}
			}

			if(idx != 0) {
				max++;
				rooms[idx] = meeting.end;
			}
		}

		System.out.println(max);
	}

	static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
