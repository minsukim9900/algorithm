import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] nums;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		result = new int[M];

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			
			nums[i] = Integer.parseInt(st.nextToken());
			
		}

		Arrays.sort(nums);

		dfs(0);
		System.out.println(sb.toString());

	}

	private static void dfs(int depth) {

		if (depth == M) {
			
			for(int i = 0; i<M; i++) {
				
				sb.append(result[i]).append(" ");
				
			}
			sb.append("\n");
			
		} else {

			int tmp = 0;

			for (int i = 0; i < N; i++) {
				
				if (tmp == nums[i])
					continue;
				tmp = nums[i];
				result[depth] = nums[i];
				dfs(depth+1);
				
			}

		}

	}
}
