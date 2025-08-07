import java.io.*;
import java.util.*;

public class Main {

	private static class Trie {
		char number;
		boolean isWordEnd;
		int cnt;

		Map<Character, Trie> children = new HashMap<>();

		Trie(char number) {
			this.number = number;
			this.cnt = 0;
		}

		public Trie() {
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			Trie head = new Trie();

			String[] phone = new String[N];
			for (int i = 0; i < N; i++) {
				phone[i] = br.readLine();

				Trie indexTrie = head;
				for (int j = 0; j < phone[i].length(); j++) {
					char number = phone[i].charAt(j);

					if (!indexTrie.children.containsKey(number)) {
						Trie newTrie = new Trie(number);
						indexTrie.children.put(number, newTrie);
					}
					indexTrie = indexTrie.children.get(number);
					indexTrie.cnt++;
				}
				indexTrie.isWordEnd = true;
			}
			boolean isConsist = true;

			for (String number : phone) {
				if (isContain(number, head)) {
					isConsist = false;
					break;
				}
			}

			System.out.println(isConsist ? "YES" : "NO");
		}
	}

	private static boolean isContain(String number, Trie trie) {
		for (int n = 0; n < number.length(); n++) {
			Trie child = trie.children.get(number.charAt(n));

			if (child == null) {
				return false;
			}
			trie = child;
		}

		if (trie.isWordEnd) {
			if (trie.children.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}