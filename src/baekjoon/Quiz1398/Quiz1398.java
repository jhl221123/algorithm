package baekjoon.Quiz1398;

import java.io.*;

/*
1. 동전 타입은 1 * 100 ^ x, 10 * 100 ^ x, 25 * 100 ^ x (x >= 0) 으로 세 가지만 존재한다.
1-1. 따라서 주어진 가격을 100 단위로 끊어서 총 동전 개수를 구할 수 있다.
2. 1 ~ 100 범위에서 필요한 최소 동전 개수를 배열로 관리한다.
3. 주어진 가격에서 낮은 두 자리씩 끊어서 동전 개수를 구하고 합한다.
3-1. sum += coin[price % 100] -> price /= 100
 */

public class Quiz1398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] coinCounts = calculateCoinCount1To100();
        StringBuilder answer = new StringBuilder();

        while(T-- > 0) {
            long price = Long.parseLong(br.readLine());
            int minimumCoinCount = calculateMinimumCoinCount(price, coinCounts);
            answer.append(minimumCoinCount).append("\n");
        }

        System.out.print(answer);
    }

    private static int[] calculateCoinCount1To100() {
        int[] coinCounts = new int[101];

        for(int i=1; i<=100; i++) {
            if(i >= 25) {
                coinCounts[i] = Math.min(coinCounts[i-25]+1, Math.min(coinCounts[i-10]+1, coinCounts[i-1]+1));
            } else if(i >= 10) {
                coinCounts[i] = Math.min(coinCounts[i-10]+1, coinCounts[i-1]+1);
            } else {
                coinCounts[i] = i;
            }
        }

        return coinCounts;
    }

    private static int calculateMinimumCoinCount(long price, int[] coinCounts) {
        int minimumCoinCount = 0;

        while(price > 0) {
            minimumCoinCount += coinCounts[(int) (price % 100)];
            price /= 100;
        }

        return minimumCoinCount;
    }
}
