package baekjoon.Quiz4673;

// 크기 10000 체크 배열로 셀프 넘버 관리
// 10000번 반복을 통해 함수로 생성된 숫자는 생성자가 존재하는 것으로 간주
// 체크안된 숫자는 생성자가 없는 것으로 간주
public class Quiz4673 {
	public static void main(String[] args) {
		boolean[] check = new boolean[20000];
		for(int i=1; i<=10000; i++) {
			int num = i;
			int temp = i;
			do {
				num += temp%10;
				temp /= 10;
			} while(temp != 0);
			check[num] = true;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=10000; i++) {
			if(!check[i]) sb.append(i).append("\n");
		}
		System.out.println(sb);
	}
}
