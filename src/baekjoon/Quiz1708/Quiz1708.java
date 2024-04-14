package baekjoon.Quiz1708;

import java.util.*;
import java.io.*;

public class Quiz1708 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] base = {40001, 40001};
		List<int[]> points = new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.add(new int[]{y, x});
			// 시작 좌표 갱신
			if(base[1] > x) {
				base[0] = y;
				base[1] = x;
			} else if(base[1] == x && base[0] > y) {
				base[0] = y;
			}
		}
		// 반시계 방향으로 좌표 정렬
		points.sort((p1, p2) -> {
			if(ccw(base, p1, p2) == 0) return Long.compare(getDist(base, p1), getDist(base, p2));
			return ccw(base, p1, p2) * -1 > 0 ? 1 : -1;
		});

		List<int[]> line = new ArrayList<>();
		line.add(points.get(0));
		line.add(points.get(1));
		for (int i = 2; i < N; i++) {
			line.add(points.get(i));
			int idx = line.size()-1;
			while(true) { // 반시계 방향이 될때까지 반복
				int[] point3 = line.get(idx);
				int[] point2 = line.get(idx-1);
				int[] point1 = line.get(idx-2);
				if(ccw(point1, point2, point3) > 0) break;
				line.set(idx-1, point3);
				line.remove(idx);
				idx--;
				if(line.size() <=2) break; // 2개 이하라면 다음 좌표 탐색을 위해 종료
			}
		}
		System.out.println(line.size());
	}
	private static long getDist(int[] point1, int[] point2) {
		return ((long)(point1[0] - point2[0]) * (point1[0] - point2[0])) + ((long)(point1[1] - point2[1]) *(point1[1] - point2[1]));
	}
	private static long ccw(int[] point1, int[] point2, int[] point3) {
		return ((long)(point2[1] - point1[1]) * (point3[0] - point1[0])) - ((long)(point3[1] - point1[1]) * (point2[0] - point1[0]));
	}
}
