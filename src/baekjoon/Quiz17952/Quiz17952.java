package baekjoon.Quiz17952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Quiz17952 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 1. 입력 받으면서 동시에 업무를 관리
		// 1-1. 새로운 업무(1): 새로운 업무가 바로 끝나는지 아닌지 확인 후 반영
		// 1-2. 업무 할당x(0): 진행중인 업무가 있다면 업무시간 -1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Task> ad = new ArrayDeque<>();
		int totalScore = 0;
		for(int i=0; i<N; i++) {
			String cmd = br.readLine();
			if(cmd.charAt(0) == '0') { // 업무 할당 x
				if(!ad.isEmpty()) { // 기존 업무가 있는 경우
					Task currentTask = ad.getFirst();
					boolean isTerminate = currentTask.minusAndCheck();
					if(isTerminate) { // 진행중인 작업이 종료되었다면, 작업 큐에서 제거 후 점수 합산
						Task endTask = ad.removeFirst();
						totalScore += endTask.score;
					}
				}
			} else { // 새로운 업무 추가
				StringTokenizer st = new StringTokenizer(cmd);
				int type = Integer.parseInt(st.nextToken()); // 1
				int score = Integer.parseInt(st.nextToken());
				int taskTime = Integer.parseInt(st.nextToken());
				// 새로운 업무가 바로 끝날 경우
				if(taskTime-1 ==0) totalScore += score;
					// 바로 끝나지 않는 경우
				else ad.addFirst(new Task(score, taskTime-1));
			}
		}
		System.out.println(totalScore);
	}
	static class Task {
		int score;
		int time;
		public Task(int score, int time) {
			this.score = score;
			this.time = time;
		}
		public boolean minusAndCheck() {
			this.time -= 1;
			if(this.time==0) return true;
			else return false;
		}
	}
}
