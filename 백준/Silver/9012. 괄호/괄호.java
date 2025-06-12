import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			String result = "NO";
			
			if(simulate(str)) {
				result = "YES";
			}
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static boolean simulate(String str) {
		boolean isPossible = false;
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == '(') {
				stack.add(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					stack.pop();
				}
			}
		}
		
		if(!stack.isEmpty()) {
			return false;
		}

		return true;
	}
}