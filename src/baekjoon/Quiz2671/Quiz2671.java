package baekjoon.Quiz2671;

import java.io.*;
import java.util.regex.Pattern;

public class Quiz2671 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		String regex = "^(100+1+|01)+$";
		boolean isMatch = Pattern.matches(regex, target);
		if(isMatch) System.out.println("SUBMARINE");
		else System.out.println("NOISE");
	}
}
