package baekjoon.Quiz2118;

import java.util.Scanner;

public class Quiz2118PointerClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 지점 입력, O(N)
		int[] arr = new int[N];
		int sum = 0;
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			sum+=arr[i];
		}
		// 이전과 현재 중 차이가 적은 좌표의 짧은 길이로 ans 초기화
		// 현재 지점 길이를 구간합에서 제거
		Pointer p=new Pointer(0, N);
		int ans=0;
		int m=sum/2;
		int currentSum=arr[0];
		for(int i=0; i<N; i++) {
			while(currentSum<=m) {
				p.next();
				currentSum += arr[p.getPoint()];
			}
			ans = Math.max(ans, Math.max(sum-currentSum, currentSum-arr[p.getPoint()]));
			currentSum -= arr[i];
		}
		System.out.println(ans);
	}
}

class Pointer {
	private int point;
	private int totalSpots;

	public Pointer(int init, int totalSpots) {
		this.point = init;
		this.totalSpots = totalSpots;
	}

	public void next() {
		if(point==totalSpots-1) point = 0;
		else this.point++;
	}

	public int getPoint() {
		return this.point;
	}
}
