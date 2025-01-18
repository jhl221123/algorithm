package baekjoon.Quiz1043;

import java.util.*;
import java.io.*;

/*
1. 파티별 참석 여부를 배열로 관리한다.
2. 모든 파티를 진행하면서 한 번이라도 만나는 사람들을 인접 리스트로 관리한다.
3. 인접 리스트를 순회하며 진실을 아는 사람, 그와 만날 수 있는 사람, 또 그와 만날 수 있는 사람 모두 방문 처리한다.
4. 모든 파티를 순회하며 단 한 사람도 방문 처리되지 않은 모임을 카운팅한다.
*/

public class Quiz1043 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int peoples = Integer.parseInt(st.nextToken());
		int parties = Integer.parseInt(st.nextToken());
		int[] observers = inputObservers(br);
		boolean[][] attendeesByParty = inputAttendeesByParty(parties, peoples, br);

		List<Set<Integer>> canMeetPeople = calculateCanMeetPeople(attendeesByParty);
		boolean[] cannotLiePeople = calculateCannotLiePeople(peoples, observers, canMeetPeople);
		int canLieCount = calculateCanLieCount(attendeesByParty, cannotLiePeople);

		System.out.println(canLieCount);
	}

	private static int calculateCanLieCount(boolean[][] attendeesByParty, boolean[] cannotLiePeople) {
		int peoples = attendeesByParty[0].length;
		int parties = attendeesByParty.length;
		int possibleLieCount = 0;

		for(int party = 0; party<parties; party++) {
			boolean possibleLie = true;

			for(int people = 1; people<peoples; people++) {
				if(!attendeesByParty[party][people]) continue;
				if(cannotLiePeople[people]) {
					possibleLie = false;
					break;
				}
			}

			if(possibleLie) possibleLieCount++;
		}
		return possibleLieCount;
	}

	private static boolean[] calculateCannotLiePeople(int N, int[] observers, List<Set<Integer>> canMeetPeople) {
		boolean[] cannotLiePeople = new boolean[N +1];
		ArrayDeque<Integer> ad = new ArrayDeque<>();

		for(int observer : observers) {
			ad.addLast(observer);
		}

		while(!ad.isEmpty()) {
			int observer = ad.removeFirst();
			if(cannotLiePeople[observer]) continue;
			cannotLiePeople[observer] = true;

			for(int meetPeople : canMeetPeople.get(observer)) {
				if(cannotLiePeople[meetPeople]) continue;
				ad.addLast(meetPeople);
			}
		}
		return cannotLiePeople;
	}

	private static List<Set<Integer>> calculateCanMeetPeople(boolean[][] attendeesByParty) {
		int peoples = attendeesByParty[0].length;
		int parties = attendeesByParty.length;
		List<Set<Integer>> canMeetPeople = new ArrayList<>();

		for(int i = 0; i<peoples ; i++) {
			canMeetPeople.add(new HashSet<>());
		}

		for(int party = 0; party<parties; party++) {
			for(int attendee = 1; attendee<peoples; attendee++) {
				if(attendeesByParty[party][attendee]) {

					for(int candidate=1; candidate<peoples; candidate++) {
						if(candidate == attendee) continue;
						if(!attendeesByParty[party][candidate]) continue;
						canMeetPeople.get(attendee).add(candidate);
					}
				}
			}
		}

		return canMeetPeople;
	}

	private static int[] inputObservers(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int observerCount = Integer.parseInt(st.nextToken());
		int[] observers = new int[observerCount];

		if(st.hasMoreTokens()) {
			for(int i=0; i<observerCount; i++) {
				int observer = Integer.parseInt(st.nextToken());
				observers[i] = observer;
			}
		}

		return observers;
	}

	private static boolean[][] inputAttendeesByParty(int M, int N, BufferedReader br) throws IOException {
		boolean[][] attendeesByParty = new boolean[M][N +1];

		for(int party = 0; party< M; party++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int peopleCount = Integer.parseInt(st.nextToken());

			while(st.hasMoreTokens()) {
				int attendee = Integer.parseInt(st.nextToken());
				attendeesByParty[party][attendee] = true;
			}
		}

		return attendeesByParty;
	}
}
