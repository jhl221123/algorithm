package baekjoon.Quiz7490;

import java.io.*;

public class Quiz7490 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			dfs(1, N, ans, new Calculator());
			ans.append("\n");
		}

		System.out.print(ans);
	}

	private static void dfs(int num, int N, StringBuilder ans, Calculator cal) {
		if(num >= N) {
			cal.inputOperand(num);
			cal.operation();
			if(cal.getResult() == 0) {
				ans.append(cal.getFormula()).append("\n");
			}
			return;
		}
		cal.inputOperand(num);
		dfs(num+1, N, ans, Calculator.copyOf(cal).inputOperator(" "));
		dfs(num+1, N, ans, Calculator.copyOf(cal).inputOperator("+"));
		dfs(num+1, N, ans, Calculator.copyOf(cal).inputOperator("-"));
	}

	static class Calculator {

		public static final int MAX = 1_000_000_000;
		public static final String INIT = "init";

		private int operand1;
		private int operand2;
		private String operator;
		private boolean isAppend;
		private StringBuilder formula;

		public Calculator() {
			this.operand1 = MAX;
			this.operand2 = MAX;
			this.operator = INIT;
			this.isAppend = false;
			this.formula = new StringBuilder();
		}

		public Calculator(int operand1, int operand2, String operator, boolean isAppend, StringBuilder formula) {
			this.operand1 = operand1;
			this.operand2 = operand2;
			this.operator = operator;
			this.isAppend = isAppend;
			this.formula = formula;
		}

		public static Calculator copyOf(Calculator cal) {
			return new Calculator(cal.operand1, cal.operand2, cal.operator, cal.isAppend, new StringBuilder(cal.formula));
		}

		public void inputOperand(int od) {
			formula.append(od);
			if(operand1 == MAX) {
				operand1 = od;
			} else if(isAppend) {
				isAppend = false;
				if(operand2 == MAX) {
					operand1 = operand1 * 10 + od;
				} else {
					operand2 = operand2 * 10 + od;
				}
			} else {
				operand2 = od;
			}
		}

		public Calculator inputOperator(String or) {
			if(" ".equals(or)) {
				this.isAppend = true;
				this.formula.append(or);
				return this;
			}
			if(!INIT.equals(this.operator)) {
				operation();
			}
			this.operator = or;
			this.formula.append(or);
			return this;
		}

		public void operation() {
			if("+".equals(this.operator)) {
				operand1 += operand2;
			} else if("-".equals(this.operator)) {
				operand1 -= operand2;
			}
		}

		public int getResult() {
			return this.operand1;
		}

		public String getFormula() {
			return this.formula.toString();
		}
	}
}
