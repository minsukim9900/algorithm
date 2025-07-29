import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static List<Integer>[] arr;
	private static boolean[] visited;
	private static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];

		arr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<>();
		}

		int root = 0;

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < N; idx++) {
			int num = Integer.parseInt(st.nextToken());

			if (num == -1) {
				root = idx;
			} else {
				arr[num].add(idx);
			}
		}

		visited[Integer.parseInt(br.readLine())] = true;
		dfs(root);
		System.out.println(answer);
	}

	private static int dfs(int node) {
		int cnt = 1;
		if (visited[node]) {
			return 0;
		} else {
			visited[node] = true;

			for (int child : arr[node]) {
				if (visited[child])
					continue;
				cnt += dfs(child);
			}

			if (cnt == 1) {
				answer++;
			}
		}
		return cnt;
	}
}
