import java.io.*;
import java.util.*;;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String str = null;
		Stack<Character> stack;
		while (true) {
			str = br.readLine();
			stack = new Stack<>();

			if (str.equals(".")) {
				break;
			} else {
				boolean isPoss = true;

				for (int i = 0; i < str.length(); i++) {
					char c = str.charAt(i);

					if (c == '[' || c == '(') {
						stack.add(c);
					} else if (c == ']') {
						if (!stack.isEmpty() && stack.peek() == '[') {
							stack.pop();
						} else {
							isPoss = false;
							break;
						}
					} else if (c == ')') {
						if (!stack.isEmpty() && stack.peek() == '(') {
							stack.pop();
						} else {
							isPoss = false;
							break;
						}
					}
				}

				if (isPoss && stack.isEmpty()) {
					sb.append("yes").append("\n");
				} else {
					sb.append("no").append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}
