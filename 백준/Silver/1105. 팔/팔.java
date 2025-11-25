import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		char[] L = st.nextToken().toCharArray();
		char[] R = st.nextToken().toCharArray();

		int answer = 0;

		if (L.length == R.length) {
			for (int i = 0; i < L.length; i++) {
				if (L[i] == R[i]) {
					if (L[i] == '8') {
						answer++;
					}
				} else {
					break;
				}
			}
		}
		System.out.println(answer);
	}
}