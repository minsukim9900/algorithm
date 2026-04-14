import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Queue<Integer>[] q = new Queue['z' - 'a' + 1];
		for (int i = 0; i < 'z' - 'a' + 1; i++) {
			q[i] = new ArrayDeque<>();
		}

		String S = br.readLine();

		for (int i = 0; i < S.length(); i++) {
			char alphabet = S.charAt(i);

			q[alphabet - 'a'].add(i);
		}

		char[] T = br.readLine().toCharArray();

		int count = 0;

		out: while (true) {
			int pre = -1;

			for (int i = 0; i < T.length; i++) {
				boolean flag = false;

				if (q[T[i] - 'a'].isEmpty()) {
					break out;
				}

				if (pre < q[T[i] - 'a'].peek()) {
					flag = true;
				}

				while (!q[T[i] - 'a'].isEmpty() && pre > q[T[i] - 'a'].peek()) {
					q[T[i] - 'a'].poll();
					flag = true;
				}

				if (!flag) {
					break out;
				}
				
				if (q[T[i] - 'a'].isEmpty())
					break out;

				pre = q[T[i] - 'a'].peek();
			}

			for (int i = 0; i < T.length; i++) {

				q[T[i] - 'a'].poll();

			}
			count++;
		}

		System.out.println(count);
	}
}