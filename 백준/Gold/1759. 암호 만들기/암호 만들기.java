import java.io.*;
import java.util.*;

public class Main {

	private static String[] str;
	private static int L, C;
	private static String[] result;
	private static StringBuilder sb = new StringBuilder();
	private static int visited;
	private static int cnt = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		str = new String[C];
		result = new String[L];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			str[i] = st.nextToken();
		}

		Arrays.sort(str);

		Calm(0, 0);

	}

	private static void Calm(int num, int depth) {

		if (depth == L) {

			if (visited > 0 && cnt >= 2) {
				for (int i = 0; i < L; i++) {
					System.out.print(result[i]);
				}
				System.out.println();
			}

		} else {
			boolean isCon = false;
			for (int i = num; i < C; i++) {
				result[depth] = str[i];

				int tmp = -1;
				switch (result[depth]) {
				case "a":
					tmp = 0;
					break;
				case "e":
					tmp = 1;
					break;
				case "i":
					tmp = 2;
					break;
				case "o":
					tmp = 3;
					break;
				case "u":
					tmp = 4;
					break;
				default:
					isCon = true;
					cnt++;
				}

				if (tmp > -1) {
					visited |= (1 << tmp);
				}

				Calm(i + 1, depth + 1);
				
				if(isCon && cnt > 0) {
					cnt--;
					isCon = false;
				}
				if (tmp > -1) {
					visited ^= (1 << tmp);
				}
			}

		}

	}

}
