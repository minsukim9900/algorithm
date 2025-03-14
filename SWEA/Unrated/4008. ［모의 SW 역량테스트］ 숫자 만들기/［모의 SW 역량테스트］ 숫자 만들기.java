import java.io.*;
import java.util.*;

public class Solution {

	private static int N, min, max;
	private static ArrayList<Integer> sign;
	private static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());

			sign = new ArrayList<>();
			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {

				int tmp = Integer.parseInt(st.nextToken());

				for (int j = 0; j < tmp; j++) {
					sign.add(i);
				}

			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}


			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			selectSign(0, new int[N - 1], new boolean[N - 1]);

			sb.append("#" + t + " " + (max - min) + "\n");
		}
		System.out.println(sb.toString());

	}

	private static int cal(int a, int b, int s) {
		if (s == 0) {
			return a + b;
		} else if (s == 1) {
			return a - b;

		} else if (s == 2) {
			return a * b;
		} else {
			return a / b;
		}
	}

	private static void selectSign(int depth, int[] result, boolean[] visited) {

		if (depth == N - 1) {

			int sum = arr[0];
			for (int i = 0; i < N - 1; i++) {
				int num = arr[i + 1];
				sum = cal(sum, num, result[i]);
			}
			min = Math.min(sum, min);
			max = Math.max(sum, max);

		} else {
			
			int pre = -1;
			for (int i = 0; i < N - 1; i++) {

				if (visited[i] || pre == sign.get(i)) {
					continue;
				}
				
				pre = sign.get(i);
				visited[i] = true;
				result[depth] = sign.get(i);
				selectSign(depth + 1, result, visited);
				visited[i] = false;
			}

		}

	}
}
