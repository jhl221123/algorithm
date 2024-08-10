package programmers.basic.lv2;

import java.util.*;

public class LightPathCycle {

	public static void main(String[] args) {
		// int[] result = solution(new String[] {"SL", "LR"});// [16]
		int[] result = solution(new String[] {"S"});// [1, 1, 1, 1]
		System.out.println(Arrays.toString(result));
	}

	static boolean[][][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static final int TOP = 0;
	static final int BOTTOM = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int ERROR = -99;
	static int N;
	static int M;

	public static int[] solution(String[] grid) {
		List<Integer> answer = new ArrayList<>();
		init(grid);

		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				String type = String.valueOf(grid[r].charAt(c));
				for(int input=0; input<4; input++) {
					int output = outputOf(type, input);
					if(visited[r][c][output]) continue;
					int[] node = new int[] {r, c};
					answer.add(excuteCycle(grid, node, output));
				}
			}
		}
		Collections.sort(answer);

		return answer.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}

	private static int excuteCycle(String[] grid, int[] node, int output) {
		int cnt = 0;
		while(!visited[node[0]][node[1]][output]) {
			visited[node[0]][node[1]][output] = true;
			cnt++;
			node = toNext(node, output);
			int input = inputOf(output);
			String type = String.valueOf(grid[node[0]].charAt(node[1]));
			output = outputOf(type, input);
		}
		return cnt;
	}

	private static void init(String[] grid) {
		N = grid.length;
		M = grid[0].length();
		visited = new boolean[N][M][4];
	}

	private static int[] toNext(int [] node, int output) {
		int mr = node[0] + dy[output];
		int mc = node[1] + dx[output];

		if(mr == -1) mr = N - 1;
		if(mr == N) mr = 0;
		if(mc == -1) mc = M - 1;
		if(mc == M) mc = 0;
		return new int[] {mr, mc};
	}

	private static int inputOf(int output) {
		return switch(output) {
			case TOP -> BOTTOM;
			case BOTTOM -> TOP;
			case LEFT -> RIGHT;
			case RIGHT -> LEFT;
			default -> ERROR;
		};
	}

	private static int outputOf(String type, int input) {
		return switch(type) {
			case "S" -> stypeOutputOf(input);
			case "L" -> ltypeOutputOf(input);
			case "R" -> rtypeOutputOf(input);
			default -> ERROR;
		};
	}

	private static int stypeOutputOf(int input) {
		return switch(input) {
			case TOP -> BOTTOM;
			case BOTTOM -> TOP;
			case LEFT -> RIGHT;
			case RIGHT -> LEFT;
			default -> ERROR;
		};
	}

	private static int ltypeOutputOf(int input) {
		return switch(input) {
			case TOP -> RIGHT;
			case BOTTOM -> LEFT;
			case LEFT -> TOP;
			case RIGHT -> BOTTOM;
			default -> ERROR;
		};
	}

	private static int rtypeOutputOf(int input) {
		return switch(input) {
			case TOP -> LEFT;
			case BOTTOM -> RIGHT;
			case LEFT -> BOTTOM;
			case RIGHT -> TOP;
			default -> ERROR;
		};
	}
}
