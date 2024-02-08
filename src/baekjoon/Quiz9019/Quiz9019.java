package baekjoon.Quiz9019;

// DSLR 4방향 탐색
// D: * 2 % 10000
// S: - 1 if 0 -> 9999
// L: addLast(removeFirst())
// R: addFirst(removeLast())
import java.util.*;
import java.io.*;

public class Quiz9019 {
	private static int[] visit;
	private static final StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while(T --> 0) {
			visit = new int[20000];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			transfer(A, B);
		}
		System.out.println(sb);
	}

	private static void transfer(int A, int B) {
		// A -> B 될 때까지 연산 반복
		ArrayDeque<Package> ad = new ArrayDeque<>();
		ad.addLast(new Package(A, ""));
		visit[A] = 1;
		while(!ad.isEmpty()) {
			Package currentPackage = ad.removeFirst();
			int currentValue = currentPackage.value;
			String curPath = currentPackage.path;
			for(int i=0; i<4; i++) {
				int next = getNext(i, currentValue);
				if(visit[next] == 0) {
					visit[next] = visit[currentValue] + 1;
					ad.addLast(new Package(next, curPath + intToCmd(i)));
					if(next == B) {
						sb.append(curPath).append(intToCmd(i)).append("\n");
						return;
					}
				}
			}
		}
	}
	private static int getNext(int type, int value) {
		// 연산 종류에 따라 연산 결과를 반환
		if(type == 0) return operateD(value);
		else if(type == 1) return operateS(value);
		else if(type == 2) return operateL(value);
		else if(type == 3) return operateR(value);
		else return -1;
	}
	private static int operateD(int value) {
		// D: * 2 % 10000
		return value * 2 % 10000;
	}
	private static int operateS(int value) {
		// S: - 1 if 0 -> 9999
		int next = value - 1;
		return next == -1 ? 9999 : next;
	}
	private static int operateL(int value) {
		// L: addLast(removeFirst())
		int[] prior = intToArr(value);
		int temp = prior[0];
		int next = 0;
		int base = 1000;
		for(int i=1; i<4; i++) {
			next += prior[i] * base;
			base /= 10;
		}
		return next + temp;
	}
	private static int operateR(int value) {
		// R: addFirst(removeLast())
		int[] prior = intToArr(value);
		int temp = prior[3];
		int next = 0;
		int base = 100;
		for(int i=0; i<3; i++) {
			next += prior[i] * base;
			base /= 10;
		}
		return temp * 1000 + next;
	}
	private static int[] intToArr(int value) {
		int[] temp = new int[4];
		int idx = 3;
		while(value != 0) {
			temp[idx--] = value % 10;
			value /= 10;
		}
		return temp;
	}
	private static String intToCmd(int type) {
		if(type == 0) return "D";
		else if(type == 1) return "S";
		else if(type == 2) return "L";
		else if(type == 3) return "R";
		else return "ERROR";
	}
}
class Package {
	int value;
	String path;

	public Package(int value, String path) {
		this.value = value;
		this.path = path;
	}
}
