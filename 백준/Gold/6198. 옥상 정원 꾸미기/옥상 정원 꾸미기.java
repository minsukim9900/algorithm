
import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		Stack<long[]> stack = new Stack<>();

		int[] nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		stack.push(new long[] { nums[N - 1], 0 });

		long answer = 0;
		for (int i = N - 2; i >= 0; i--) {
			long cnt = 0;

			while (!stack.isEmpty()) {
				long[] curr = stack.peek();

				if (curr[0] >= nums[i]) {
					break;
				} else {
					cnt += (curr[1] + 1);
					stack.pop();
				}
			}
			stack.push(new long[] { nums[i], cnt });
			answer += cnt;
		}

		System.out.println(answer);
	}
}