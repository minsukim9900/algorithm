import java.io.*;
import java.util.*;

public class Solution {

	private static class Trie {
		char alphabet;
		boolean isWordEnd;
		int cnt;

		Map<Character, Trie> children = new HashMap<>();

		public Trie(char alphabet) {
			this.alphabet = alphabet;
			this.cnt = 0;
		}

		public Trie() {
		}
	}

	private static int K;
	private static char[] results;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			Trie head = new Trie();
			K = Integer.parseInt(br.readLine());
			String words = br.readLine();

			int len = words.length();

			if (K > len) {
				sb.append("#").append(t).append(" ").append("none").append("\n");
				continue;
			}

			// i번째 문자에서 시작하는 접미열을 Trie에 반영
			for (int i = 0; i < len; i++) {
				Trie indexTrie = head;

				for (int j = i; j < len; j++) {
					char alphabet = words.charAt(j);

					// 새로운 문자라면 정점에 추가하기
					if (!indexTrie.children.containsKey(alphabet)) {
						Trie newTrie = new Trie(alphabet);
						indexTrie.children.put(alphabet, newTrie);
					}

					indexTrie = indexTrie.children.get(alphabet);
					indexTrie.cnt++;
				}
				indexTrie.isWordEnd = true;
			}

			results = new char[len];
			dfs(head, 0, t);
		}
		System.out.println(sb.toString());
	}

	private static void dfs(Trie trie, int depth, int t) {
		if (K == 0)
			return;

		if (trie.isWordEnd) {
			K--;
			if (K == 0) {
				sb.append("#").append(t).append(" ");
				for (int i = 0; i < depth; i++) {
					sb.append(results[i]);
				}
				sb.append("\n");
				return;
			}
		}

		for (char i = 'a'; i <= 'z'; i++) { // 낮은 알파벳부터 하나씩 이동
			if (trie.children.containsKey(i)) { // 해당 알파벳으로 이동할 수 있다면
				Trie child = trie.children.get(i);

				if (child.cnt < K) { // 해당 정점으로 이동하더라고 K개의 문자열보다 적은 개수의 문자열이 있다면
					K -= child.cnt; // 빠르게 해당 개수만큼 skip 한다.
					continue;
				}

				results[depth] = i;
				dfs(child, depth + 1, t);
			}
		}
	}

}