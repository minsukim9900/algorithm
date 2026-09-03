import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	private static Map<Character, Integer> map = new HashMap<>();
	private static char[] info = new char[8];

	static {
		map.put('(', 0);
		map.put(')', 1);
		map.put('[', 2);
		map.put(']', 3);
		map.put('{', 4);
		map.put('}', 5);
		map.put('<', 6);
		map.put('>', 7);
		info[0] = '(';
		info[2] = '[';
		info[4] = '{';
		info[6] = '<';
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		final int T = 10;

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			int length = Integer.parseInt(br.readLine());

			char[] chr = br.readLine().toCharArray();

			Stack<Character> stack = new Stack<>();
			int result = 1;

			for (int i = 0; i < length; i++) {
				char c = chr[i];
				int idx = map.get(c);

				if ((idx & 1) == 1) {
					if (stack.peek() != info[idx ^ 1]) {
						break;
					}

					stack.pop();
				} else {
					stack.push(c);
				}
			}

			if (!stack.isEmpty()) {
				result = 0;
			}

			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
	}
}
