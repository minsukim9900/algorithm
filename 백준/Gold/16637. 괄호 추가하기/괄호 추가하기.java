import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, answer;
	private static int[] number;
	private static char[] sign;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = N >> 1;
		number = new int[M + 1];
		sign = new char[M];

		char[] expression = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				number[i >> 1] = expression[i] - '0';
			} else {
				sign[i >> 1] = expression[i];
			}
		}

		answer = Integer.MIN_VALUE;
		dfs(0, number[0]);
		System.out.println(answer);
	}

	private static void dfs(int depth, int sum) {
		if (depth == M) {
			answer = Math.max(answer, sum);
			return;
		}

		dfs(depth + 1, cal(sum, number[depth + 1], sign[depth]));

		if (depth + 1 < M) {
			int temp = cal(number[depth + 1], number[depth + 2], sign[depth + 1]);
			dfs(depth + 2, cal(sum, temp, sign[depth]));
		}
	}

	private static int cal(int num1, int num2, char e) {
		switch (e) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		default:
			return num1 * num2;
		}
	}
}
