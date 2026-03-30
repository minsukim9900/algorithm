import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		Trie root = new Trie();

		for (int i = 0; i < N; i++) {
			String nickname = br.readLine();

			Trie curr = root;
			boolean flag = false;

			for (int j = 0; j < nickname.length(); j++) {
				char alphabet = nickname.charAt(j);

				if (!curr.next.containsKey(alphabet)) {
					curr.next.put(alphabet, new Trie());

					if (!flag) {
						sb.append(alphabet);
					}
					flag = true;
				}

				if (!flag) {
					sb.append(alphabet);
				}

				curr = curr.next.get(alphabet);
			}

			curr.count++;

			if (!flag) {
				if(curr.count > 1) {
					sb.append(curr.count);
				}
			}

			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static class Trie {
		int count = 0;
		Map<Character, Trie> next;

		public Trie() {
			this.next = new HashMap<>();
		}
	}
}