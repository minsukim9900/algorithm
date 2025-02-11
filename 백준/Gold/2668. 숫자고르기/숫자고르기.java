import java.io.*;
import java.util.*;

public class Main {

	private static int[] p;
	private static int[] dfsVisited, visited;
	private static ArrayList<Integer> result = new ArrayList<>();
	private static int N, M;
	private static boolean isPick;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new int[N / 32 + 1];

		p = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			p[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			if ((visited[i / 32] & (1 << i % 32)) == 0) {
				dfsVisited = new int[N / 32 + 1];
				int[] tmp = p.clone();
				dfs(i, tmp);
			}
		}

		System.out.println(result.size());
		Collections.sort(result);
		for (int r : result) {
			System.out.println(r);
		}

	}

	private static int dfs(int num, int[] tmp) {

		if ((dfsVisited[tmp[num] / 32] & (1 << (tmp[num] % 32))) == 0) {
			dfsVisited[num / 32] |= (1 << (num % 32));
			tmp[num] = dfs(tmp[num], tmp);
		}

		if (num == tmp[num] && (visited[tmp[num] / 32] & (1 << (tmp[num] % 32))) == 0) {
			visited[num / 32] |= (1 << (num % 32));
			result.add(num);
		}
		return tmp[num];

	}

}
