package programmers.basic.lv1;

public class DivideString {
	public static void main(String[] args) {
		int result = solution("banana"); // 3
		System.out.println(result);
	}
	public static int solution(String s) {
		int idx = 0;
		int comp = 0;
		char x = s.charAt(idx++);
		int l = 1;
		int r = 0;
		int answer = 1;
		while(idx < s.length()) {
			if(s.charAt(idx++) == x) l++;
			else r++;
			if(l==r) {
				answer++;
				comp = idx - 1;
				if(idx < s.length()) x = s.charAt(idx++);
				l = 1;
				r = 0;
			}
		}
		if(comp == idx - 1 && comp != 0) answer--;
		return answer;
	}
}
