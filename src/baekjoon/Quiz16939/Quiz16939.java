package baekjoon.Quiz16939;

import java.util.*;
import java.io.*;

public class Quiz16939 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Cube cube = new Cube();
    for(int i=1; i<=24; i++) {
      cube.color[i] = Integer.parseInt(st.nextToken());
    }
    cube.rx();
    cube.check();
    cube.rx();
    cube.rx();
    cube.check();
    cube.rx();

    cube.ry();
    cube.check();
    cube.ry();
    cube.ry();
    cube.check();
    cube.ry();

    cube.rz();
    cube.check();
    cube.rz();
    cube.rz();
    cube.check();
    System.out.println(0);
  }
  static class Cube {
    int[] color = new int[25];
    public Cube() {};
    public void check() {
      int[] base = {1, 5, 9, 13, 17, 21};
      for(int i=1; i<=24; i++) {
        if(color[i] != color[base[(i-1)/4]]) return;
      }
      System.out.println(1);
      System.exit(0);
    }
    public void rx() {
      int t1 = color[2];
      int t2 = color[4];
      color[2] = color[6];
      color[4] = color[8];
      color[6] = color[10];
      color[8] = color[12];
      color[10] = color[21];
      color[12] = color[23];
      color[21] = t1;
      color[23] = t2;
    }
    public void ry() {
      int t1 = color[13];
      int t2 = color[14];
      color[13] = color[5];
      color[14] = color[6];
      color[5] = color[17];
      color[6] = color[18];
      color[17] = color[21];
      color[18] = color[22];
      color[21] = t1;
      color[22] = t2;
    }
    public void rz() {
      int t1 = color[3];
      int t2 = color[4];
      color[3] = color[16];
      color[4] = color[14];
      color[16] = color[10];
      color[14] = color[9];
      color[10] = color[17];
      color[9] = color[19];
      color[17] = t1;
      color[19] = t2;
    }
  }
}
