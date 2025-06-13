import java.io.*;
import java.util.*;

public class Main {
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		Stack<Long> stack = new Stack<>();
		
		for (int i = 0; i < K; i++) {
			long num = Long.parseLong(br.readLine());

			if (num == 0L) {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(num);
			}
		}
		
		long sum = 0L;
		while(!stack.isEmpty()) {
			sum += stack.pop();
		}
		System.out.println(sum);
	}
}