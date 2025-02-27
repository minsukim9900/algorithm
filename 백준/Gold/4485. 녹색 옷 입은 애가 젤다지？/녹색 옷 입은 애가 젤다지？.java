import java.util.*;
import java.io.*;

public class Main {

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	static int N;

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[][] heap;
	private static int size;

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {

			N = read();
			if (N == 0)
				break;

			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			heap = new int[N * N + 1][3];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = read();
					dist[r][c] = Integer.MAX_VALUE;
				}
			}

			dist[0][0] = map[0][0];

			push(new int[] { 0, 0, dist[0][0] });
			while (size > 0) {
				int[] curr = poll();

				for (int i = 0; i < 4; i++) {
					int nr = curr[0] + delta[i][0];
					int nc = curr[1] + delta[i][1];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {

						visited[nr][nc] = true;
						dist[nr][nc] = Math.min(dist[nr][nc], curr[2] + map[nr][nc]);
						push(new int[] { nr, nc, dist[nr][nc] });

					}
				}
			}

			sb.append("Problem ").append(t++).append(": ").append(dist[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void push(int[] data) {

		heap[++size] = data;

		int p = size / 2;
		int c = size;

		while (c != 1 && heap[p][2] > heap[c][2]) {

			swap(p, c);

			c = p;
			p = c / 2;

		}
	}

	private static int[] poll() {
		int[] arr = heap[1];

		heap[1] = heap[size--];

		int p = 1;
		int c = p * 2;

		if (c + 1 <= size && heap[c + 1][2] < heap[c][2]) {
			c++;
		}

		while (c <= size && heap[p][2] > heap[c][2]) {

			swap(p, c);

			p = c;
			c = p * 2;

			if (c + 1 <= size && heap[c + 1][2] < heap[c][2]) {
				c++;
			}

		}

		return arr;
	}

	private static void swap(int p, int c) {
		int[] tmp = heap[p];
		heap[p] = heap[c];
		heap[c] = tmp;
	}
}