package swea.d2;

import java.util.*;

public class Quiz1948 {
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 각 모서리 달 사이의 달을 대상으로 반복문 수행 + 모서리 일수
			// 사이 달 일수를 구하는 메서드 활용
			int startMonth = sc.nextInt();
			int startDay = sc.nextInt();
			int endMonth = sc.nextInt();
			int endDay = sc.nextInt();
			int midDays = getMidDays(startMonth+1, endMonth-1);
			int startDays = getStartDays(startMonth, startDay);
			int totalDays = startMonth == endMonth ? endDay - startDay + 1 : midDays+startDays+endDay;
			System.out.println("#"+test_case+" "+totalDays);
		}
	}
	private static int getMidDays(int leftMonth, int rightMonth) {
		// 월 차이가 1 이하일 경우 처리
		int days = 0;
		if(leftMonth>rightMonth) return days;
		for(int i=leftMonth; i<=rightMonth; i++) {
			if(i==2) days+=28;
			else if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12) days+=31;
			else days+=30;
		}
		return days;
	}
	private static int getStartDays(int startMonth, int startDay) {
		int daysOfcurrentMonth = 0;
		if(startMonth==2) daysOfcurrentMonth=28;
		else if(startMonth==1 || startMonth==3 || startMonth==5 || startMonth==7 || startMonth==8 || startMonth==10 || startMonth==12) daysOfcurrentMonth=31;
		else daysOfcurrentMonth=30;
		return daysOfcurrentMonth - startDay + 1;
	}
}
