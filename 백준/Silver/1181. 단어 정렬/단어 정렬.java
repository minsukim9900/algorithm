import java.io.*;
import java.util.*;

public class Main {

	private static class Words implements Comparable<Words> {
		String word;
		int len;

		public Words(String word, int len) {
			super();
			this.word = word;
			this.len = len;
		}

		@Override
		public int compareTo(Words o) {

			if (this.len == o.len) {
				return this.word.compareTo(o.word);
			}

			return this.len - o.len;

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Words[] w = new Words[N];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			w[i] = new Words(tmp, tmp.length());
		}

		Arrays.sort(w);
		String pre = " ";
		for (Words i : w) {
			if(!pre.equals(i.word)) {
				pre = i.word;
				sb.append(i.word).append("\n");
			}
		}
		System.out.println(sb.toString());

	}

}
