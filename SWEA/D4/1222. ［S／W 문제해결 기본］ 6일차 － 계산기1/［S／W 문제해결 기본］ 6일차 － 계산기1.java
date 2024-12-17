import java.io.*;
import java.util.*;

public class Solution {

	private static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {
			stack = new Stack<>();
			int cnt = 0;
			int length = Integer.parseInt(br.readLine());
			String formula = br.readLine();
			for (int i = 0; i < length; i++) {

				if (formula.charAt(i) >= '0' && formula.charAt(i) <= '9') {
					stack.push(formula.charAt(i) - '0');
				} else {
					cnt++;
				}
			}
			
			while(cnt != 0) {
				cnt--;
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.push(num1 + num2);
			}
			
			sb.append("#").append(t).append(" ").append(stack.pop()).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
