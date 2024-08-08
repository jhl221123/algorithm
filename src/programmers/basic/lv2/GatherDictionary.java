package programmers.basic.lv2;

public class GatherDictionary {
	public static void main(String[] args) {
		int result = solution("I"); // 1563
		System.out.println(result);
	}
	static int count;
	static char[] gather = new char[] {'A', 'E', 'I', 'O', 'U'};
	public static int solution(String word) {
		char[] arr = new char[5];
		perm(arr, 0, word);
		return count;
	}
	private static boolean perm(char[] arr, int idx, String word) {
		if(String.valueOf(arr, 0, idx).equals(word)) {
			return true;
		}

		if(idx == 5) {
			return false;
		}

		for(int i = 0; i < 5; i++) {
			arr[idx] = gather[i];
			count++;
			if(perm(arr, idx + 1, word)) {
				return true;
			}
		}

		return false;
	}
}
