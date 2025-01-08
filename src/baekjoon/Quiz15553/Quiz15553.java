package baekjoon.Quiz15553;

import java.util.*;
import java.io.*;

/*
1. 방문 시간이 이어지지 않는 구간들을 구한다.
2. 구간을 내림차순으로 정렬한다.
3. 가용 성냥 개수 - 1 만큼 시간을 절약할 수 있다.
4. 전체 시간에서 절약한 시간을 제거한다.
 */

public class Quiz15553 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visitTimes = new int[N];
        for(int i=0; i<N; i++) {
            visitTimes[i] = Integer.parseInt(br.readLine());
        }

        int totalTime = visitTimes[N-1] - visitTimes[0] + 1;
        List<Integer> diffs = calculateDiffsBetweenFrontAndBack(N, visitTimes);
        diffs.sort((o1, o2) -> o2 - o1);

        int minimumUsageTime = calculateMinimumUsageTime(K, totalTime, diffs);
        System.out.println(minimumUsageTime);
    }

    private static int calculateMinimumUsageTime(int K, int totalTime, List<Integer> diffs) {
        int canSaveTime = 0;
        for(int i = 0; i< K -1; i++) {
            if(i >= diffs.size()) break;
            canSaveTime += diffs.get(i);
        }

        return totalTime - canSaveTime;
    }

    private static List<Integer> calculateDiffsBetweenFrontAndBack(int N, int[] visitTimes) {
        List<Integer> diffs = new ArrayList<>();

        for(int i = 1; i< N; i++) {
            if(visitTimes[i-1] + 1 < visitTimes[i]) {
                int diff = visitTimes[i] - visitTimes[i-1] - 1;
                diffs.add(diff);
            }
        }

        return diffs;
    }
}
