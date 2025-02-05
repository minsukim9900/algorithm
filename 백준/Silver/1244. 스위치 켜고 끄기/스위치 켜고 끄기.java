import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int[] lights;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		lights = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i ++) {
			lights[i] = Integer.parseInt(st.nextToken());
		}

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			int light = Integer.parseInt(st.nextToken());
			order(student, light);
		}

		for (int i = 1; i <= N; i++) {
			sb.append(lights[i]).append(" ");
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}

		System.out.println(sb.toString());

	}

	private static void order(int student, int light) {

		if (student == 1) {

			for (int i = light; i <= N; i += light) {
				change(i);
			}

		} else {

			change(light);
			int left = light - 1;
			int right = light + 1;

			while (left >= 1 && right <= N) {

				if ((lights[left] ^ lights[right]) != 0) {
					break;
				}
				change(left);
				change(right);
				left--;
				right++;
			}

		}

	}

	private static void change(int idx) {
		lights[idx] ^= 1;
	}

}
