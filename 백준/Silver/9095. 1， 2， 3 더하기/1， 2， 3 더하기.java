import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		int[] info = new int[12];
		info[1] = 1;
		info[2] = 2;
		info[3] = 4;

		for (int i = 4; i <= 11; i++) {
			info[i] = info[i - 1] + info[i - 2] + info[i - 3];
		}

		for (int t = 1; t <= T; t++) {
			int num = Integer.parseInt(br.readLine());

			sb.append(info[num]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
