package baekjoon.Quiz1941;

import java.util.*;
import java.io.*;

public class Quiz1941 {
	// 5 * 5 좌표중 7개의 좌표를 조합으로 선택
	// 이중 반복문으로 7명이 친구인지 확인
	// 친구라면 그중 4명 이상이 이다솜파(S)인지 확인
	static List<Member> members;
	static Member[] tgt;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		members = new ArrayList<>();
		int memberIdx = 0;
		for(int i=0; i<5; i++) {
			String str = br.readLine();
			for(int j=0; j<5; j++) {
				char team = str.charAt(j);
				members.add(new Member(memberIdx++, team));
			}
		}
		tgt = new Member[7];
		comb(0, 0, 0);
		System.out.println(count);
	}
	static void comb(int srcIdx, int tgtIdx, int mask) {
		if(tgtIdx==7) {
			// mask apply
			int idx = 0;
			for(int i=0; i<members.size(); i++) {
				if((mask & (1 << i)) != 0) tgt[idx++] = members.get(i);
			}
			if(allFriend() && isWin()) count++;
			return;
		}
		if(srcIdx==members.size()) return;
		comb(srcIdx+1, tgtIdx+1, mask | 1 << srcIdx);
		comb(srcIdx+1, tgtIdx, mask);
	}
	static boolean isWin() {
		int ourTeam = 0;
		for(int i=0; i<7; i++) {
			if(tgt[i].team == 'S') ourTeam++;
		}
		return ourTeam >= 4;
	}
	static boolean allFriend() {
		boolean[] visit = new boolean[7];
		ArrayDeque<Member> ad = new ArrayDeque<>();
		ad.add(tgt[0]);
		while(!ad.isEmpty()) {
			Member f1 = ad.removeFirst();
			for(int i=0; i<7; i++) {
				if(!visit[i] && isFriend(f1.num, tgt[i].num)) {
					visit[i] = true;
					ad.addLast(tgt[i]);
				}
			}
		}
		// is all friend?
		for(int i=0; i<7; i++) {
			if(!visit[i]) return false;
		}
		return true;
	}
	static boolean isFriend(int me, int pair) { // 검사 중복 제거 필요
		int diff = Math.abs(me - pair);
		int max = Math.max(me, pair);
		if(diff == 1 && max % 5 != 0) return true;
		if(diff == 5) return true;
		return false;
	}
	static class Member {
		int num;
		char team;
		public Member(int num, char team) {
			this.num = num;
			this.team = team;
		}
	}
}
