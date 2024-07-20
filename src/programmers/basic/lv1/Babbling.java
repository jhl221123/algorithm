package programmers.basic.lv1;

public class Babbling {
	public static void main(String[] args) {
		int result = solution(new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"}); // 2
		System.out.println(result);
	}
	public static int solution(String[] babbling) {
		int answer = 0;
		for(String b : babbling) {
			if(b.length() == 1) continue;
			boolean flag = true;
			String prior = "init";
			while(flag && b.length() > 1) {

				char f = b.charAt(0);
				char s = b.charAt(1);
				char t = b.length() > 2 ? b.charAt(2) : ' ';

				if(!prior.equals("aya") && b.length() > 2 && f == 'a' && s == 'y' && t == 'a') {
					prior = "aya";
					b = b.substring(3);
				} else if(!prior.equals("woo") && b.length() > 2 && f == 'w' && s == 'o' && t == 'o') {
					prior = "woo";
					b = b.substring(3);
				} else if(!prior.equals("ye") && f == 'y' && s == 'e') {
					prior = "ye";
					b = b.substring(2);
				} else if(!prior.equals("ma") && f == 'm' && s == 'a') {
					prior = "ma";
					b = b.substring(2);
				} else {
					flag = false;
					break;
				}
			}
			if(flag && b.length() == 0) answer++;
		}
		return answer;
	}
}
