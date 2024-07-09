package programmers.kakao_tech_internship_2022;

public class PersonalityTypeTest {
	public static void main(String[] args) {
		String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
		int[] choice = {5, 3, 2, 7, 5};
		String result = solution(survey, choice); // "TCMA"
		System.out.println(result);
	}

	static int[] score = new int[26];
	public static String solution(String[] survey, int[] choices) {
		for(int i=0; i<survey.length; i++) {
			checkScore(survey[i].charAt(0) - 'A', survey[i].charAt(1) - 'A', choices[i]);
		}
		return getType();
	}

	private static void checkScore(int l, int r, int s) {
		if(s > 4) {
			score[r] += (s - 4);
		} else if(s < 4) {
			score[l] += (4 - s);
		}
	}

	private static String getType() {
		StringBuilder sb = new StringBuilder();
		if(score[17] >= score[19]) sb.append('R');
		else sb.append('T');
		if(score[2] >= score[5]) sb.append('C');
		else sb.append('F');
		if(score[9] >= score[12]) sb.append('J');
		else sb.append('M');
		if(score[0] >= score[13]) sb.append('A');
		else sb.append('N');
		return sb.toString();
	}
}
