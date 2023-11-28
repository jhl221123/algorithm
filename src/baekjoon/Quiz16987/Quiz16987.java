package baekjoon.Quiz16987;

import java.util.Scanner;

// 전체 시간 복잡도: O((N-1)^N)
public class Quiz16987 {
	// egg 클래스 생성
	// base
	// 1. start가 마지막 계란이라면 카운트 체크
	// recur
	// start는 들고 있는 계란으로 취급, continue;
	// 내구도 0이하 egg, continue;
	// 0부터 깨기 시작(내구도 감소), start+1 전달
	// recur 종료후 내구도 원복
	static int N;
	static Egg[] eggs;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		eggs = new Egg[N];
		for(int i=0; i<N; i++) {
			eggs[i] = new Egg(sc.nextInt(), sc.nextInt());
		}
		recur(0);
		System.out.println(ans);
	}
	private static void recur(int target) {
		if(target == N) {
			int count = 0;
			for(int i=0; i<N; i++) {
				if(eggs[i].durability<=0) count++;
			}
			ans = Math.max(ans, count);
			return;
		}
		if(eggs[target].durability<=0) recur(target+1);
		else {
			boolean flag = false;
			for(int j=0; j<N; j++) {
				if(j==target) continue;
				if(eggs[j].durability>0) {
					flag = true;
					eggs[j].fight(eggs[target]);
					recur(target+1);
					eggs[j].restore(eggs[target]);
				}
			}
			if(!flag) recur(target+1);
		}
	}
}

class Egg {
	int durability;
	final int weight;

	public Egg(int durability, int weight) {
		this.durability = durability;
		this.weight = weight;
	}

	public void fight(Egg other) {
		this.durability -= other.weight;
		other.durability -= this.weight;
	}

	public void restore(Egg other) {
		this.durability += other.weight;
		other.durability += this.weight;
	}
}
