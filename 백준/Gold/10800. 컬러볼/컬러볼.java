import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static List<int[]> balls;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		balls = new ArrayList<>();

		for (int index = 0; index < N; index++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			balls.add(new int[] { color, size, index });
		}

		balls.sort((a, b) -> Integer.compare(a[1], b[1]));

		long[] answer = new long[N];
		long[] colorSum = new long[N + 1];
		long totalSum = 0L;

		int groupStart = 0;
		while (groupStart < N) {
			int groupEnd = groupStart;
			int size = balls.get(groupEnd)[1];

			while (groupEnd < N && balls.get(groupEnd)[1] == size) {
				groupEnd++;
			}

			for (int k = groupStart; k < groupEnd; k++) {
				int[] b = balls.get(k);
				answer[b[2]] = totalSum - colorSum[b[0]];
			}

			for (int k = groupStart; k < groupEnd; k++) {
				int[] b = balls.get(k);

				totalSum += b[1];
				colorSum[b[0]] += b[1];
			}

			groupStart = groupEnd;
		}

		for (long ans : answer) {
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
}