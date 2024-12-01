import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int[] board;
	private static boolean[] visited;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			cnt = 0;
			N = Integer.parseInt(br.readLine());
			board = new int[N];
			visited = new boolean[N];
			dfs(0);
			System.out.println("#"+t+" "+cnt);
		}

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
			int nr = depth-i;
			int nc = Math.abs(board[depth]-board[i]);
			if(nr == nc) return false;
			
		}
		return true;
	}
	
		
		
	

}
