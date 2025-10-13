import java.io.*;
import java.util.*;

public class Main {
	private static int A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int count = 0;
		while (true) {
			String tmp = String.valueOf(B);

			if (B == A) {
				break;
			}

			if (B < A) {
				count = 0;
				break;
			}
			
			if (tmp.charAt(tmp.length() - 1) - '0' != 1 && (tmp.charAt(tmp.length() - 1) - '0') % 2 == 1) {
				count = 0;
				break;
			}

			if (B % 2 == 1) {
				B -= 1;
				B /= 10;
			} else {
				B /= 2;
			}
			count++;
		}

		System.out.println(count == 0 ? -1 : count + 1);
	}
}
