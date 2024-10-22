package baekjoon.Quiz1493;

import java.io.*;
import java.util.*;

public class Quiz1493 {
	static int[] box;
	static int[] cube;
	static long totalVolume;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		box = new int[3];
		totalVolume = 1;
		for(int i=0; i<3; i++) {
			box[i] = Integer.parseInt(st.nextToken());
			totalVolume *= box[i];
		}
		int N = Integer.parseInt(br.readLine());
		cube = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			cube[size] = count;
		}

		System.out.println(dfs(N-1, 0, 0));
	}

	private static long dfs(int cubeSize, long filledCount, long count) {
		long neededCubeCount = 1;
		for(int i=0; i<3; i++) {
			// 현재 크기의 큐브가 차지할 수 있는 개수를 구한다.
			neededCubeCount *= box[i] >> cubeSize;
		}

		// filledCount = 이전에 채웠던 크기 / 현재 큐브의 크기 -> 이전 dfs에서 갱신되어 전달된다.
		// neededCube = 현재 크기의 큐브가 차지할 수 있는 총 개수
		// neededCube - filledCount = 현재 필요한 큐브의 개수
		neededCubeCount -= filledCount;

		// 필요한 개수와 주어진 개수 중 작은 값이 결과적으로 채워진다.
		long fillCount = Math.min(neededCubeCount, cube[cubeSize]);
		if(cubeSize == 0) {
			if(fillCount + filledCount != totalVolume) return -1;
			else return count + fillCount;
		}

		// 큐브 사이즈는 한 단계 감소, 현재 채워진 공간은 다음 큐브 사이즈의 개수로 전달, 사용한 큐브 개수를 반영
		return dfs(cubeSize - 1, filledCount + fillCount << 3, count + fillCount);
	}
}
