import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str = br.readLine();
		int N = str.length();

		List<String> result = new ArrayList<>();
		for (int i = 1; i < N - 2; i++) {
			for (int j = i + 1; j < N; j++) {
				sb = new StringBuilder();
				char[] a = str.substring(0, i).toCharArray();
				char[] b = str.substring(i, j).toCharArray();
				char[] c = str.substring(j).toCharArray();

				for (int i1 = a.length - 1; i1 >= 0; i1--) {
					sb.append(a[i1]);
				}

				for (int i1 = b.length - 1; i1 >= 0; i1--) {
					sb.append(b[i1]);
				}

				for (int i1 = c.length - 1; i1 >= 0; i1--) {
					sb.append(c[i1]);
				}

				result.add(sb.toString());
			}
		}

		result.sort((a, b) -> a.compareTo(b));
		System.out.println(result.get(0));
	}
}