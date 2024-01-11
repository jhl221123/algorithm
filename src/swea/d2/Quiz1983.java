package swea.d2;

import java.util.*;

public class Quiz1983 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 입력 순서별 총 점수를 계산
			// hashmap key: 순서, map: 점수
			// k의 value가 어떤 범위에 포함되는 지 확인
			int N = sc.nextInt();
			int K = sc.nextInt();
			Map<Integer, Double> map = new HashMap<>();
			for(int i=1; i<=N; i++) {
				double mid = sc.nextDouble();
				double end = sc.nextDouble();
				double task = sc.nextDouble();
				double totalScore = (mid * 0.35) + (end * 0.45) + (task * 0.20);
				map.put(i, totalScore);
			}
			List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(map.entrySet());
			entryList.sort(Map.Entry.comparingByValue());
			int rank = N;
			String result = null;
			for(Map.Entry<Integer, Double> entry : entryList) {
				if(entry.getKey() == K) {
					result = getResult(rank, N);
					break;
				}
				rank--;
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
	private static String getResult(int rank, int N) {
		int base = N / 10;
		if(rank <= base) return "A+";
		else if(rank <= base * 2) return "A0";
		else if(rank <= base * 3) return "A-";
		else if(rank <= base * 4) return "B+";
		else if(rank <= base * 5) return "B0";
		else if(rank <= base * 6) return "B-";
		else if(rank <= base * 7) return "C+";
		else if(rank <= base * 8) return "C0";
		else if(rank <= base * 9) return "C-";
		else return "D0";
	}
}
