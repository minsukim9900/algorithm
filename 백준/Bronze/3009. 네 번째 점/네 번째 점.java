import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] xcount = new int[1001];
		int[] ycount = new int[1001];

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			xcount[Integer.parseInt(st.nextToken())]++;
			ycount[Integer.parseInt(st.nextToken())]++;
		}

		for (int i = 0; i < xcount.length; i++) {
			if (xcount[i] == 1) {
				sb.append(i).append(" ");
			}
		}

		for (int i = 0; i < xcount.length; i++) {
			if (ycount[i] == 1) {
				sb.append(i);
			}
		}
		System.out.println(sb.toString());
	}
}