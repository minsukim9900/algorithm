import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int numLen = N - K;

		char[] nums = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			char c = nums[i];

			while (K > 0 && !stack.isEmpty() && stack.peek() < c) {
				stack.pop();
				K--;
			}
			stack.add(c);
		}

		while (K-- > 0) {
			stack.pop();
		}
		
		for(char c : stack) {
			sb.append(c);
		}
		System.out.println(sb.toString());
	}
}