package baekjoon.Quiz16236;

import java.util.*;
import java.io.*;

/*
1. 상어 위치를 시작으로 bfs 탐색
1-1. 현재 위치에 물고기가 있고, 상어보다 작다면 먹는다.
1-1-1. 현재 노드의 거리만큼 전체 ans를 증가시킨다.
1-1-2. 맵에서 물고기를 지운다.
1-1-3. 물고기 먹은 횟수를 증가시키고, 크기 증가를 고려한다.
1-1-4. 우선순위 큐와 방문 배열을 초기화한다.
1-1-5. 현재 위치를 기준으로 다시 bfs 탐색을 시작한다.
1-2. 물고기를 못먹으면 4방향 탐색한다.
1-2-1. 격자를 벗어나거나, 이미 방문했거나, 큰 물고기가 있다면 패스한다.
1-2-2. 이동 가능한 칸이라면 거리 +1, mr, mc의 노드를 우선순위 큐에 추가한다.
*/

public class Quiz16236 {
	private static int N;
	private static int[][] map;
	private static Shark shark;
	private static boolean[][] visited;
	private static PriorityQueue<Node> pq;
	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.dist == o2.dist) {
				if(o1.r == o2.r) {
					return o1.c - o2.c;
				}
				return o1.r - o2.r;
			}
			return o1.dist - o2.dist;
		});

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Shark(i, j, 2);
					map[i][j] = 0;
				}
			}
		}

		while(shark.possibleHunt()) {
			bfs();
			initDataStructure();
		}

		System.out.println(shark.getTime());
	}

	private static void bfs() {
		pq.offer(new Node(0, shark.r, shark.c));

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int r = node.r;
			int c = node.c;

			if(visited[r][c]) continue;
			visited[r][c] = true;
			if(map[r][c] > 0 && map[r][c] < shark.size) {
				shark.eat();
				shark.move(r, c, node.dist);
				map[r][c] = 0;
				return;
			}

			for(int d=0; d<4; d++) {
				int mr = node.r + dy[d];
				int mc = node.c + dx[d];
				if(mr < 0 || mc < 0 || mr >= N || mc >= N) continue;
				if(visited[mr][mc] || map[mr][mc] > shark.size) continue;
				pq.offer(new Node(node.dist + 1, mr, mc));
			}
		}

		shark.callMom();
	}

	private static void initDataStructure() {
		// init visited, pq
		for(int i=0; i<N; i++) {
			Arrays.fill(visited[i], false);
		}

		pq.clear();
	}

	static class Node {
		private int dist, r, c;

		public Node(int dist, int r, int c) {
			this.dist = dist;
			this.r = r;
			this.c = c;
		}
	}

	static class Shark {
		private int r, c, size, eatCount, time;
		private boolean sos;

		public Shark(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.eatCount = 0;
			this.time = 0;
			this.sos = false;
		}

		public void eat() {
			this.eatCount++;
			if(this.eatCount == this.size) {
				this.size++;
				this.eatCount = 0;
			}
		}

		public void move(int mr, int mc, int dist) {
			this.r = mr;
			this.c = mc;
			this.time += dist;
		}

		public boolean possibleHunt() {
			return !sos;
		}

		public void callMom() {
			this.sos = true;
		}

		public int getTime() {
			return this.time;
		}
	}
}
