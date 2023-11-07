package baekjoon.Quiz15828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

// 전체 시간 복잡도: O(I)
public class Quiz15828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> ad = new ArrayDeque<>();

		while(true) {
			// -1 들어오면 종료
			int target = Integer.parseInt(br.readLine());
			if(target == -1) break;
				// 0들어오면 큐에서 poll
			else if(target == 0) ad.poll();
				// 정보가 0보다 크고, 남은공간이 N보다 작으면 큐에 입력
			else if(ad.size()<N) ad.addLast(target);
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if(ad.isEmpty()) bw.write("empty");
		while(!ad.isEmpty()) {
			bw.write(ad.poll() + " ");
		}
		bw.flush();
	}
}
