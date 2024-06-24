package programmers.pcce;

import java.util.*;

public class DataAnalysis {
	public static void main(String[] args) {
		int[][] board = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}};
		int[][] result = solution(board, "date", 20300501, "remain"); // [[3,20300401,10,8],[1,20300104,100,80]]
		for(int[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}
	public static int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
		List<Data> dataList = transferArrToList(data);
		removeData(dataList, ext, val_ext);
		sort(dataList, sort_by);
		return transferListToArr(dataList);
	}

	private static List<Data> transferArrToList(int[][] data) {
		List<Data> list = new ArrayList<>();
		for(int i=0; i<data.length; i++) {
			int[] row = data[i];
			list.add(new Data(row));
		}
		return list;
	}

	private static int[][] transferListToArr(List<Data> list) {
		int[][] answer = new int[list.size()][4];
		for(int i=0; i<list.size(); i++) {
			answer[i] = list.get(i).info;
		}
		return answer;
	}

	private static int transferToInt(String ext) {
		if("code".equals(ext)) return 0;
		else if("date".equals(ext)) return 1;
		else if("maximum".equals(ext)) return 2;
		else if("remain".equals(ext)) return 3;
		else return -1;
	}

	private static void removeData(List<Data> list, String ext, int val_ext) {
		int base = transferToInt(ext);
		for(int i=0; i<list.size(); i++) {
			Data data = list.get(i);
			if(data.info[base] >= val_ext) data.flag = true;
		}
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).flag) list.removeIf((Data d) -> d.flag);
		}
	}

	private static void sort(List<Data> list, String sort_by) {
		int base = transferToInt(sort_by);
		Collections.sort(list, (o1, o2) -> o1.info[base] - o2.info[base]);
	}

	static class Data {
		int[] info;
		boolean flag = false;

		public Data(int[] info) {
			this.info = info;
		}
	}
}
