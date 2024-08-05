package programmers.basic.lv2;

public class MasterOfPlayingAlone {
	public static void main(String[] args) {
		int result = solution(new int[] {8,6,3,7,2,5,1,4});// 12
		System.out.println(result);
	}
	public static int solution(int[] cards) {
		int max = 0;
		Card[] arr = createCardDeck(cards);
		for(int i=0; i<cards.length; i++) {
			int first = open(i, arr);
			for(int j=0; j<cards.length; j++) {
				if(i == j) continue;
				int second = open(j, arr);
				max = Math.max(max, first * second);
				cover(j, arr);
			}
			cover(i, arr);
		}
		return max;
	}
	private static Card[] createCardDeck(int[] cards) {
		Card[] arr = new Card[cards.length];
		for(int i=0; i<cards.length; i++) {
			arr[i] = new Card(cards[i] - 1, -1);
		}
		return arr;
	}
	private static int open(int idx, Card[] arr) {
		int cnt = 0;
		int next = idx;
		while(arr[next].visited == -1) {
			cnt++;
			arr[next].visited = idx;
			next = arr[next].num;
		}
		return cnt;
	}
	private static void cover(int idx, Card[] arr) {
		for(Card card : arr) {
			if(card.visited == idx) card.visited = -1;
		}
	}
	static class Card {
		int num;
		int visited;

		public Card(int num, int visited) {
			this.num = num;
			this.visited = visited;
		}
	}
}
