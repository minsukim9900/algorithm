import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] nums;
	private static List<Integer>[] arr;

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

		arr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				arr[i].add(to);
			}
		}
		int answer = solve();
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static int solve() {
		int answer = Integer.MAX_VALUE;

		int full_mask = (1 << N) - 1;
		int limit = (1 << N);

		for (int k = 1; k <= N / 2; k++) {
			int comb = (1 << k) - 1;

			while (comb < limit) {
				int maskA = comb;
				int maskB = full_mask ^ comb;

				if (isConnect(maskA) && isConnect(maskB)) {
					int a = cal(maskA);
					int b = cal(maskB);

					answer = Math.min(answer, Math.abs(a - b));
				}

				int u = comb & -comb;
				int v = comb + u;
				comb = v + (((v ^ comb) / u) >> 2);
			}
		}

		return answer;
	}

	private static boolean isConnect(int mask) {
		int count = 1;
		int lowLevelBit = mask & -mask;
		int s = 0;

		for (int i = 0; i < N; i++) {
			if ((lowLevelBit & (1 << i)) != 0) {
				s = i;
			}
		}

		boolean[] visited = new boolean[N + 1];
		visited[s] = true;

		Queue<Integer> q = new ArrayDeque<>();
		q.add(s);

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int w : arr[curr]) {
				if ((mask & (1 << w)) != 0 && !visited[w]) {
					visited[w] = true;
					count++;
					q.add(w);
				}
			}
		}
		return count == Integer.bitCount(mask);
	}

	private static int cal(int mask) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) != 0) {
				sum += nums[i];
			}
		}
		return sum;
	}
}