package baekjoon.Quiz2457;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz2457 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// case1
		// 1  2  3  4  5  6  7  8  9  10  11  12
		// 31 28 31 30 31 30 31 31 30 31  30  31 = 365
		// 3-1 ~ 11-30
		// 3  4  5  6  7  8  9  10  11
		// 31 30 31 30 31 31 30 31  30 = 275(60~334)
		// 1     2      3      4       5       6       7       8       9       10      11      12
		// 1-31  32-59  60-90  91-120  121-151 152-181 182-212 213-243 244-273 274-304 305-334 335-365
		// 60~334를 모두 채울 수 있는 가장 적은 프로젝트 수

		//case2
		// 입력 받으면서 월별 -> 월이 같다면 일별로 정렬
		// 첫 프로젝트의 시작일은 3월 1일 <=, 종료일은 3월 1일 >=
		// 이후 프로젝트의 시작일은 이전 종료일 <=, 종료일은 최대한 큰 것 -> 시작일이 유효한 것 중 종료일이 가장 큰 것 탐색
		// 종료일 픽스 후 위 과정 반복, 개수 1개 증가
		int[] base = setMonth();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Project[] projects = new Project[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			if(base[sm] + sd>=335) continue;
			projects[i] = new Project(base[sm] + sd, base[em]+ed);
		}
		// 시작일 기준 정렬
		Arrays.sort(projects, (p1, p2) -> p1.start - p2.start);
		int prior = base[3] + 1; // 3월 1일
		int max = -1;
		int flag = 0;
		int count = 0;
		for(int i=0; i<N; i++) {
			if(projects[i].start <= prior) {
				// 시작일이 이전 프로젝트 종료일 보다 작은 경우 종료일이 가장 큰 값을 선택
				max = Math.max(max, projects[i].end);
			} else {
				// 대체 불가, 새로운 프로젝트 시작
				if(max == -1) {
					System.out.println(0);
					return;
				}
				if(projects[i].start == projects[i].end) continue;
				prior = max;
				flag = max;
				max = -1;
				count++;
				i--;
			}
		}
		//        System.out.println(prior + " " + max);

		if(flag <= 334 && max > 334) count++;
		else if(flag<=334) count=0;
		System.out.println(count);
	}
	static int[] setMonth() {
		int[] arr = new int[13];
		arr[1] = 0;
		arr[2] = 31;
		arr[3] = 59;
		arr[4] = 90;
		arr[5] = 120;
		arr[6] = 151;
		arr[7] = 181;
		arr[8] = 212;
		arr[9] = 243;
		arr[10] = 273;
		arr[11] = 304;
		arr[12] = 334;
		return arr;
	}
	static class Project {
		int start;
		int end;
		public Project(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
