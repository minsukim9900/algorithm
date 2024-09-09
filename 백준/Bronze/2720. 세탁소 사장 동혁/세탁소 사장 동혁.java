import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tscase = Integer.parseInt(br.readLine());
		int[] coin = { 25, 10, 5, 1 };
		int[][] count = new int[tscase][4];

		for (int i = 0; i < tscase; i++) {
			int pay = Integer.parseInt(br.readLine());

			for (int j = 0; j < coin.length; j++) {
				if (coin[j] > pay) {
					continue;
				} else {
					count[i][j] = pay / coin[j];
					pay %= coin[j];
				}
			}

		}

		for (int i = 0; i < count.length; i++) {
			for(int j = 0; j < count[i].length; j++) {
				sb.append(count[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}
}