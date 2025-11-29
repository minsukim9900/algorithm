import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		char[] state = br.readLine().toCharArray();
		int answer = cal(N, state, true);
		answer = Math.min(answer, cal(N, state, false));
		System.out.println(answer);
	}

	private static int cal(int N, char[] state, boolean option) {
		boolean flag = true;

		int[] count = new int[2];

		if (option) {
			char comp = state[N - 1];
			for (int i = N - 2; i >= 0; i--) {
				if (flag && comp == state[i])
					continue;

				flag = false;
				int index = state[i] == 'R' ? 0 : 1;

				count[index]++;
			}
		} else {
			char comp = state[0];
			for (int i = 1; i < N; i++) {
				if (flag && comp == state[i]) {
					continue;
				}
				flag = false;
				int index = state[i] == 'R' ? 0 : 1;
				count[index]++;
			}
		}
		return Math.min(count[0], count[1]);
	}
}