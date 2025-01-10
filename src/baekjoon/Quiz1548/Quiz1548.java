package baekjoon.Quiz1548;

import java.util.*;
import java.io.*;

/*
1. 수열을 오름차순으로 정렬한다.
2. 왼쪽 끝의 두 수의 합이 오른쪽 끝 수보다 크다면, 해당 수열은 삼각 수열이 된다.
2-1. 오름 차순으로 정렬했기 때문에 위 조건을 만족했을 때, 해당 수열에서 어떤 세 수를 뽑아도 삼각 수열이 된다.
3. 초기 수열에서 왼쪽 인덱스와 오른쪽 인덱스를 조절하면서 최대 길이를 탐색한다.
3-1. 왼쪽 인덱스를 고정해두고, 조건을 만족할때까지 오른쪽 인덱스를 줄여나간다.
3-2. 왼쪽 인덱스를 증가시키고, 다시 오른쪽 인덱스를 줄여나간다.
 */

public class Quiz1548 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer numbers = new StringTokenizer(br.readLine());

        if (isLessThanThree(N)) {
            System.out.println(N);
            return;
        }

        Integer[] numberSequence = initializeNumberSequence(numbers);
        Arrays.sort(numberSequence);
        int maxLength = calculateMaxLengthOf(numberSequence);

        System.out.println(maxLength);
    }

    private static int calculateMaxLengthOf(Integer[] numberSequence) {
        int length = numberSequence.length;
        int maxLength = 2;

        for(int i = 0; i< length -2; i++) {
            for(int j = length -1; j>i+1; j--) {
                if(numberSequence[i] + numberSequence[i+1] > numberSequence[j]) {
                    maxLength = Math.max(maxLength, j - i + 1);
                    break;
                }
            }
        }

        return maxLength;
    }

    public static boolean isLessThanThree(int N) {
        return N < 3;
    }

    public static Integer[] initializeNumberSequence(StringTokenizer numbers) {
        int length = numbers.countTokens();
        Integer[] numberSequence = new Integer[length];

        for(int i=0; i<length; i++) {
            numberSequence[i] = Integer.parseInt(numbers.nextToken());
        }

        return numberSequence;
    }
}
