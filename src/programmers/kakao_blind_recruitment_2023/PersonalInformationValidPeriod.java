package programmers.kakao_blind_recruitment_2023;

import java.util.*;

public class PersonalInformationValidPeriod {
	public static void main(String[] args) {
		int[] result = solution(
			"2022.05.19",
			new String[]{"A 6", "B 12", "C 3"},
			new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}
		); // [1, 3]
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String today, String[] terms, String[] privacies) {
		Date todayDate = toDate(-1, today, new Term(-1, -1));
		int[] term = initTerms(terms);
		Date[] dates = initPrivacies(privacies, term);
		List<Integer> answer = new ArrayList<>();

		for(Date date : dates) {
			Date appliedDate = date.applyCondition();
			if(appliedDate.lessThan(todayDate)) answer.add(date.no);
		}
		return listToArr(answer);
	}

	private static int[] initTerms(String[] terms) {
		int[] arr = new int[26];
		for(String term : terms) {
			StringTokenizer st = new StringTokenizer(term);
			int idx = st.nextToken().charAt(0) - 'A';
			int period = Integer.parseInt(st.nextToken());
			arr[idx] = period;
		}
		return arr;
	}

	private static Date[] initPrivacies(String[] privacies, int[] term) {
		Date[] dates = new Date[privacies.length];
		for(int i=0; i<privacies.length; i++) {
			StringTokenizer st = new StringTokenizer(privacies[i]);
			String date = st.nextToken();
			int condition = st.nextToken().charAt(0) - 'A';
			dates[i] = toDate(i+1, date, new Term(condition, term[condition]));
		}
		return dates;
	}

	private static Date toDate(int no, String date, Term term) {
		StringTokenizer st = new StringTokenizer(date, ".");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
		return new Date(no, year, month, day, term);
	}

	private static int[] listToArr(List<Integer> list) {
		Collections.sort(list);
		int[] arr = new int[list.size()];
		for(int i=0; i<list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	static class Date {
		int no, year, month, day;
		Term term;

		public Date(int no, int year, int month, int day, Term term) {
			this.no = no;
			this.year = year;
			this.month = month;
			this.day = day;
			this.term = term;
		}

		public Date applyCondition() {
			int year = this.year;
			int month = this.term.period + this.month;
			int day = this.day - 1;
			if(day == 0) {
				day = 28;
				month -= 1;
			}
			if(month > 12) {
				if(month % 12 == 0) {
					year += (month / 12) - 1;
					month = 12;
				} else {
					year += month / 12;
					month %= 12;
				}
			}
			return new Date(this.no, year, month, day, this.term);
		}

		public boolean lessThan(Date date) {
			if(this.year > date.year) return false;
			else if(this.year < date.year) return true;
			else {
				if(this.month > date.month) return false;
				else if(this.month < date.month) return true;
				else {
					if(this.day > date.day) return false;
					else if(this.day < date.day) return true;
					else {
						return false;
					}
				}
			}
		}
	}

	static class Term {
		int condition;
		int period;

		public Term(int condition, int period) {
			this.condition = condition;
			this. period = period;
		}
	}
}
