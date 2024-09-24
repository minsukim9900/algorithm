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
		while (true) {
			String s = br.readLine();
			if (s.equals(".")) {
				break;
			}
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(' || s.charAt(i) == '[') {
					stack.push(s.charAt(i));
				} else if (s.charAt(i) == ']' && stack.isEmpty()) {
					System.out.println("no");
					break;
				} else if (s.charAt(i) == ')' && stack.isEmpty()) {
					System.out.println("no");
					break;
				} else if (!stack.isEmpty() && s.charAt(i) == ')' && stack.peek() == '(') {
					stack.pop();
				} else if (!stack.isEmpty() && s.charAt(i) == ']' && stack.peek() == '[') {
					stack.pop();
				} else if (s.charAt(i) == ')' && stack.peek() != s.charAt(i)) {
					System.out.println("no");
					break;
				} else if (s.charAt(i) == ']' && stack.peek() != s.charAt(i)) {
					System.out.println("no");
					break;
				} else if (s.charAt(i) == '.') {
					if (stack.isEmpty()) {
						System.out.println("yes");
					} else if (!stack.isEmpty()) {
						System.out.println("no");
					}
				}

			}
		}
	}
}