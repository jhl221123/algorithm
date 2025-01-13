package baekjoon.Quiz6209;

import java.util.*;
import java.io.*;

/*
1. 각 돌섬 사이의 거리를 배열로 관리한다.
2. 이분탐색으로 최소 길이의 최대값이 될 수 있는 후보 길이를 구한다.
3. 후보 길이가 최소 길이의 최대값이 될 수 있는지 검증한다.
3-1. 돌섬 사이의 거리를 순회하며 모든 거리가 후보 길이보다 큰지 확인한다.
3-2. 돌섬 사이의 거리가 후보 길이보다 작다면, 후보 길이보다 커질 때까지 돌섬을 제거한다.
3-2-1. 돌섬 제거 횟수 내에 후보 길이보다 커지면 계속해서 후보 검증을 진행한다.
3-2-2. 돌섬 제거 횟수 내에 후보 길이보다 커지지 못하면 후보에서 탈락한다.
4. 후보 길이 검증에 성공하면 후보 길이를 증가시킨다.
4-1. 후보 길이 검증에 실패하면 후보 길이를 감소시킨다.
 */

public class Quiz6209 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Integer[] distances = inputDistances(br, n, d);
        int[] distanceDifferences = calculateDifferencesBetweenStoneIslands(distances);

        int maximum = findMaximumOfMinimumDistance(d, distanceDifferences, m);
        System.out.println(maximum);
    }

    private static Integer[] inputDistances(BufferedReader br, int n, int d) throws IOException {
        Integer[] distances = new Integer[n +1];

        for(int i = 0; i< n; i++) {
            distances[i] = Integer.parseInt(br.readLine());
        }

        distances[n] = d;
        Arrays.sort(distances);
        return distances;
    }

    private static int[] calculateDifferencesBetweenStoneIslands(Integer[] distances) {
        int size = distances.length;
        int[] differences = new int[size];
        int before = 0;

        for(int i = 0; i< size; i++) {
            differences[i] = distances[i] - before;
            before = distances[i];
        }

        return differences;
    }

    private static int findMaximumOfMinimumDistance(int distanceOfDestination, int[] distanceDifferences, int removeCount) {
        int l=0;
        int r= distanceOfDestination +1;
        int maximum = 0;

        while(l<=r) {
            int candidate = (l + r) / 2;
            boolean possible = isPossible(candidate, distanceDifferences, removeCount);

            if(possible) {
                maximum = candidate;
                l = candidate + 1;
            } else {
                r = candidate - 1;
            }
        }

        return maximum;
    }

    private static boolean isPossible(int pivot, int[] diff, int removeCount) {
        for(int i=0; i<diff.length; i++) {
            if(diff[i] < pivot) {
                int sum = diff[i];

                while(removeCount > 0 && sum < pivot) {
                    removeCount--;
                    sum += diff[++i];
                }

                if(sum < pivot) return false;
            }
        }

        return true;
    }
}
