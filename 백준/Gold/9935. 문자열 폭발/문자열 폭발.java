import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		String bomb = br.readLine();

		int strLen = str.length();
		int bombLen = bomb.length();

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < strLen; i++) {
			int count = 0;
			stack.push(str.charAt(i));

			if (stack.size() >= bombLen) {
				for (int j = 0; j < bombLen; j++) {

					if (stack.get(stack.size() - bombLen + j) == bomb.charAt(j)) {
						count++;
					}

					if (count == bombLen) {
						for (int k = 0; k < bombLen; k++) {
							stack.pop();
						}
					}
				}
			}
		}
		String result = "FRULA";

		if (!stack.isEmpty()) {
			for (char ch : stack) {
				sb.append(ch);
			}

			result = sb.toString();
		}

		System.out.println(result);
	}
}