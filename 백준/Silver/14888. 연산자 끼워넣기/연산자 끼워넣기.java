import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] nums;
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int[] operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}

		simulate(0, operator[0], operator[1], operator[2], operator[3], nums[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void simulate(int depth, int plus, int minus, int multiply, int devide, int sum) {
		if (depth == N - 1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
		} else {
			if (plus > 0) {
				simulate(depth + 1, plus - 1, minus, multiply, devide, sum + nums[depth + 1]);
			}

			if (minus > 0) {
				simulate(depth + 1, plus, minus - 1, multiply, devide, sum - nums[depth + 1]);
			}

			if (multiply > 0) {
				simulate(depth + 1, plus, minus, multiply - 1, devide, sum * nums[depth + 1]);
			}

			if (devide > 0) {
				simulate(depth + 1, plus, minus, multiply, devide - 1, sum / nums[depth + 1]);
			}

		}
	}
}