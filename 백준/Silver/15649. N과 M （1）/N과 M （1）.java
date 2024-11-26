import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static boolean[] visited;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result= new int[M+1];
		
		for(int i = 1; i<=N; i++) {
			visited = new boolean[N+1];
			perm(i,1);
		}
		
	}
	
	public static void perm(int num, int idx) {
		visited[num] = true;
		if(idx == M	) {
			System.out.print(num+" ");
			for(int i = 1; i<M; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
		}else {
			for(int i = 1; i<=N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				result[idx] = i;
				perm(num, idx+1);
				visited[i]=false;
			}
		}
	}
	

}
