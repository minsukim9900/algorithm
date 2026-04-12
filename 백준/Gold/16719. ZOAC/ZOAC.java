import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String input = br.readLine();
		int size = input.length();
		boolean[] visited = new boolean[size];

		recur(0, input, size, visited, sb);
		
		System.out.println(sb.toString());
	}

	private static void recur(int idx, String input, int size, boolean[] visited, StringBuilder sb) {
		while (true) {
			int minChar = 'Z' + 1;
			int minIdx = -1;

			for (int i = idx; i < size; i++) {
				char c = input.charAt(i);

				if (minChar > c && !visited[i]) {
					minChar = c;
					minIdx = i;
				}
			}

			if (minIdx == -1) {
				break;
			}

			visited[minIdx] = true;

			for (int i = 0; i < size; i++) {
				if (visited[i]) {
					sb.append(input.charAt(i));
				}
			}
			sb.append("\n");

			recur(minIdx + 1, input, size, visited, sb);
		}
	}
}