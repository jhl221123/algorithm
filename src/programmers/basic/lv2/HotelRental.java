package programmers.basic.lv2;

import java.util.*;

public class HotelRental {
	public static void main(String[] args) {
		String[][] bookTime = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
		int result = solution(bookTime); // 3
		System.out.println(result);
	}
	public static int solution(String[][] book_time) {
		List<Time> times = new ArrayList<>();
		// 1. 시간을 int로 전환
		for(String[] book : book_time) {
			int startTime = changeStringToInt(book[0]);
			int endTime = changeStringToInt(book[1]);
			times.add(new Time(startTime, endTime));
		}
		// 2. startTime asc 정렬
		Collections.sort(times, (o1, o2) -> o1.startTime - o2.startTime);
		// 3. endTime을 pq로 관리
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(times.get(0).endTime);
		// 4. 순회하며 최대힙과 비교
		int count = 1;
		for(int i=1; i<times.size(); i++) {
			// 5. 최대힙 + 10 보다 크거나 같다면 -> poll, 갱신
			if(pq.peek() + 10 <= times.get(i).startTime) {
				pq.poll();
				pq.offer(times.get(i).endTime);
			} else { // 6. 작다면 -> count++, 해당 시간 pq에 삽입
				count++;
				pq.offer(times.get(i).endTime);
			}
		}
		return count;
	}
	public static int changeStringToInt(String str) {
		StringTokenizer st = new StringTokenizer(str, ":");
		int hour = Integer.parseInt(st.nextToken());
		int min = Integer.parseInt(st.nextToken());
		return hour * 60 + min;
	}
	static class Time {
		int startTime;
		int endTime;

		public Time(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
}
