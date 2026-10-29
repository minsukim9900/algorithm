import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	private static final char[] PATTERN = { 'f', 'o', 'x' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			StringBuilder sb = new StringBuilder();

			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			for (int i = 0; i < N; i++) {
				sb.append(str.charAt(i));

				int len = sb.length();

				if (len >= 3 && sb.charAt(len - 3) == 'f' && sb.charAt(len - 2) == 'o' && sb.charAt(len - 1) == 'x') {
					sb.setLength(len - 3);
				}
			}

			System.out.println(sb.length());
		}
	}
}
