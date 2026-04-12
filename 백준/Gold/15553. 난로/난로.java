import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int pre = Integer.parseInt(br.readLine());
		int minTime = pre;
		int maxTime = pre + 1;

		List<int[]> edges = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			int currTime = Integer.parseInt(br.readLine());

			edges.add(new int[] { currTime, currTime - pre, 0 });
			pre = currTime;
		}

		edges.sort((a, b) -> Integer.compare(b[1], a[1]));

		for (int i = 0; i < K - 1; i++) {
			int[] curr = edges.get(i);
			curr[2] = 1;
		}

		edges.sort((a, b) -> Integer.compare(a[0], b[0]));

		long answer = 0;

		for (int[] edge : edges) {
			if (edge[2] == 0) {
				minTime = Math.min(edge[0], minTime);
				maxTime = Math.max(edge[0] + 1, maxTime);
			} else {
				answer += (maxTime - minTime);
				minTime = edge[0];
				maxTime = edge[0] + 1;
			}
		}

		answer += (maxTime - minTime);
		System.out.println(answer);
	}
}