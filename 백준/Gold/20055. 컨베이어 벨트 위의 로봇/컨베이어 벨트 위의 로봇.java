import java.io.*;
import java.util.*;

public class Main {

	private static int N, K, L, cnt;
	private static int[] info;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		L = N << 1;

		info = new int[L];
		visited = new boolean[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < info.length; i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}

		simulate();

	}

	private static void simulate() {

		Queue<Integer> robots = new ArrayDeque<>();

		int idx = 0;
		int t = 0;

		while (true) {

			idx = ((idx - 1) + L) % L;

			if (!robots.isEmpty()) {

				if (robots.peek() == ((idx + N - 1)) % L) {
					int tmp = robots.poll();
					visited[tmp] = false;
				}

				robots = move(robots, idx);
			}

			if (info[idx] > 0) {
				robots.offer(idx);
				visited[idx] = true;
				info[idx]--;

				if (info[idx] == 0) {
					cnt++;
				}
			}

			t++;

			if (cnt >= K) {
				break;
			}
		}

		System.out.println(t);

	}

	private static Queue<Integer> move(Queue<Integer> robots, int idx) {
		int size = robots.size();

		for (int i = 0; i < size; i++) {
			int curr = robots.poll();

			int nIdx = (curr + 1) % L;

			if (info[nIdx] > 0 && !visited[nIdx]) {

				visited[nIdx] = true;
				info[nIdx]--;
				visited[curr] = false;
				
				if(info[nIdx] == 0) {
					cnt++;
				}

				if ((nIdx == ((idx + N - 1)) % L)) {
					continue;
				}

				robots.offer(nIdx);
			} else {
				robots.offer(curr);
			}

		}

		return robots;
	}
}
