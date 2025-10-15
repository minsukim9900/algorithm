import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer>[] pq = new PriorityQueue[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq[i] = new PriorityQueue<>((a, b) -> a - b);
			for (int j = 0; j < M; j++) {
				pq[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		PriorityQueue<int[]> cal = new PriorityQueue<>((a, b) -> a[0] - b[0]);

		int max = -1;
		int min = Integer.MAX_VALUE;
		int maxIdx = 0;
		int minIdx = 0;

		for (int i = 0; i < N; i++) {
			int num = pq[i].poll();

			if (max < num) {
				max = num;
				maxIdx = i;
			}

			if (min > num) {
				min = num;
				minIdx = i;
			}
			cal.add(new int[] { num, i });
		}
		cal.poll();
		int ans = max - min;

		while (true) {
			if (pq[minIdx].isEmpty())
				break;

			int num = pq[minIdx].poll();
			max = Math.max(max, num);
			cal.add(new int[] { num, minIdx });
			int[] minInfo = cal.poll();
			min = minInfo[0];
			minIdx = minInfo[1];
			ans = Math.min(ans, max - min);
		}
		System.out.println(ans);
	}
}
