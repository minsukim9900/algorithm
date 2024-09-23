import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 0;
		while (true) {
			int sum = 0;
			N = Integer.parseInt(br.readLine());
			for (int i = 2; i < N; i++) {
				if (N % i == 0) {
					sum += i;
				}

			}
			if ((sum + 1) == N) {
				int nsum = sum + 1;
				for (int i = 2; i < nsum; i++) {
					if (nsum % i == 0) {
						sb.append(" + ").append(i);
					}
				}
				System.out.println(N + " = " + 1 + sb.toString());
				sb.setLength(0);
			}
			else if(N == -1) {
				break;
			}
			else {
				System.out.println(N + " is NOT perfect.");
			}
			
		}
	}
}