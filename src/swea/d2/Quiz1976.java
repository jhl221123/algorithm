package swea.d2;

import java.util.*;

public class Quiz1976 {
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 시계 클래스 생성
			Clock baseTime = new Clock(sc.nextInt(), sc.nextInt());
			Clock targetTime = new Clock(sc.nextInt(), sc.nextInt());
			Clock resultTime = baseTime.addTime(targetTime);
			System.out.println("#"+test_case+" "+resultTime.getHour() + " " + resultTime.getMin());
		}
	}
}
class Clock {
	private int hour;
	private int min;

	public Clock(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}

	public int getHour() {
		return this.hour;
	}

	public int getMin() {
		return this.min;
	}

	public Clock addTime(Clock target) {
		int afterHour = this.hour + target.getHour();
		int afterMin = this.min + target.getMin();

		if(afterMin > 59) {
			afterHour++;
			afterMin %= 60;
		}
		if(afterHour == 24) afterHour = 12;
		else if(afterHour > 12) afterHour %= 12;
		return new Clock(afterHour, afterMin);
	}
}
