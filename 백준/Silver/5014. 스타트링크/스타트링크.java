import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int F, S, G, U, D;

	private static boolean isRange(int x) {
		return x >= 1 && x <= F;
	}

	private static int simulate() {
		int[] delta = { U, -D };

		boolean[] visited = new boolean[F + 1];
		visited[S] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { S, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int floor = curr[0];
			int step = curr[1];

			if (floor == G) {
				return step;
			}

			for (int i = 0; i < 2; i++) {
				int nextFloor = floor + delta[i];

				if (isRange(nextFloor) && !visited[nextFloor]) {
					visited[nextFloor] = true;
					q.add(new int[] { nextFloor, step + 1 });
				}
			}
		}

		return -1;
	}

	private static String output(int step) {
		if (step != -1) {
			return String.valueOf(step);
		}

		return "use the stairs";
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		System.out.println(output(simulate()));
	}
}
