package baekjoon.Quiz15961;

import java.util.*;
import java.io.*;

public class Quiz15961 {
	// 0~N까지 투포인터로 패키지 관리
	// 매번 패키지 구성품, 쿠폰 번호를 set에 넣어 size 확인
	static int N, d, k, c;
	static int[] convey;
	static int[] check;
	static ArrayDeque<Integer> ad = new ArrayDeque<>();
	static int sum;
	static int max;
	static int idx;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		convey = new int[N];
		check = new int[3001];
		for(int i=0; i<N; i++) {
			convey[i] = Integer.parseInt(br.readLine());
		}
		init();
		flag = false;
		while(!(idx == k && flag)) {
			check();
			move();
		}
		System.out.println(max);
	}
	private static void init() {
		for(int i=0; i<k; i++) {
			int target = convey[i];
			if(check[target]==0) sum++;
			check[target]++;
			ad.addLast(target);
		}
		max = Math.max(max, sum);
		idx = k;
	}
	private static void check() {
		// 현재 세트에 쿠폰과 같은 번호가 없다면 개수 추가
		if(check[c]==0)sum++;
		max = Math.max(max, sum);
		if(check[c]==0) sum--;
	}
	private static void move() {
		// 첫번째 제거
		int before = ad.removeFirst();
		check[before]--;
		if(check[before]==0) sum--;

		// 마지막 추가
		int add = convey[idx];
		ad.addLast(add);
		if(check[add]>0) {
			// 이미 있다면
			check[add]++;
		} else {
			// 없었다면
			check[add]++;
			sum++;
		}
		idx = (idx + 1)%N;
		if(idx==k) flag = true;
	}
}
