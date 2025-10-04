import java.io.*;
import java.util.*;

public class Main {
	private static final int N = 11;
	private static int result;
	private static boolean isPoss;
	private static int[][] state = new int[N][N];
	private static int[][] order = new int[N][N];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			init();

			for (int i = 2; i <= 10; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 5; j++) {
					int idx = Integer.parseInt(st.nextToken());
					state[i][idx] = j;
					order[i][j - 1] = idx;
				}
			}
			result = simulate();
			if (result == 6) {
				sb.append("NO").append("\n");
				continue;
			}

			dfs(0, new int[5], 0);
			if (isPoss) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int[] select, int visited) {
		if (isPoss)
			return;

		if (depth == 5) {
			for (int i = 0; i < 5; i++) {
				state[1][i + 6] = select[i];
			}

			if (result > simulate()) {
				isPoss = true;
			}
		} else {
			for (int i = 6; i < N; i++) {
				if (isPoss || (visited & (1 << i)) != 0)
					continue;

				visited |= (1 << i);
				select[depth] = i;
				dfs(depth + 1, select, visited);
				visited ^= (1 << i);
			}
		}
	}

	private static void init() {
		for (int i = 1; i < N; i++) {
			Arrays.fill(state[i], 0);
			Arrays.fill(order[i], 0);
		}
		result = 0;

		state[1][6] = 1;
		state[1][7] = 2;
		state[1][8] = 3;
		state[1][9] = 4;
		state[1][10] = 5;

		isPoss = false;
	}

	private static int simulate() {
		int count = 0;
		int[] matchNumber = new int[N];
		int[] index = new int[N];

		while (count != 5) {
			for (int i = 6; i <= 10; i++) {
				if (matchNumber[i] > 0)
					continue;

				int num = order[i][index[i]++];

				if (matchNumber[num] > 0) {
					if (state[num][i] < state[num][matchNumber[num]]) {
						matchNumber[matchNumber[num]] = 0;
						matchNumber[num] = i;
						matchNumber[i] = num;
					}
				} else {
					count++;
					matchNumber[num] = i;
					matchNumber[i] = num;
				}
			}
		}
		return matchNumber[1];
	}
}
