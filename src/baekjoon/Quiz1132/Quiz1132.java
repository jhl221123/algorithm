package baekjoon.Quiz1132;

import java.io.*;

public class Quiz1132 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] alphabetNumbers = inputAlphabetNumbers(N, br);
		boolean[] isFirstAlphabet = checkFirstAlphabet(N, alphabetNumbers);
		long[] weight = calculateAlphabetWeight(N, alphabetNumbers);
		int[] alphabetToNum = fixNumberOfAlphabet(weight, isFirstAlphabet);
		long sum = sumNumbers(N, alphabetNumbers, alphabetToNum);
		System.out.println(sum);
	}

	private static String[] inputAlphabetNumbers(int N, BufferedReader br) throws IOException {
		String[] nums = new String[N];
		for(int i = 0; i< N; i++) {
			nums[i] = br.readLine();
		}
		return nums;
	}

	private static long sumNumbers(int N, String[] nums, int[] alphabetToNum) {
		long sum = 0;
		for(int i = 0; i< N; i++) {
			String num = nums[i];
			long base = 1L;
			for(int j=num.length()-1; j>=0; j--) {
				sum += alphabetToNum[num.charAt(j) - 'A'] * base;
				base *= 10;
			}
		}
		return sum;
	}

	private static int[] fixNumberOfAlphabet(long[] count, boolean[] first) {
		int[] alphabetToNum = new int[10];
		boolean[] visit = new boolean[10];
		boolean usedZero = false;
		int fixNum = 1;
		for(int i=0; i<10; i++) {
			int min=0;
			long minCount=10000000000000L;
			for(int j=0; j<10; j++) {
				if(visit[j]) continue;
				if(minCount > count[j]) {
					minCount = count[j];
					min = j;
				}
			}
			visit[min] = true;
			// 첫 번째 자리에 위치하지 않으면서, 가중치가 가장 낮은 알파벳이 0이 된다.
			if(!usedZero && !first[min]) {
				alphabetToNum[min] = 0;
				usedZero = true;
			} else {
				alphabetToNum[min] = fixNum;
				fixNum++;
			}
		}
		return alphabetToNum;
	}

	private static long[] calculateAlphabetWeight(int N, String[] nums) {
		long[] count = new long[10];
		for(int i = 0; i< N; i++) {
			String num = nums[i];
			long base = 1L;
			for(int j=num.length()-1; j>=0; j--) {
				count[num.charAt(j) - 'A'] += base;
				base *= 10;
			}
		}
		return count;
	}

	private static boolean[] checkFirstAlphabet(int N, String[] nums) {
		boolean[] first = new boolean[10];
		for(int i = 0; i< N; i++) {
			first[nums[i].charAt(0) - 'A'] = true;
		}
		return first;
	}
}
