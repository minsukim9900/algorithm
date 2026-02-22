import java.io.*;
import java.util.*;

public class Main {
	private static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String S = br.readLine();
		String T  = br.readLine();
		
		dfs(T, S);
		System.out.println(answer);
	}

	private static void dfs(String T, String S) {

		if (T.length() == S.length()) {
			if (T.equals(S)) {
				answer = 1;
			}
			return;
		}

		if (T.endsWith("A")) {
			dfs(T.substring(0, T.length() - 1), S);
		}

		if (T.startsWith("B")) {
			dfs(new StringBuilder(T.substring(1)).reverse().toString(), S);
		}
	}
}
