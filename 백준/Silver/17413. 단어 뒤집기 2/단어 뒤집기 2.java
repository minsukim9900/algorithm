import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		char[] words = br.readLine().toCharArray();

		boolean flag = false;
		Stack<Character> stack = new Stack<>();
		for (char c : words) {
			if (flag) {
				sb.append(c);
				if (c == '>') {
					flag = false;
				}
				continue;
			}

			if (c == '<') {
				flush(stack, sb);
				sb.append(c);
				flag = true;
				continue;
			}

			if (c == ' ') {
				flush(stack, sb);
				sb.append(c);
				continue;
			}
			
			stack.push(c);
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}

	private static void flush(Stack<Character> stack, StringBuilder sb) {
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
	}
}