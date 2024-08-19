package programmers.kakao_blind_recruitment_2022;

import java.util.*;

public class ReportResults {
	static HashSet<String> set = new HashSet<>();
	static HashMap<String, Integer> receiverCountMap = new HashMap<>();
	static HashMap<String, Integer> senderCountMap = new HashMap<>();
	public int[] solution(String[] id_list, String[] report, int k) {
		distinctReport(report);
		countReceiver();
		countSender(k);
		return sendMail(id_list);
	}

	private void distinctReport(String[] report) {
		set.addAll(Arrays.asList(report));
	}

	private void countReceiver() {
		for(String str : set) {
			StringTokenizer st = new StringTokenizer(str);
			String sender = st.nextToken();
			String receiver = st.nextToken();
			if(receiverCountMap.containsKey(receiver))
				receiverCountMap.put(receiver, receiverCountMap.get(receiver) + 1);
			else receiverCountMap.put(receiver, 1);
		}
	}

	private void countSender(int k) {
		for(String str : set) {
			StringTokenizer st = new StringTokenizer(str);
			String sender = st.nextToken();
			String receiver = st.nextToken();
			if(receiverCountMap.get(receiver) >= k) {
				if(senderCountMap.containsKey(sender))
					senderCountMap.put(sender, senderCountMap.get(sender) + 1);
				else senderCountMap.put(sender, 1);
			}
		}
	}

	private int[] sendMail(String[] id_list) {
		int[] arr = new int[id_list.length];

		for(int i=0; i<arr.length; i++) {
			String sender = id_list[i];
			arr[i] = senderCountMap.getOrDefault(sender, 0);
		}

		return arr;
	}
}
