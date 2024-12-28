package baekjoon.Quiz23559;

import java.util.*;
import java.io.*;

/*
1. 메뉴를 다음 규칙에 따라 정렬한다.
1-1. 맛 A가 더 큰 메뉴가 우선 순위가 높다.
1-2. 맛 A가 더 큰 메뉴 중, 두 값의 차이가 큰 것이 더 우선 순위가 높다.
2. 메뉴 리스트를 순회하며 A와 B 중 선택한다.
2-1. 남은 금액이 (남은 일수 - 1) * 1000 + 5000 보다 크거나 같다면 A를 선택할 수 있다.
2-2. B 값이 더 크다면 B를 선택한다.
2-3. 남은 금액으로 A를 선택할 수 없다면 B를 선택한다.
*/

public class Quiz23559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        List<Menu> menus = new LinkedList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int flavorA = Integer.parseInt(st.nextToken());
            int flavorB = Integer.parseInt(st.nextToken());
            menus.add(new Menu(flavorA, flavorB));
        }

        menus.sort(Comparator
                .comparing(Menu::isFlavorAGreaterThenFlavorB).reversed()
                .thenComparing(Menu::getDiff).reversed()
        );

        int sumOfFlavorScore = sumFlavorScoreFromSelectedMenus(N, X, menus);

        System.out.println(sumOfFlavorScore);
    }

    private static int sumFlavorScoreFromSelectedMenus(int N, int X, List<Menu> menus) {
        int remainingDays = N;
        int remainingMoney = X;
        int flavorScore = 0;

        while (remainingDays > 0) {
            Menu menu = menus.get(0);

            if (isPossibleToSelectFlavorA(remainingMoney, remainingDays, menu)) {
                flavorScore += menu.getFlavorA();
                menus.remove(0);
                remainingDays--;
                remainingMoney -= 5000;
                continue;
            }

            flavorScore += menu.getFlavorB();
            menus.remove(0);
            remainingDays--;
            remainingMoney -= 1000;
        }

        return flavorScore;
    }

    private static boolean isPossibleToSelectFlavorA(int remainingMoney, int remainingDays, Menu menu) {
        return remainingMoney >= (remainingDays - 1) * 1000 + 5000 && menu.isFlavorAGreaterThenFlavorB();
    }

    static class Menu {
        int flavorA;
        int flavorB;
        int diff;
        boolean flavorAGreaterThenFlavorB;

        public Menu(int flavorA, int flavorB) {
            this.flavorA = flavorA;
            this.flavorB = flavorB;
            this.diff = Math.abs(flavorA - flavorB);
            this.flavorAGreaterThenFlavorB = flavorA > flavorB;
        }

        public int getFlavorA() {
            return flavorA;
        }

        public int getFlavorB() {
            return flavorB;
        }

        public boolean isFlavorAGreaterThenFlavorB() {
            return flavorAGreaterThenFlavorB;
        }

        public int getDiff() {
            return diff;
        }
    }
}
