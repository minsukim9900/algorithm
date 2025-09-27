import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static int[] pair = { 5, 3, 4, 1, 2, 0 };
	private static int[][] nums;
	private static Map<Integer, Integer>[] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N][6];

		map = new HashMap[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			map[i] = new HashMap<>();
			for (int j = 0; j < 6; j++) {
				int num = Integer.parseInt(st.nextToken());
				nums[i][j] = num;
				map[i].put(num, j);
			}
		}

		visited = new boolean[N][7];
		// 1번 주사위 윗변 정하기
		for (int i = 1; i <= 6; i++) {
			gameStart(i, 0, 0);
		}
		System.out.println(answer);
	}

	private static void gameStart(int num, int depth, int sum) {
		if (depth == N) {
			answer = Math.max(answer, sum);
			return;
		}
		visited[depth][num] = true;
		visited[depth][nums[depth][pair[map[depth].get(num)]]] = true;

		int max = 0;
		for (int i = 1; i <= 6; i++) {
			if (visited[depth][i])
				continue;
			max = Math.max(max, i);
		}
		if (depth == 0) {
			gameStart(num, depth + 1, sum + max);
		} else {
			gameStart(nums[depth][pair[map[depth].get(num)]], depth + 1, sum + max);
		}
		visited[depth][num] = false;
		visited[depth][nums[depth][pair[map[depth].get(num)]]] = false;
	}
}