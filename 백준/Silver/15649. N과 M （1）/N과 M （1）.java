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
		visited = new boolean[N+1];
		
		perm(1);
		System.out.println(sb.toString());
		
	}
	
	public static void perm(int idx) {
		if(idx == M+1	) {
			for(int i = 1; i<=M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
		}else {
			for(int i = 1; i<=N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				result[idx] = i;
				perm(idx+1);
				visited[i]=false;
			}
		}
	}
	

}
