import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int[][] nums = new int[3][];

	static {
		nums[0] = new int[] { 1, 2, 3, 5, 7 };
		nums[1] = new int[] { 0, 4, 6, 9 };
		nums[2] = new int[] { 8 };
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int X = Integer.parseInt(br.readLine());

			if (X == 0) {
				sb.append(1).append("\n");
				continue;
			}

			if (X == 1) {
				sb.append(0).append("\n");
				continue;
			}

			while (X != 0) {
				if (X % 2 == 0) {
					int temp = X / 2;

					for (int i = 0; i < temp; i++) {
						sb.append(8);
					}

					X -= (2 * temp);

				} else {
					X -= 1;
					sb.append(4);
				}
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}
