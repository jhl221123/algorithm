package baekjoon.Quiz2056;

import java.util.*;
import java.io.*;

/*
Gold4: 작업 / [topological-sort, dp]
1. 진입 차수가 0인 작업부터 처리한다.
1-1. 작업에 소요된 시간을 최대 시간과 비교해 갱신한다.
2. 다음 작업의 차수를 감소시킨다.
2-1. 이전 작업의 최대 시간을 갱신하며, 다음 작업을 시각할 수 있는 최소 소요 시간을 구한다.
*/
public class Quiz2056 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] topo = new int[N + 1];
		int[][] times = new int[N + 1][2];
		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int num=1; num<=N; num++) {
			String[] nodeInfo = br.readLine().split(" ");
			int time = Integer.parseInt(nodeInfo[0]);
			int ppc = Integer.parseInt(nodeInfo[1]);
			times[num][0] = time;

			for(int count=0; count<ppc; count++) {
				int ppn = Integer.parseInt(nodeInfo[count + 2]);
				topo[num]++;
				graph.get(ppn).add(num);
			}
		}

		boolean[] visited = new boolean[N + 1];
		int totalTime = 0;
		while(true) {
			ArrayDeque<Integer> task = new ArrayDeque<>();
			for(int num=1; num<=N; num++) {
				if(topo[num] == 0 && !visited[num]) {
					visited[num] = true;
					task.addLast(num);
				}
			}

			if(task.isEmpty()) {
				break;
			}

			while(!task.isEmpty()) {
				int num = task.removeFirst();
				totalTime = Math.max(totalTime, times[num][1] + times[num][0]);

				for(int next : graph.get(num)) {
					topo[next]--;
					times[next][1] = Math.max(times[next][1], times[num][1] + times[num][0]);
				}
			}
		}

		System.out.println(totalTime);
	}
}
