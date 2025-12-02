import java.io.*;
import java.util.*;

public class Main {
	private static int N, end;
	private static Map<Integer, List<int[]>> map;
	private static final int INF = Integer.MAX_VALUE;
	private static int count = 0;
	private static Map<Integer, Integer> numsIdx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		numsIdx = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());

			int[] nums = new int[M];
			for (int j = 0; j < M; j++) {
				nums[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < M; j++) {
				boolean flag = false;
				List<int[]> arr = new ArrayList<>();

				if (map.containsKey(nums[j])) {
					arr = map.get(nums[j]);
					flag = true;
				}

				for (int k = 0; k < M; k++) {
					if (k == j)
						continue;
					arr.add(new int[] { i, nums[k] });
				}

				if (!flag) {
					numsIdx.put(nums[j], count++);
					map.put(nums[j], arr);
				}
			}
		}

		end = Integer.parseInt(br.readLine());
		
		if (end == 0) {
		    System.out.println(0);
		    return;
		}
		

		if (!map.containsKey(0) || !map.containsKey(end)) {
			System.out.println(-1);
			return;
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		int answer = INF;
		int size = count;
		int[][] dist = new int[N][size];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}

		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> info = map.get(0);
		
		boolean[] hosunVisited = new boolean[N];
		for (int[] x : info) {
			if (hosunVisited[x[0]]) continue;
			hosunVisited[x[0]] = true;
			dist[x[0]][numsIdx.get(0)] = 0;
			q.add(new int[] { x[0], 0 });
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int hosun = curr[0];
			int station = curr[1];
			int cost = dist[hosun][numsIdx.get(station)];

			if (station == end) {
				answer = Math.min(answer, cost);
				continue;
			}

			List<int[]> arr = map.get(station);
			if (arr == null)
				continue;

			for (int[] next : arr) {
				int nHosun = next[0];
				int nStationIdx = numsIdx.get(next[1]);

				int w = nHosun == hosun ? 0 : 1;

				if (dist[nHosun][nStationIdx] > cost + w) {
					dist[nHosun][nStationIdx] = cost + w;
					q.add(new int[] { nHosun, next[1] });
				}
			}
		}

		return answer == INF ? -1 : answer;
	}
}