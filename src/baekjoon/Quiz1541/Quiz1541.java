package baekjoon.Quiz1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 잃어버린 괄호
public class Quiz1541 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    boolean lever = false;
    int temp = 0;
    int left = 0;
    int right = 0;
    for(int i=0; i<str.length(); i++) {
      if(lever) {
        if(str.charAt(i) == '+' || str.charAt(i) == '-') {
          right += temp;
          temp = 0;
          continue;
        }
      } else {
        if(str.charAt(i) == '+' || str.charAt(i) == '-') {
          left += temp;
          temp = 0;
          if(str.charAt(i) == '-') lever = true;
          continue;
        }
      }
      temp = temp * 10 + (str.charAt(i) - '0');
    }
    if(lever) right += temp;
    else left += temp;
    System.out.println(left - right);
  }
}
