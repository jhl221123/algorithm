package programmers.basic.lv1;

public class OurCode {

	public static void main(String[] args) {
		String s = "aukks";
		String skip = "wbqd";
		int index = 5;
		String result = solution(s, skip, index); // "happy"
		System.out.println(result);
	}

	public static String solution(String s, String skip, int index) {
		StringBuilder answer = new StringBuilder();
		for(int i=0; i<s.length(); i++) {
			char next = s.charAt(i);
			int count = 0;
			while(count < index) {
				next = (char)(next + 1);
				if(next > 'z') next = (char)(next - ('z' + 1) + 'a');
				if(skip.contains(String.valueOf(next))) continue;
				count++;
			}
			answer.append(next);
		}
		return answer.toString();
	}
}
