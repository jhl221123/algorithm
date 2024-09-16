package baekjoon.Quiz1744;

import java.io.*;
import java.util.*;

public class Quiz1744 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> negative = new ArrayList<>();
		List<Integer> positive = new ArrayList<>();
		int minusOneCnt = 0;
		int zeroCnt = 0;
		int oneCnt = 0;

		// -1, 0, 1이 있다면 제외해두고 시작
		// -1은
		// 1. 음수가 하나 남았을 때 곱하고 덧셈 0
		// 2. 0이 있다면 0과 곱하고 덧셈 0
		// 3. 음수가 안남았다면 그냥 덧셈
		// 0은
		// 1. 음수가 하나 남았다면 곱하고 덧셈 o
		// 2. 나머진 그냥 덧셈
		// 1은
		// 1. 그냥 덧셈
		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if(num == -1) {
				minusOneCnt++;
				continue;
			}
			if(num == 0) {
				zeroCnt++;
				continue;
			}
			if(num == 1) {
				oneCnt++;
				continue;
			}
			if(num < 0) negative.add(num);
			if(num > 0) positive.add(num);
		}

		Collections.sort(negative);
		Collections.sort(positive, (o1, o2) -> o2 - o1);

		int sum = 0;
		for(int i=0; i<negative.size(); i++) {
			if(i + 1 <= negative.size() - 1) {
				sum += (negative.get(i) * negative.get(i + 1));
				i++;
			} else { // 마지막 음수
				if(minusOneCnt > 0) { // -1 남아있으면 양수로 전환
					sum += (negative.get(i) * -1);
					minusOneCnt--;
				} else if(zeroCnt > 0) { // 0 남아있으면 0으로 전환
					zeroCnt--;
				} else { // 상쇄시키지 못하면 그냥 더하기
					sum += negative.get(i);
				}
			}
		}

		for(int i=0; i<positive.size(); i++) {
			if(i + 1 <= positive.size() - 1) {
				sum += (positive.get(i) * positive.get(i + 1));
				i++;
			} else { // 마지막 양수
				sum += positive.get(i);
			}
		}

		if(minusOneCnt > 1) {
			sum += (minusOneCnt / 2); // -1 2개는 1로 전환 가능
			minusOneCnt /= 2;
		}
		if(minusOneCnt > 0 && zeroCnt == 0) sum += -1; // 0이 남아있다면, 남아있는 -1 상쇄
		if(oneCnt > 0) sum += oneCnt; // 남아있는 1은 모두 더한다.
		System.out.println(sum);
	}
}
