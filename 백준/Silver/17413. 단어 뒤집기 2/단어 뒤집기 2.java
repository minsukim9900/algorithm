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
		for (int i = 0; i < words.length; i++) {

			if (words[i] == '<') {

				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				flag = true;
				sb.append(words[i]);
				continue;
			}

			if (words[i] == '>') {
				sb.append(words[i]);
				flag = false;
				continue;
			}

			if (flag) {
				sb.append(words[i]);
				continue;
			}

			if (words[i] == ' ') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(words[i]);
				continue;
			}
			stack.push(words[i]);
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}
}