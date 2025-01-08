import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, M;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		result = new int[M];
		
		dfs(0);
		System.out.println(sb.toString());
		
	}
	
	private static void dfs(int depth) {
		if(depth == M) {
			
			for(int i = 0; i<M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			
		}else {
			
			for(int i = 1; i<= N; i++) {
				result[depth] = i;
				dfs(depth+1);
			}
			
		}
	}

}
