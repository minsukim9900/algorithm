import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static Trie root = new Trie();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int depth = Integer.parseInt(st.nextToken());

			String[] words = new String[depth];
			for (int d = 0; d < depth; d++) {
				words[d] = st.nextToken();
			}

			init(words);
		}
		
		traverse(root, "", sb);
		System.out.println(sb.toString());
	}

	private static void init(String[] words) {
		Trie current = root;

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Map<String, Trie> map = current.children;

			Trie next = null;
			if (!map.containsKey(word)) {
				next = new Trie();
				map.put(word, next);
			} else {
				next = map.get(word);
			}

			current = next;
		}
	}

	private static void traverse(Trie current, String token, StringBuilder sb) {
		for (String word : current.children.keySet()) {
			sb.append(token).append(word).append("\n");
			traverse(current.children.get(word), token + "--", sb);
		}
	}

	private static class Trie {
		Map<String, Trie> children;

		Trie() {
			this.children = new TreeMap<>();
		}
	}
}