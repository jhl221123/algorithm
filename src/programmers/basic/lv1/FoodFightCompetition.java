package programmers.basic.lv1;

public class FoodFightCompetition {
	public static void main(String[] args) {
		int[] food = {1, 3, 4, 6};
		String result = solution(food); // "1223330333221"
		System.out.println(result);
	}
	public static String solution(int[] food) {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<food.length; i++) {
			int num = food[i] / 2;
			for(int j=0; j<num; j++) {
				sb.append(i);
			}
		}
		String prefix = sb.toString();
		sb.reverse();
		String subfix = sb.toString();
		return prefix + "0" + subfix;
	}
}
