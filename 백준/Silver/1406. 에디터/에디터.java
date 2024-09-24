import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int N = Integer.parseInt(br.readLine());
		Stack<Character> stack = new Stack<>();
		Stack<Character> temp = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
		}

		for (int m = 0; m < N; m++) {
			String order = br.readLine();
			if (order.charAt(0) == 'P') {
				stack.push(order.charAt(2));
			}
			else if (order.charAt(0) == 'L') {
				if (stack.size() == 0) {
					continue;
				}
				temp.push(stack.pop());
			}
			else if (order.charAt(0) == 'B') {
				if (stack.isEmpty()) {
					continue;
				}
				else {
					stack.pop();
				}
			}
			else if (order.charAt(0) == 'D') {
				if (temp.isEmpty()) {
					continue;
				}
				stack.push(temp.pop());
			}
		}
		int nstack = stack.size();
		int ntemp = temp.size() + nstack;


		for (int c = 0; c < nstack; c++) {
			temp.push(stack.pop());
		}
		for (int d = 0; d < ntemp; d++) {
			sb.append(temp.pop());
		}
		System.out.println(sb.toString());
	}
}