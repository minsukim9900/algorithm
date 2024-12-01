import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] board;
	private static boolean[] visited;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N];
		visited = new boolean[N];
		dfs(0);
		System.out.println(cnt);

	}
	
	public static void dfs(int depth) {
		if(depth == N) {
			cnt++;
		}
		else {
			
			for(int i = 0; i<N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				board[depth] = i;
				if(check(depth)) {
				dfs(depth+1);
				}
				visited[i] = false;
			}
			
		}
		
	}

	
	private static boolean check(int depth) {
		
		for(int i = 0; i<depth; i++) {
			int nr = Math.abs(board[depth]-board[i]);
			int nc = Math.abs(depth-i);
			if(nr == nc) return false;
		}
		return true;
	}
	
		
		
	

}
