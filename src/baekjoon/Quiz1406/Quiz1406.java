package baekjoon.Quiz1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

// 전체 시간 복잡도: O(M*1)
public class Quiz1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = str.length();
		List<Character> list = new LinkedList<>();
		for(int i=0; i<N; i++) {
			list.add(str.charAt(i));
		}

		int M = Integer.parseInt(br.readLine());
		Cursor cursor = new Cursor(list.listIterator(N));
		// O(M*1)
		while(M-- > 0) {
			String command = br.readLine();
			switch(command.charAt(0)) {
				case 'L': cursor.L();
					break;
				case 'D': cursor.D();
					break;
				case 'B': cursor.B();
					break;
				case 'P': cursor.P(command.charAt(2));
			}
		}
		String result = list.stream()
			.map(String::valueOf)
			.collect(Collectors.joining());
		System.out.println(result);
	}
}

class Cursor {
	ListIterator<Character> iterator;

	public Cursor(ListIterator<Character> iterator) {
		this.iterator = iterator;
	}
	// L: 커서 idx -1, if(idx>0)
	public void L() {
		if(iterator.hasPrevious()) iterator.previous();
	}
	// D: 커서 idx +1, if(idx<N)
	public void D() {
		if(iterator.hasNext()) iterator.next();
	}
	// B: if(idx>0) remove(idx-1)
	public void B() {
		if(iterator.hasPrevious()) {
			iterator.previous();
			iterator.remove();
		}
	}
	// P: insert(idx)
	public void P(char target) {
		iterator.add(target);
	}
}