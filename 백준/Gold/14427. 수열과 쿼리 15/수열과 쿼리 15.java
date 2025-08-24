import java.io.*;
import java.util.*;

public class Main {
	private static class Info {
		int idx;
		int value;
		int version;

		public Info(int idx, int value, int version) {
			this.idx = idx;
			this.value = value;
			this.version = version;
		}
	}

	private static int[] version;
	private static PriorityQueue<Info> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>((a, b) -> a.value == b.value ? a.idx - b.idx : a.value - b.value);

		version = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Info info = new Info(i + 1, Integer.parseInt(st.nextToken()), 0);
			pq.add(info);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());

			if (num == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				version[idx]++;
				pq.add(new Info(idx, v, version[idx]));
			} else {
				int idx = -1;
				Info pick = null;
				while (!pq.isEmpty()) {
					Info curr = pq.poll();

					if (curr.version == version[curr.idx]) {
						idx = curr.idx;
						pick = curr;
						break;
					}
				}

				if (idx != -1) {
					sb.append(idx).append("\n");
					pq.add(pick);
				}
			}
		}
		System.out.println(sb.toString());
	}
}