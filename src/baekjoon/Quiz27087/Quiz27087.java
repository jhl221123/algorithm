package baekjoon.Quiz27087;

import java.io.*;

/*
Gold5: 직육면체 / [math]
1. 두 가지 변이 p로 나누어 떨어지면 1, 아니면 0을 출력한다.
*/
public class Quiz27087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            String[] ABCp = br.readLine().split(" ");
            int A = Integer.parseInt(ABCp[0]);
            int B = Integer.parseInt(ABCp[1]);
            int C = Integer.parseInt(ABCp[2]);
            int p = Integer.parseInt(ABCp[3]);

            ans.append(isPossible(A, B, C, p)).append("\n");
        }

        System.out.print(ans);
    }

    private static int isPossible(int A, int B, int C, int p) {
        boolean isPossible =
                (A % p == 0 && B % p == 0) ||
                (B % p == 0 && C % p == 0) ||
                (A % p == 0 && C % p == 0);
        return isPossible ? 1 : 0;
    }
}
