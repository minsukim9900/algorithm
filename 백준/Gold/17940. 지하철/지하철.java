import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, size;
	private static ArrayList<int[]>[] adj;
	private static int[][] heap;
	private static final int INF = 987654321;
	private static int[] dist, transfer, station;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		station = new int[N];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			station[i] = Integer.parseInt(br.readLine());
		}

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int w = Integer.parseInt(st.nextToken());

				if (w > 0) {
					cnt++;
					adj[r].add(new int[] { c, w });
				}
			}
		}

		heap = new int[N * N][3];
		transfer = new int[N];
		dist = new int[N];
		Arrays.fill(transfer, INF);
		Arrays.fill(dist, INF);

		dijkstra(0, M);

		System.out.println(transfer[M] + " " + dist[M]);
	}

	private static void dijkstra(int start, int end) {
		dist[start] = 0;
		transfer[start] = 0;
		boolean[] visited = new boolean[N];
		push(new int[] { start, dist[start], transfer[start] });
		int st = station[0];
		
		while (size > 0) {
			int[] curr = poll();

			if (visited[curr[0]])
				continue;

			visited[curr[0]] = true;
			int tr = curr[2];
			for (int[] k : adj[curr[0]]) {
				int tmp = 0;
				if (station[k[0]] != station[curr[0]])
					tmp = 1;
				if (transfer[k[0]] > curr[2] + tmp
						|| (transfer[k[0]] == curr[2] + tmp && dist[k[0]] > curr[1] + k[1])) {
					transfer[k[0]] = curr[2] + tmp;
					dist[k[0]] = curr[1] + k[1];
					push(new int[] { k[0], dist[k[0]], transfer[k[0]] });
				}
			}
		}
	}

	 private static void push(int[] data) {
	        if (size >= heap.length - 1) return;
	        heap[++size] = data;
	        int ch = size;
	        int p = ch / 2;

	        while (ch > 1 && (heap[p][2] > heap[ch][2] || (heap[p][2] == heap[ch][2] && heap[p][1] > heap[ch][1]))) {
	            swap(p, ch);
	            ch = p;
	            p = ch / 2;
	        }
	    }

	    private static int[] poll() {
	        if (size == 0) return new int[] {-1, -1, -1};
	        int[] arr = heap[1];
	        heap[1] = heap[size--];
	        int p = 1;
	        int ch = p * 2;

	        if (ch + 1 <= size && (heap[ch + 1][2] < heap[ch][2] || 
	            (heap[ch + 1][2] == heap[ch][2] && heap[ch + 1][1] < heap[ch][1]))) {
	            ch++;
	        }

	        while (ch <= size && (heap[p][2] > heap[ch][2] || (heap[p][2] == heap[ch][2] && heap[p][1] > heap[ch][1]))) {
	            swap(p, ch);
	            p = ch;
	            ch = p * 2;

	            if (ch + 1 <= size && (heap[ch + 1][2] < heap[ch][2] || 
	                (heap[ch + 1][2] == heap[ch][2] && heap[ch + 1][1] < heap[ch][1]))) {
	                ch++;
	            }
	        }

	        return arr;
	    }

	    private static void swap(int p, int ch) {
	        int[] tmp = heap[p];
	        heap[p] = heap[ch];
	        heap[ch] = tmp;
	    }
}
