package baekjoon.Quiz14226;

import java.util.*;
import java.io.*;

/*
Gold4: 이모티콘 / [bfs]
1. 세 가지 연산을 수행하며 bfs를 진행한다.
2. 현재 화면에 있는 이모티콘과 클립보드 수를 처리한 이력이 있다면 건너뛴다.
3. 다음 세 가지 연산이 가능한 경우만 연산을 수행한다.
4. 현재 이모티콘의 개수가 S와 같아지면 시간을 출력한다.
*/
public class Quiz14226 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		Set<String> visited = new HashSet<>();
		ArrayDeque<Screen> ad = new ArrayDeque<>();
		ad.addLast(new Screen(1, 0, 0));

		while(!ad.isEmpty()) {
			Screen screen = ad.removeFirst();
			int cc = screen.current;
			int ccb = screen.clipboard;
			int ct = screen.time;

			if(cc >= S * 2 || cc <= 0) continue;
			if(visited.contains(Screen.snapshot(cc, ccb))) continue;
			visited.add(Screen.snapshot(cc, ccb));

			if(cc == S) {
				System.out.println(ct);
				return;
			}

			if(ccb > 0 && !visited.contains(Screen.snapshot(cc + ccb, ccb))) {
				ad.addLast(new Screen(cc + ccb, ccb, ct + 1));
			}

			if(!visited.contains(Screen.snapshot(cc, cc))) {
				ad.addLast(new Screen(cc, cc, ct + 1));
			}

			if(cc - 1 > 0 && !visited.contains(Screen.snapshot(cc - 1, ccb))) {
				ad.addLast(new Screen(cc - 1, ccb, ct + 1));
			}
		}
	}

	static class Screen {
		int current;
		int clipboard;
		int time;

		public Screen(int current, int clipboard, int time) {
			this.current = current;
			this.clipboard = clipboard;
			this.time = time;
		}

		public static String snapshot(int current, int clipboard) {
			return current + "_" + clipboard;
		}
	}
}
