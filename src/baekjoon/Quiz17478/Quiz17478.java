package baekjoon.Quiz17478;

import java.io.*;

/*
Silver5: 재귀함수가 뭔가요? / [dfs]
*/
public class Quiz17478 {

	private static int N;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
		dfs(0);
		System.out.print(sb);
	}

	private static void dfs(int depth) {
		if(depth > N) return;
		StringBuilder prefix = new StringBuilder();
		for(int i=0; i<depth; i++) {
			prefix.append("____");
		}
		excute1(prefix.toString(), depth == N);
		dfs(depth + 1);
		excute2(prefix.toString());
	}

	private static void excute1(String prefix, boolean isTerminal) {
		sb.append(prefix).append("\"재귀함수가 뭔가요?\"").append("\n");
		if(isTerminal) {
			sb.append(prefix).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
			return;
		}
		sb.append(prefix).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n");
		sb.append(prefix).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n");
		sb.append(prefix).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n");
	}

	private static void excute2(String prefix) {
		sb.append(prefix).append("라고 답변하였지.").append("\n");
	}
}
