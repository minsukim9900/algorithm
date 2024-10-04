import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	private static int result = 1;
	private static StringBuilder sb = new StringBuilder();

	public static void dataComptuerNum(int a, int b) {
		
		result = 1;

		for (int i = 0; i < b; i++) {
			result *= a;
			result %= 10;
		}
		if (result == 0) {
			sb.append(10).append("\n");
		} else {
			sb.append(result % 10).append("\n");
		}
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dataComptuerNum(a, b);
		}

		System.out.println(sb.toString());
	}
}