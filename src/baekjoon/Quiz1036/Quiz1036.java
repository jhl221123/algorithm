package baekjoon.Quiz1036;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Quiz1036 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] nums = new String[N];
		for(int i=0; i<N; i++) {
			nums[i] = br.readLine();
		}
		int K = Integer.parseInt(br.readLine());

		BigInteger[] weights = calculateWeights(nums);
		Set<Character> set = selectTopK(weights, K);
		changeTargetsToZ(nums, set);
		String sum = sumOf(nums);
		System.out.println(sum);
	}

	private static String sumOf(String[] nums) {
		BigInteger sum = BigInteger.ZERO;
		for(String num : nums) {
			BigInteger next = new BigInteger(num, 36);
			sum = sum.add(next);
		}
		return sum.toString(36).toUpperCase();
	}

	private static void changeTargetsToZ(String[] nums, Set<Character> set) {
		for(int i = 0; i< nums.length; i++) {
			String num = nums[i];
			for(Character c : set) {
				num = num.replace(c, 'Z');
			}
			nums[i] = num;
		}
	}

	private static Set<Character> selectTopK(BigInteger[] weights, int K) {
		PriorityQueue<BigInteger[]> pq = new PriorityQueue<>((o1, o2) -> o2[1].compareTo(o1[1]));
		for(int i=0; i<35; i++) {
			if(weights[i].equals(BigInteger.ZERO)) continue;
			pq.offer(new BigInteger[] {BigInteger.valueOf(i), weights[i]});
		}

		Set<Character> set = new HashSet<>();
		for (int i = 0; i < K; i++) {
			if(pq.isEmpty()) break;
			BigInteger[] poll = pq.poll();
			int index = Integer.parseInt(String.valueOf(poll[0]));
			char changeTarget = index < 10 ? (char)('0' + index) : (char)('A' + (index - 10));
			set.add(changeTarget);
		}
		return set;
	}

	private static BigInteger[] calculateWeights(String[] nums) {
		BigInteger[] weights = new BigInteger[36];
		Arrays.fill(weights, BigInteger.ZERO);
		for(String num : nums) {
			BigInteger digit = BigInteger.ONE;
			for(int i=num.length()-1; i>=0; i--) {
				int target = num.charAt(i) <= '9' ? num.charAt(i) - '0' : num.charAt(i) - 'A' + 10;
				weights[target] = weights[target].add(BigInteger.valueOf(35 - target).multiply(digit));
				digit = digit.multiply(BigInteger.valueOf(36));
			}
		}
		return weights;
	}
}
