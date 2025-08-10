import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static int[] arr;
	private static boolean[] isUsed, isFinished;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			isUsed = new boolean[N + 1];
			isFinished = new boolean[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			answer = N;
			for (int node = 1; node <= N; node++) {
				if (!isUsed[node]) {
					answer -= dfs(node);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int start) {
		int cycle = 0;
		int next = arr[start];
		isUsed[start] = true;

		if (!isUsed[next]) {
			cycle += dfs(next);
		} else if (!isFinished[next]) {
			cycle++;
			for (int curr = next; curr != start; curr = arr[curr]) {
				cycle++;
			}
		}

		isFinished[start] = true;
		return cycle;
	}

}
