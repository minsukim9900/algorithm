
import java.io.*;
import java.util.*;

public class Main {
	private static Map<String, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());
		map = new HashMap<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			int score = Integer.parseInt(st.nextToken());

			if (temp.length() > str.length())
				continue;

			if (map.containsKey(temp)) {
				score = Math.max(map.get(temp), score);
			}
			map.put(temp, score);
		}

		int[] dp = new int[str.length() + 1];

		for (int i = 1; i <= str.length(); i++) {
			dp[i] = Math.max(dp[i - 1] + 1, dp[i]);
			for (String s : map.keySet()) {
				if (str.startsWith(s, i - 1)) {
					dp[i + s.length() - 1] = Math.max(dp[i + s.length() - 1], dp[i - 1] + map.get(s));
				}
			}
		}
		System.out.println(dp[str.length()]);
	}
}
