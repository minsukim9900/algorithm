import java.io.*;
import java.util.*;

public class Main {
	private static int N, count;
	private static long answer;
	private static boolean isEnd;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		answer = -1L;
		for (int i = 1; i < 11; i++) {
			if(isEnd) break;
			dfs(0, 10, i, 0);
		}
		System.out.println(answer);
	}

	private static void dfs(int depth, int pre, int level, long sum) {
		if (isEnd)
			return;
		if (depth == level) {
			if (count == N) {
				answer = sum;
				isEnd = true;
			}
			count++;
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (pre <= i)
				continue;
			dfs(depth + 1, i, level, sum * 10 + i);
		}
	}
}