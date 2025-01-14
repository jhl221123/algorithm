package baekjoon.Quiz16288;

import java.util.*;
import java.io.*;

/*
1. 입력으로 주어지는 순서대로 대기 가능한 곳을 탐색하고 배치한다.
1-1. 현재 사용중인 큐를 순회하면서 큐의 마지막 순번보다 현재 순번이 크다면 해당 큐에 대기 가능하다.
1-1-1. 이때, 나머지 큐도 모두 탐색해서 차이가 가장 작은 곳을 선택한다.
1-2. 현재 사용중인 모든 큐를 탐색했지만, 대기 가능한 큐가 없다면 새로운 큐를 사용한다.
1-2-1. 이때, 큐 사이즈가 k를 초과하면 "NO"를 출력한다.
2. 모든 배치가 완료되었다면 "YES"를 출력한다.
 */

public class Quiz16288 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] passengers = initializeSequence(N, st);
        boolean isOrderPossible = enqueuePassengers(passengers, k);

        if(isOrderPossible) System.out.println("YES");
        else System.out.println("NO");
    }

    private static boolean enqueuePassengers(int[] passengers, int k) {
        List<Integer> queues = new ArrayList<>();
        queues.add(passengers[0]);

        for(int i = 1; i< passengers.length; i++) {
            // 1: queues를 순회하며 sequence[i] 보다 작으면서 서로의 차이가 최소인 곳을 찾는다.
            int idx = -1;
            int diff = 1000;
            int currentPassenger = passengers[i];
            for(int j=0; j<queues.size(); j++) {
                int lastOrderOfQueue = queues.get(j);
                if(lastOrderOfQueue < currentPassenger && currentPassenger - lastOrderOfQueue < diff) {
                    idx = j;
                    diff = currentPassenger - lastOrderOfQueue;
                }
            }

            // 2: 찾았다면 해당 인덱스에 값을 덮는다.
            if(idx >= 0) {
                queues.set(idx, currentPassenger);
            }

            // 3: 찾지 못했다면 새로운 원소로 추가한다.
            else {
                queues.add(currentPassenger);
            }

            // 4: queues.size() 가 k를 초과하면 NO
            if(queues.size() > k) {
                return false;
            }
        }
        return true;
    }

    private static int[] initializeSequence(int N, StringTokenizer st) {
        int[] sequence = new int[N];
        for(int i = 0; i< N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        return sequence;
    }
}
