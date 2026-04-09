
import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String S = br.readLine();
		String P = br.readLine();

		int idx = 0;
		int cnt = 0;
		for (int i = 0; i < P.length(); i++) {
			if (S.indexOf(P.substring(idx, i + 1)) >= 0) {
				continue;
			}

			idx = i;
			cnt++;
		}

		System.out.println(cnt + 1);
	}
}