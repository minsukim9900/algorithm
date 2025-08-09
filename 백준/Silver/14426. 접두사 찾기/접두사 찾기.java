import java.io.*;
import java.util.*;

public class Main {

	private static class Trie {
		private char alphabet;
		private boolean isWordEnd;
		private Map<Character, Trie> children = new HashMap<>();

		public Trie(char alphabet) {
			this.alphabet = alphabet;
		}

		public Trie() {
		}
	}

	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Trie head = new Trie();

		for (int i = 0; i < N; i++) {
			Trie indexTrie = head;

			String word = br.readLine();
			int len = word.length();

			for (int j = 0; j < len; j++) {

				char alphabet = word.charAt(j);

				if (!indexTrie.children.containsKey(alphabet)) {
					Trie newTrie = new Trie(alphabet);
					indexTrie.children.put(alphabet, newTrie);
				}
				indexTrie = indexTrie.children.get(alphabet);
				indexTrie.isWordEnd = true;
			}
		}

		int answer = 0;

		for (int i = 0; i < M; i++) {
			if (isPrefix(br.readLine(), head)) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static boolean isPrefix(String word, Trie head) {
		int len = word.length();

		Trie indexTrie = head;
		for (int i = 0; i < len; i++) {
			char alphabet = word.charAt(i);

			if (!indexTrie.children.containsKey(alphabet)) {
				return false;
			}
			indexTrie = indexTrie.children.get(alphabet);
		}
		return true;
	}
}
