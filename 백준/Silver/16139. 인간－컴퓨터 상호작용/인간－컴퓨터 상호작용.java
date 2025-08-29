import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		int[][] count = new int[('z' - 'a') + 1][str.length() + 1];

		for (int i = 1; i < str.length() + 1; i++) {
			char c = str.charAt(i - 1);
			for (int j = 'a'; j <= 'z'; j++) {
				int num = 0;
				if (c == j) {
					num = 1;
				}
				count[j - 'a'][i] = count[j - 'a'][i - 1] + num;
			}
		}

		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);

			int s = Integer.parseInt(st.nextToken()) + 1;
			int e = Integer.parseInt(st.nextToken()) + 1;

			sb.append(count[c - 'a'][e] - count[c - 'a'][s - 1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}