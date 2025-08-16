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

			arr[from].add(new int[] { from, to, w });
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		sb.append(findTrack(start, end)).append("\n");
		sb.append(countCity(end)).append("\n");
		
		while(!answer.isEmpty()) {
			sb.append(answer.pop()).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int countCity(int city) {
		answer.add(city);
		if (p[city] == 0) {
			return 1;
		}

		int cnt = 1;

		cnt += countCity(p[city]);

		return cnt;
	}

	private static int findTrack(int start, int end) {
		dist[start] = 0;
		p[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		pq.addAll(arr[start]);

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0];
			int to = curr[1];
			int w = curr[2];

			if (dist[to] > dist[from] + w) {
				dist[to] = dist[from] + w;
				p[to] = from;
				pq.addAll(arr[to]);
			}
		}

		return dist[end];
	}

	private static void union(int x, int y) {
		p[y] = x;
	}
}