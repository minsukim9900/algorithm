import java.io.*;
import java.util.*;

public class Main {
	private static boolean isEnd = false;
	private static String result = "CALL FRIEND";
	private static int N, L;
	private static char[][] word;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		word = new char[N][];
		for (int i = 0; i < N; i++) {
			word[i] = br.readLine().toCharArray();
		}
		dfs(0, new char[L]);
		System.out.println(result);
	}

	private static void dfs(int depth, char[] comp) {
		if(isEnd) return;
		
		if (depth == L) {
			isEnd = true;
			result = String.valueOf(comp);
			return;
		}

		for (int i = 0; i < 26; i++) {
			char x = (char) ('A' + i);
			comp[depth] = x;
			if(check(depth, comp) ) {
				dfs(depth + 1, comp);
			}
		}
	}

	private static boolean check(int depth, char[] comp) {
		for (char[] w : word) {
			int count = 0;
			for (int i = 0; i <= depth; i++) {
				if(w[i] != comp[i]) count++;
				
				if(count > 1) {
					return false;
				}
			}
		}
		return true;
	}
}
