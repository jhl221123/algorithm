package programmers.kakao_blind_recruitment_2023;

public class DeliveryAndPickup {
	public static void main(String[] args) {
		// String[] arr = {"O.X", ".O.", "..X"}; // 1
		int[] deliveries = {1, 0, 3, 1, 2};
		int[] pickups = {0, 3, 0, 4, 0};
		long result = solution(4, 5, deliveries, pickups);
		System.out.println(result);
	}
	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		for (int i = deliveries.length - 1; i >= 0;) {
			if (deliveries[i] == 0 && pickups[i] == 0) {
				i--;
				continue;
			}
			excute(cap, deliveries, i);
			excute(cap, pickups, i);
			answer += (i + 1) * 2;
		}
		return answer;
	}

	private static void excute(int cap, int[] arr, int index) {
		while (index >= 0) {
			if (cap >= arr[index]) {
				cap -= arr[index];
				arr[index--] = 0;
			} else {
				arr[index] -= cap;
				break;
			}
		}
	}
}
