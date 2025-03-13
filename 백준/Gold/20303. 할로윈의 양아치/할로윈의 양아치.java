import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, K;
	private static int[] arr;
	private static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			union(findP(Integer.parseInt(st.nextToken())), findP(Integer.parseInt(st.nextToken())));
		}

		for (int i = 1; i <= N; i++) {
			p[i] = findP(p[i]);
		}

		ArrayList<int[]> items = merge();
		
		System.out.println(cal(items));

	}

	private static int cal(ArrayList<int[]> items) {
		int[] dp = new int[K];

		for (int i = 0; i < items.size(); i++) {
			
			int[] curr = items.get(i);
			
			for(int j = K -1; j>=curr[0]; j--) {
				dp[j] = Math.max(dp[j], dp[j-curr[0]] + curr[1]);
			}
			
		}
		
		return dp[K-1];

	}

	private static ArrayList<int[]> merge() {
		ArrayList<Integer>[] nums = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = new ArrayList<>();
			nums[i].add(0);
		}

		for (int i = 1; i <= N; i++) {
			nums[p[i]].add(nums[p[i]].get(nums[p[i]].size() - 1) + arr[i]);
		}

		ArrayList<int[]> info = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (nums[i].size() == 1)
				continue;

			info.add(new int[] { nums[i].size() - 1, nums[i].get(nums[i].size() - 1) });
		}

		return info;
	}

	private static int findP(int x) {
		if (x != p[x]) {
			return p[x] = findP(p[x]);
		}

		return p[x];
	}

	private static void union(int x, int y) {
		if (y > x) {
			p[y] = x;
		} else {
			p[x] = y;
		}
	}

}
