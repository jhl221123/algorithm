package baekjoon.Quiz30805;

import java.util.*;
import java.io.*;

/*
1. 100 -> 1 순회하며, 두 순열이 공통으로 가지는 요소를 찾는다.
2. 공통으로 가진다면, 리스트에 해당 요소를 추가하고, 탐색 시작 지점을 옮긴다.
3. 다시 1 과정부터 반복한다.
3-1. 순열의 범위를 벗어나거나, 더 이상 공통되는 요소가 없다면 종료한다.
*/

public class Quiz30805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequenceA = initializeSequence(st);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] sequenceB = initializeSequence(st);

        List<Integer> maxCommonSubsequence = findMaxCommonSubsequenceByLexicographicalOrder(sequenceA, sequenceB);
        printAnswer(maxCommonSubsequence);
    }

    private static void printAnswer(List<Integer> sequence) {
        StringBuilder sb = new StringBuilder();
        sb.append(sequence.size()).append("\n");
        sequence.forEach(x -> sb.append(x).append(" "));
        System.out.println(sb);
    }

    private static List<Integer> findMaxCommonSubsequenceByLexicographicalOrder(int[] sequenceA, int[] sequenceB) {
        int lengthA = sequenceA.length;
        int lengthB = sequenceB.length;
        List<Integer> maxCommonSubsequence = new ArrayList<>();

        int offsetA = 0;
        int offsetB = 0;
        int currentMaxNumber = 100;
        bk: while(offsetA < lengthA && offsetB < lengthB) {
            for(int number=currentMaxNumber; number>0; number--) {
                int tempOffsetA = findCurrentMaxNumberIdx(sequenceA, offsetA, number);
                int tempOffsetB = findCurrentMaxNumberIdx(sequenceB, offsetB, number);

                if(hasCommonNumber(tempOffsetA, tempOffsetB)) {
                    maxCommonSubsequence.add(number);
                    currentMaxNumber = number;
                    offsetA = tempOffsetA;
                    offsetB = tempOffsetB;
                    break;
                }

                if (hasNoMoreCommonNumbers(number)) break bk;
            }
        }

        return maxCommonSubsequence;
    }

    private static boolean hasCommonNumber(int offsetA, int offsetB) {
        return offsetA > 0 && offsetB > 0;
    }

    private static boolean hasNoMoreCommonNumbers(int i) {
        return i == 1;
    }

    private static int findCurrentMaxNumberIdx(int[] sequence, int offset, int i) {
        int length = sequence.length;
        int tempOffset = -1;

        for(int j = offset; j< length; j++) {
            if(sequence[j] == i) {
                tempOffset = j + 1;
                break;
            }
        }

        return tempOffset;
    }

    private static int[] initializeSequence(StringTokenizer st) {
        int length = st.countTokens();
        int[] sequence = new int[length];

        for(int i = 0; i< length; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        return sequence;
    }
}
