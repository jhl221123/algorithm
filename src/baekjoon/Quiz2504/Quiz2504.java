package baekjoon.Quiz2504;

import java.util.ArrayDeque;
import java.util.Scanner;

// 전체 시간 복잡도: O(L)
public class Quiz2504 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] input = sc.next().toCharArray();
		ArrayDeque<Element> ad = new ArrayDeque<>();
		boolean invalid = false;
		for(char target : input) {
			if(target == '(' || target == '[') ad.addLast(new Element("Deli", deliToValue(target)));
			else {
				if(ad.isEmpty()) {
					System.out.println(0);
					return ;
				}
				int innerValue = 0;
				Element element = ad.removeLast();
				while(element.getType().equals("Number")) {
					innerValue += element.getValue();
					if(ad.isEmpty())  {
						System.out.println(0);
						return ;
					}
					element = ad.removeLast();
				}
				// 페어가 아닌 경우
				if(element.getValue() != deliToValue(target) || invalid) {
					System.out.println(0);
					return ;
				}
				if(innerValue == 0) innerValue = 1;
				ad.addLast(new Element("Number", innerValue * element.getValue()));
			}
		}
		int ans = 0;
		while(!ad.isEmpty()) {
			Element element = ad.removeLast();
			if(element.getType().equals("Deli")) {
				System.out.println(0);
				return ;
			}
			ans += element.getValue();
		}
		System.out.println(ans);
	}
	private static int deliToValue(char target) {
		if(target == '(' || target == ')') return 2;
		else return 3;
	}
}

class Element {
	private String type;
	private int value;

	public Element(String type, int value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public int getValue() {
		return this.value;
	}
}
