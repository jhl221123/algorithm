package baekjoon.Quiz14370;

import java.io.*;

public class Quiz14370 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();

		for(int t=1; t<=T; t++) {
			String code = br.readLine();
			String phoneNumber = decode(code);
			ans.append("Case").append(" ").append("#").append(t).append(":").append(" ")
				.append(phoneNumber).append("\n");
		}

		System.out.print(ans);
	}

	private static String decode(String code) {
		int[] nums = new int[10];
		int[] alphabets = new int[26];

		for(int i=0; i<code.length(); i++) {
			alphabets[code.charAt(i) - 'A']++;
		}

		select8(alphabets, nums);
		select3(alphabets, nums);
		select4(alphabets, nums);
		select5(alphabets, nums);
		select6(alphabets, nums);
		select7(alphabets, nums);
		select2(alphabets, nums);
		select0(alphabets, nums);
		select1(alphabets, nums);
		select9(alphabets, nums);

		StringBuilder number = new StringBuilder();
		for(int i=0; i<10; i++) {
			while(nums[i] > 0) {
				number.append(i);
				nums[i]--;
			}
		}

		return number.toString();
	}

	private static void select0(int[] alphabets, int[] nums) {
		if(alphabets[25] > 0) {
			int count = alphabets[25];
			nums[0] += count;
			alphabets[25] -= count; // z
			alphabets[4] -= count; // e
			alphabets[17] -= count; // r
			alphabets[14] -= count; // o
		}
	}

	private static void select1(int[] alphabets, int[] nums) {
		if(alphabets[14] > 0) {
			int count = alphabets[14];
			nums[1] += count;
			alphabets[14] -= count; // o
			alphabets[4] -= count; // e
			alphabets[13] -= count; // n
		}
	}

	private static void select2(int[] alphabets, int[] nums) {
		if(alphabets[22] > 0) {
			int count = alphabets[22];
			nums[2] += count;
			alphabets[19] -= count; // t
			alphabets[22] -= count; // w
			alphabets[14] -= count; // o
		}
	}

	private static void select3(int[] alphabets, int[] nums) {
		if(alphabets[7] > 0) {
			int count = alphabets[7];
			nums[3] += count;
			alphabets[4] -= (count * 2); // e
			alphabets[7] -= count; // h
			alphabets[17] -= count; // r
			alphabets[19] -= count; // t
		}
	}

	private static void select4(int[] alphabets, int[] nums) {
		if(alphabets[20] > 0) {
			int count = alphabets[20];
			nums[4] += count;
			alphabets[5] -= count; // f
			alphabets[14] -= count; // o
			alphabets[17] -= count; // r
			alphabets[20] -= count; // u
		}
	}

	private static void select5(int[] alphabets, int[] nums) {
		if(alphabets[5] > 0) {
			int count = alphabets[5];
			nums[5] += count;
			alphabets[5] -= count; // f
			alphabets[8] -= count; // i
			alphabets[21] -= count; // v
			alphabets[4] -= count; // e
		}
	}

	private static void select6(int[] alphabets, int[] nums) {
		if(alphabets[23] > 0) {
			int count = alphabets[23];
			nums[6] += count;
			alphabets[18] -= count; // s
			alphabets[8] -= count; // i
			alphabets[23] -= count; // x
		}
	}

	private static void select7(int[] alphabets, int[] nums) {
		if(alphabets[18] > 0) {
			int count = alphabets[18];
			nums[7] += count;
			alphabets[18] -= count; // s
			alphabets[4] -= (count * 2); // e
			alphabets[21] -= count; // v
			alphabets[13] -= count; // n
		}
	}

	private static void select8(int[] alphabets, int[] nums) {
		if(alphabets[6] > 0) {
			int count = alphabets[6];
			nums[8] += count;
			alphabets[4] -= count; // e
			alphabets[6] -= count; // g
			alphabets[7] -= count; // h
			alphabets[8] -= count; // i
			alphabets[19] -= count; // t
		}
	}

	private static void select9(int[] alphabets, int[] nums) {
		if(alphabets[8] > 0) {
			int count = alphabets[8];
			nums[9] += count;
			alphabets[13] -= (count * 2); // n
			alphabets[4] -= count; // e
			alphabets[8] -= count; // i
		}
	}
}
