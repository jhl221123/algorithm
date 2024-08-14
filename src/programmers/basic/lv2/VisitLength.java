package programmers.basic.lv2;

import java.util.*;

public class VisitLength {
	public static void main(String[] args) {
		int result = solution("ULURRDLLU"); // 7
		System.out.println(result);
	}
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	public static int solution(String dirs) {
		Set<String> set = new HashSet<>();
		int[] from = new int[] {0, 0};
		int answer = 0;
		for(int i=0; i<dirs.length(); i++) {
			int dir = charToInt(dirs.charAt(i));
			int mr = from[0] + dy[dir];
			int mc = from[1] + dx[dir];
			if(mr < -5 || mc < -5 || mr > 5 || mc > 5) continue;
			int[] to = new int[] {mr, mc};
			String edge = getEdge(from, to);
			if(!set.contains(edge)) {
				answer++;
				String pair = getEdge(to, from);
				set.add(edge);
				set.add(pair);
			}
			from = to;
		}
		return answer;
	}

	private static int charToInt(char d) {
		return switch(String.valueOf(d)) {
			case "U" -> 0;
			case "D" -> 1;
			case "L" -> 2;
			case "R" -> 3;
			default -> -1;
		};
	}

	private static String getEdge(int[] from, int[] to) {
		return new StringBuilder()
			.append(from[0])
			.append(from[1])
			.append(to[0])
			.append(to[1])
			.toString();
	}
}
