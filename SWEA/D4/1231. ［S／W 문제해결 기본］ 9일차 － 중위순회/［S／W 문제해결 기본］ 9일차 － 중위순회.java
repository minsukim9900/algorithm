import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static int[][] infos;
	private static char[] alphabet;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			infos = new int[N + 1][2];
			alphabet = new char[N + 1];

			for (int i = 1; i <= N; i++) {
				String[] temp = br.readLine().split(" ");
				alphabet[i] = temp[1].charAt(0);

				for (int j = 2; j < temp.length; j++) {
					int num = Integer.parseInt(temp[j]);
					infos[i][j - 2] = num;
				}
			}
			sb.append("#").append(test_case).append(" ");
			dfs(1);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int node) {
		if (infos[node][0] != 0) {
			dfs(infos[node][0]);
		}

		sb.append(alphabet[node]);

		if (infos[node][1] != 0) {
			dfs(infos[node][1]);
		}
	}
}