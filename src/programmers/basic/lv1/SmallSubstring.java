package programmers.basic.lv1;

public class SmallSubstring {
	public static void main(String[] args) {
		int result = solution("3141592", "271"); // 2
		System.out.println(result);
	}
	public static int solution(String t, String p) {
		int answer = 0;
		for(int i=0; i<=t.length() - p.length(); i++) {
			boolean isLessThanP = true;
			int j = i;
			int k = 0;
			while(k < p.length()) {
				if(t.charAt(j) == p.charAt(k)) {
					j++;
					k++;
					continue;
				}
				else if(t.charAt(j) < p.charAt(k)) break;
				else if(t.charAt(j) > p.charAt(k)) {
					isLessThanP = false;
					break;
				}
				else System.out.println("Error");
			}
			if(isLessThanP) answer++;
		}
		return answer;
	}
}
