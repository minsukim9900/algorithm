import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		for(int w : cal()) {
			sb.append(w).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int[] cal() {
		int[] answer = new int[N];
		Stack<Integer> stack = new Stack<>();

		for (int i = N - 1; i >= 0; i--) {
			if (!stack.isEmpty()) {
				while (!stack.isEmpty() && stack.peek() <= nums[i]) {
					stack.pop();
				}

				if (stack.isEmpty()) {
					answer[i] = -1;
				} else {
					answer[i] = stack.peek();
				}
			} else {
				answer[i] = -1;
			}
			stack.push(nums[i]);
		}

		return answer;
	}
}