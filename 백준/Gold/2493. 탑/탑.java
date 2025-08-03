import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int[] answer = simulateLaser(nums);

		for (int w : answer) {
			sb.append(w).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int[] simulateLaser(int[] nums) {
		int[] answer = new int[N];

		Deque<int[]> stack = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && stack.peek()[0] < nums[i]) {
				stack.pop();
			}

			if (!stack.isEmpty()) {
				answer[i] = stack.peek()[1];
			}
			stack.push(new int[] { nums[i], i + 1 });
		}

		return answer;
	}
}