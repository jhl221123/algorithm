package swea.d3;

import java.util.*;

public class Quiz1206 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T=10;

        for(int test_case = 1; test_case <= T; test_case++) {
            // 앞, 뒤 +2 건물과 각각 비교(총 4번) 수행 후 차이가 가장 작은 부분만큼 조망권 확보. 최소값이 음수라면 0으로 계산
            // 비교 메서드 수행시 앞, 뒤 건물 존재여부도 관리
            int N = sc.nextInt();
            int[] building = new int[N];

            for(int i=0; i<N; i++) {
                building[i] = sc.nextInt();
            }

            int numberOfGoodRoom=0;

            for(int i=0; i<N; i++) {
                numberOfGoodRoom += countGoodRoom(building, i);
            }

            System.out.println("#"+test_case+" "+numberOfGoodRoom);
        }
    }

    private static int countGoodRoom(int[] building, int target) {
        int numberOfGoodRoom = 255;
        int targetHeight = building[target];

        for(int i=target-1; i>=target-2; i--) {
            if(i<0) break;
            int currentGoodRoom = targetHeight - building[i];
            if(currentGoodRoom<0) currentGoodRoom = 0;
            numberOfGoodRoom = Math.min(numberOfGoodRoom, currentGoodRoom);
        }

        for(int i=target+1; i<=target+2; i++) {
            if(i>=building.length) break;
            int currentGoodRoom = targetHeight - building[i];
            if(currentGoodRoom<0) currentGoodRoom = 0;
            numberOfGoodRoom = Math.min(numberOfGoodRoom, currentGoodRoom);
        }

        return numberOfGoodRoom;
    }
}
