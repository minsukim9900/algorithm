import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());

			if (n == 1) {
				sb.append("1\n1\n");
				continue;
			}

			int m = (int) (Math.log(n) / Math.log(2));
			int s = 1 << (m - 1);
			int e = Math.min(3 * s - 1, n);
			
			sb.append(e - s + 1).append("\n");
			for (int i = s; i <= e; i++) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}