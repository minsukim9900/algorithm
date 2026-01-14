import java.io.*;
import java.util.*;

public class Main {
	private static int M, N;
	private static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		dp = new long[101];
		for (int i = 1; i < 101; i++) {
			dp[i] = dp[i - 1] + i;
		}

		TreeSet<Node>[] nodes = new TreeSet[M];
		for(int i = 0; i < M; i++) {
			nodes[i] = new TreeSet<>((a, b) -> a.value == b.value ? Integer.compare(a.index, b.index)
					: Integer.compare(a.value, b.value));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				Node node = new Node(j, value);
				nodes[i].add(node);
			}
		}

		int[] info = new int[101];
		Map<String, Integer> map = new HashMap<>();
		int count = 0;
		for (int i = 0; i < M; i++) {
			StringBuilder tm = new StringBuilder();
			
			int preNum = 0;
			for (int j = 0; j < N; j++) {
				Node node = nodes[i].pollFirst();
				int index = node.index;
				int value = node.value;
				String str = null;
				if(preNum == value) {
					str = "same";
				} else if(preNum < value) {
					str = "large";
				} else {
					str = "small";
				}
				preNum = value;
				tm.append(index).append(str);
			}
			
			String key = tm.toString();
			if (!map.containsKey(key)) {
				map.put(key, count);
				info[count++]++;
			} else {
				info[map.get(key)]++;
			}
		}

		long result = 0L;
		for (int x : info) {
			if(x > 1) {
				result += dp[x - 1];
			}
		}
		System.out.println(result);
	}

	private static class Node {
		int index, value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [index=" + index + ", value=" + value + "]";
		}

	}
}
