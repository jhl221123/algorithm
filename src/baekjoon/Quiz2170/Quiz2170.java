package baekjoon.Quiz2170;

import java.util.*;
import java.io.*;

/*
Gold5: 선 긋기 / [sort, sweeping]
1. 점의 시작 위치 오름차순, 같다면 끝 위치 오름차순으로 선분을 정렬한다.
2. 선분을 순회하며 이전 끝 점이 다음 시작점보다 클 경우, 이전 끝 점과 다음 끝 점 중 더 큰 값을 유지한다.
3. 이전 끝 점이 다음 시작점보다 작을 경우, 현재 길이를 총 길이에 합한다.
 */
public class Quiz2170 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Edge[] edges = new Edge[N];
		for(int i=0; i<N; i++) {
			String[] edge = br.readLine().split(" ");
			int start = Integer.parseInt(edge[0]);
			int end = Integer.parseInt(edge[1]);
			edges[i] = new Edge(start, end);
		}

		Arrays.sort(edges, (o1, o2) -> {
			if(o1.start == o2.start) {
				return o1.end - o2.end;
			};
			return o1.start - o2.start;
		});

		int cs = edges[0].start;
		int ce = edges[0].end;
		int sum = 0;
		for(int i=1; i<N; i++) {
			int ns = edges[i].start;
			int ne = edges[i].end;
			if(ns <= ce) {
				ce = Math.max(ce, ne);
				continue;
			}

			sum += ce - cs;
			cs = ns;
			ce = ne;
		}
		sum += ce - cs;

		System.out.println(sum);
	}

	static class Edge {
		int start;
		int end;

		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
