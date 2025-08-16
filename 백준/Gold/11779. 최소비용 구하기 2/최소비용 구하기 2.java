import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] dist, p;
	private static final int INF = 100_000_001;
	private static List<int[]>[] arr;
	private static Stack<Integer> answer = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		p = new int[N + 1];
		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
			p[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[from].add(new int[] { to, w });
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		sb.append(findTrack(start, end)).append("\n");
		sb.append(countCity(end, start)).append("\n");
		while (!answer.isEmpty()) {
			sb.append(answer.pop()).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int countCity(int city, int start) {
		answer.add(city);
		if (city == start) {
			return 1;
		}
		int cnt = 1;
		cnt += countCity(p[city], start);
		return cnt;
	}

	private static int findTrack(int start, int end) {
		dist[start] = 0;
		p[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			if(curr[1] > dist[curr[0]]) continue;
			
			for (int[] next : arr[curr[0]]) {
				if (dist[next[0]] > dist[curr[0]] + next[1]) {
					p[next[0]] = curr[0];
					dist[next[0]] = dist[curr[0]] + next[1];
					pq.add(new int[] { next[0], dist[next[0]] });
				}
			}
		}
		return dist[end];
	}
}