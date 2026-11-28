import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[] house, convience;

	private static List<int[]>[] adj;

	private static final int INF = 2_000_000_000;

	private static boolean binarySearch(int target) {
		int s = 0;
		int e = house.length - 1;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (house[mid] == target) {
				return true;
			} else if (house[mid] < target) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return false;
	}

	private static int dijkstra() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(a, b) -> a[1] == b[1] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

		for (int i = 0; i < convience.length; i++) {
			int c = convience[i];

			dist[c] = 0;

			pq.add(new int[] { c, 0 });
		}

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int d = curr[1];

			if (d != dist[node]) {
				continue;
			}

			if (binarySearch(node)) {
				return node;
			}

			for (int[] next : adj[node]) {
				if (dist[next[0]] > d + next[1]) {
					dist[next[0]] = d + next[1];
					pq.add(new int[] { next[0], d + next[1] });
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];

		for (int node = 1; node < N + 1; node++) {
			adj[node] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int nodeX = Integer.parseInt(st.nextToken());
			int nodeY = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[nodeX].add(new int[] { nodeY, weight });
			adj[nodeY].add(new int[] { nodeX, weight });
		}

		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		house = new int[p];
		convience = new int[q];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < p; i++) {
			house[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(house);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			convience[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(convience);

		System.out.println(dijkstra());
	}
}
