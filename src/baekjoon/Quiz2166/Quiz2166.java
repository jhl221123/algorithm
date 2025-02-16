package baekjoon.Quiz2166;

import java.io.*;

/*
1. 신발끈 공식을 활용해 다각형의 면적을 구한다.
1-1. 행렬에서 신발끈 형태로 곱한 후, 두 값의 차의 절대값을 구한다.
1-2. 절대값에 1/2을 곱한 후, 소수 둘째 자리에서 반올림한다.
 */
public class Quiz2166 {

	public static void main(String[] args) throws IOException {
		new Quiz2166().solve();
	}

	public void solve() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[][] vertices = new double[N + 1][2];
		for(int i=0; i<N; i++) {
			String[] vertex = br.readLine().split(" ");
			vertices[i][0] = Double.parseDouble(vertex[0]);
			vertices[i][1] = Double.parseDouble(vertex[1]);
		}
		vertices[N][0] = vertices[0][0];
		vertices[N][1] = vertices[0][1];

		double width = 0;
		for(int i=0; i<N; i++) {
			width += vertices[i][0] * vertices[i+1][1];
			width -= vertices[i][1] * vertices[i+1][0];
		}

		System.out.printf("%.1f", Math.abs(width) / 2);
	}
}
