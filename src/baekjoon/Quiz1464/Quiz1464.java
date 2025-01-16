package baekjoon.Quiz1464;

import java.io.*;

/*
1. 원소를 순차적으로 이어붙여 사전순으로 가장 큰 문자열을 만든다.
1-1. 만들어진 문자의 가장 마지막 문자(가장 작은 문자)보다 다음 인덱스의 값이 크면 가장 왼쪽에 붙인다.
1-2. 만들어진 문자의 가장 마지막 문자(가장 작은 문자)보다 다음 인덱스의 값이 작거나 같으면 가장 오른쪽에 붙인다.
2. 만들어진 문자를 뒤집는다.
*/

public class Quiz1464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder ans = new StringBuilder(String.valueOf(S.charAt(0)));

        for(int i=1; i<S.length(); i++) {
            if(S.charAt(i) > ans.charAt(i-1)) {
                ans.insert(0, S.charAt(i));
            } else {
                ans.append(S.charAt(i));
            }
        }

        System.out.println(ans.reverse());
    }
}
