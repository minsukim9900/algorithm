import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static char[][] arr;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new char[N][];
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			simulate(1, String.valueOf(arr[i]), visited);
		}
		System.out.println(answer);
	}

	private static void simulate(int depth, String str, boolean[] visited) {
		if(str.length() > answer) return;
		
		if (depth == N) {
			answer = Math.min(answer, str.length());
		} else {
			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;
				int idx = check(str, arr[i]);

				idx = idx == -1 ? str.length() : idx;
				String tmp = str;
				for (int j = str.length() - idx; j < arr[i].length; j++) {
					str += arr[i][j];
				}
				visited[i] = true;
				simulate(depth + 1, str, visited);
				str = tmp;
				visited[i] = false;
			}
		}
	}

	private static int check(String str, char[] comp) {
		int length = str.length();

		int right = comp.length - 1;
		for (int i = Math.max(0, length - comp.length); i < str.length(); i++) {
			boolean possible = true;
			int compLeft = 0;
			int strLeft = i;

			while (compLeft <= right && strLeft < length) {
				if (comp[compLeft] == str.charAt(strLeft)) {
					compLeft++;
					strLeft++;
				} else {
					possible = false;
					right--;
					break;
				}
			}

			if (possible)
				return i;
		}
		return -1;
	}
}