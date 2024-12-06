import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] nums;
	private static boolean[] visited;
	private static int[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		visited = new boolean[N];
		result = new int[M];

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			
			nums[i] = Integer.parseInt(st.nextToken());
			
		}

		Arrays.sort(nums);

		dfs(0, 0);

	}

	private static void dfs(int num, int depth) {

		if (depth == M) {
			
			for(int i = 0; i<M; i++) {
				
				System.out.print(result[i]+" ");
				
			}
			
			System.out.println();
			
		} else {

			int tmp = 0;

			for (int i = num; i < N; i++) {
				
				if (tmp == nums[i])
					continue;
				tmp = nums[i];
				result[depth] = nums[i];
				dfs(i+1, depth+1);
				
			}

		}

	}
}
