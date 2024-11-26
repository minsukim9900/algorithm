import java.io.*;
import java.util.*;

public class Main {
	private static int N, sum, M;
	private static boolean[] visited;
	private static int[] result;
	private static int cnt = 0;
	private static int[] nums;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		sum = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			
			nums[i] = Integer.parseInt(st.nextToken());
			
		}
		
		for(int i = 1; i<=N; i++) {
			visited = new boolean[N];
			M = i;
			result = new int[M];
			dfs(0, 0);
		}
		
		System.out.println(cnt);
	
	}

	public static void dfs(int num, int depth) {
		if(depth == M) {
			int tmp = 0;
			for(int i = 0; i<M; i++) {
				tmp += result[i];
			}
			if(sum == tmp) {
				cnt++;
			}
		}else {
			for(int i = num; i<N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				result[depth] = nums[i];
				dfs(i, depth+1);
				visited[i]=false;
			}
		}
	}

}
