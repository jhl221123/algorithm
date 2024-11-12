package baekjoon.Quiz2608;

import java.io.*;
import java.util.*;

public class Quiz2608 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String romeA = br.readLine();
		String romeB = br.readLine();
		Map<String, Integer> nums = createMap();
		int araA = romeToAra(romeA, nums);
		int araB = romeToAra(romeB, nums);

		int sum = araA + araB;
		String romeSum = araToRome(sum, nums);
		System.out.println(sum);
		System.out.println(romeSum);
	}

	private static int romeToAra(String rome, Map<String, Integer> nums) {
		int ara = 0;
		for(int i=0; i<rome.length(); i++) {
			// 왼쪽이 더 작은 수
			String left = String.valueOf(rome.charAt(i));
			if(i+1 < rome.length()) {
				String right = String.valueOf(rome.charAt(i+1));
				if(nums.get(left) < nums.get(right)) {
					String append = left + right;
					ara += nums.get(append);
					i++;
					continue;
				}
			}

			ara += nums.get(left);
		}
		return ara;
	}

	private static String araToRome(int ara, Map<String, Integer> nums) {
		StringBuilder rome = new StringBuilder();
		String[] arr = new String[] {"M", "M", "M", "CM", "D", "CD",
			"C", "C", "C", "XC", "L", "XL",
			"X", "X", "X", "IX", "V", "IV", "I", "I", "I"};
		for(String target : arr) {
			int araTarget = nums.get(target);
			if(ara < araTarget) continue;
			ara -= araTarget;
			rome.append(target);
		}

		return rome.toString();
	}

	private static Map<String, Integer> createMap() {
		Map<String, Integer> map = new HashMap<>();
		map.put("M", 1000);
		map.put("CM", 900);
		map.put("D", 500);
		map.put("CD", 400);
		map.put("C", 100);
		map.put("XC", 90);
		map.put("L", 50);
		map.put("XL", 40);
		map.put("X", 10);
		map.put("IX", 9);
		map.put("V", 5);
		map.put("IV", 4);
		map.put("I", 1);
		return map;
	}
}
