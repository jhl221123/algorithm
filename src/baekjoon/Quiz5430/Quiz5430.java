package baekjoon.Quiz5430;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

// 전체 시간 복잡도: O(T * max(P, N))
public class Quiz5430 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String ns = br.readLine();
			String[] arr = ns.substring(1, ns.length()-1).split(",");
			LinkedList<String> list = new LinkedList<>();
			for(int i=0; i<n; i++) {
				list.addLast(arr[i]);
			}
			boolean isReverse = false;
			boolean isValid = true;
			for(int i=0; i<p.length(); i++) {
				char target = p.charAt(i);
				if(target=='R') isReverse = !isReverse;
				else if(target=='D') {
					if(list.isEmpty()) {
						isValid = false;
						break;
					}
					if(!isReverse) list.removeFirst();
					else list.removeLast();
				}
			}
			if(isReverse) Collections.reverse(list);
			String result = list.stream().collect(Collectors.joining(",", "[", "]"));
			if(!isValid) result = "error";
			bw.write(result + "\n");
		}
		bw.flush();
	}
}
