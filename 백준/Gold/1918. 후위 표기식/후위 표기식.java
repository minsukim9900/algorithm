import java.io.*;
import java.util.*;

public class Main {

	private static Map<Character, Integer> map = new HashMap<>();
	static {
		map.put('+', 1);
		map.put('-', 1);
		map.put('/', 2);
		map.put('*', 2);
		map.put('(', 0);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String infix = br.readLine();
		System.out.println(infixToPostfix(infix));
	}
	
	private static String infixToPostfix(String infix) {
		String postfix = "";
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < infix.length(); i++) {

			char c = infix.charAt(i);

			if ('A' <= c && c <= 'Z') {
				postfix += c;
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.peek() == '(') {
					stack.pop();
				} else {
					while (stack.peek() != '(') {
						postfix += stack.pop();
					}
					stack.pop();
				}
			} else {
				while (!stack.isEmpty() && stack.peek() != '(' && map.get(stack.peek()) >= map.get(c)) {
					postfix += stack.pop();
				}

				stack.push(c);

			}

		}
		while (!stack.isEmpty()) {
			postfix += stack.pop();
		}

		return postfix;

	}

}