package programmers.basic.lv2;

public class Fatigue {
	public static void main(String[] args) {
		int result = solution(80, new int[][] {{80,20},{50,40},{30, 10}});// 3
		System.out.println(result);
	}

	public static int solution(int k, int[][] dungeons) {
		boolean[] visited = new boolean[dungeons.length];
		return dfs(k, dungeons, visited, 0);
	}

	private static int dfs(int k, int[][] dungeons, boolean[] visited, int cnt) {
		if(cnt == dungeons.length) {
			return dungeons.length;
		}
		int max = cnt;
		for(int i=0; i<dungeons.length; i++) {
			if(visited[i] || k < dungeons[i][0]) continue;
			visited[i] = true;
			max = Math.max(max, dfs(k - dungeons[i][1], dungeons, visited, cnt + 1));
			visited[i] = false;
		}
		return max;
	}
}
