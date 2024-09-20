import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		int[][] apart = new int[15][15];
		for (int r = 0; r < 15; r++) {
			apart[0][r] = r + 1;
		}
		for (int c = 0; c < 15; c++) {
			apart[c][0] = 1;
		}

		for (int i = 1; i < 15; i++) {
			for (int j = 1; j < 15; j++) {
				apart[i][j] = apart[i - 1][j] + apart[i][j - 1];
			}
		}
		
		for(int i = 0; i<tc; i++) {
			int ar = Integer.parseInt(br.readLine());
			int ac = Integer.parseInt(br.readLine());
			sb.append(apart[ar][ac-1]).append("\n");
		}
		System.out.println(sb.toString());

	}
}