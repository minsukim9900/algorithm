import java.io.*;
import java.util.*;

public class Main {
	private static int answer = 0;
	private static final int END = 32;
	private static final int ROUND = 10;
	private static int[] nums = new int[ROUND];
	private static int[] score = new int[END + 1];
	private static int[] next = new int[END + 1];
	private static int[] blue = new int[END + 1];

	static {
		for (int i = 1; i <= 20; i++) {
			score[i] = i * 2;
			next[i - 1] = i;
		}
		next[20] = END;
		score[END] = 0;

		score[21] = 13;
		score[22] = 16;
		score[23] = 19;
		score[24] = 25;
		score[25] = 30;
		score[26] = 35;
		next[21] = 22;
		next[22] = 23;
		next[23] = 24;
		next[24] = 25;
		next[25] = 26;
		next[26] = 20;
		blue[5] = 21;

		score[27] = 22;
		score[28] = 24;
		next[27] = 28;
		next[28] = 24;
		blue[10] = 27;

		score[29] = 28;
		score[30] = 27;
		score[31] = 26;
		next[29] = 30;
		next[30] = 31;
		next[31] = 24;
		blue[15] = 29;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < ROUND; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, new int[4], new boolean[33]);
		System.out.println(answer);
	}

	private static void dfs(int depth, int sum, int[] pos, boolean[] visited) {
		if (depth == ROUND) {
			answer = Math.max(answer, sum);
			return;
		}

		int dist = nums[depth];
		for (int i = 0; i < 4; i++) {
			int curr = pos[i];
			if (curr == END)
				continue;

			int next = move(curr, dist);

			if (next != END && visited[next])
				continue;

			pos[i] = next;
			visited[curr] = !visited[curr];
			visited[next] = !visited[next];
			dfs(depth + 1, sum + score[next], pos, visited);
			visited[curr] = !visited[curr];
			visited[next] = !visited[next];
			pos[i] = curr;
		}
	}

	private static int move(int pos, int dist) {
		if (pos == END)
			return END;

		if (blue[pos] > 0) {
			pos = blue[pos];
			dist--;
		} else {
			pos = next[pos];
			dist--;
		}

		while (dist-- > 0 && pos != END) {
			pos = next[pos];
		}
		return pos;
	}

}