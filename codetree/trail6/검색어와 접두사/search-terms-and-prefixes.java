import java.io.*;
import java.util.*;

public class Main {
    private static class Trie {
        boolean isEnd;
        int count;
        Map<Character, Trie> child;

        public Trie() {
            this.isEnd = false;
            this.count = 0;
            child = new HashMap<>();
        }
    }

    private static int N, M;
    private static String S;
    private static Trie root;
    private static int[] result;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];

        root = new Trie();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            String word = st.nextToken();

            Trie curr = root;

            for (int j = 0; j < word.length(); j++) {
                char x = word.charAt(j);
                boolean isEnd = (j == word.length() - 1) ? true : false;
                Trie next = curr.child.get(x);

                if (next == null) {
                    next = new Trie();
                }

                next.count++;

                if (!next.isEnd && isEnd) {
                    next.isEnd = true;
                }

                curr.child.put(x, next);
                curr = next;
            }
        }

        S = br.readLine();
    }

    private static void dfs(int depth, Trie curr) {
        if (depth == M) {
            return;
        }

        Trie next  = curr.child.get(S.charAt(depth));


        if (next != null) {
            result[depth] = next.count;
            dfs(depth + 1, next);
        }
    }
    public static void main(String[] args) throws Exception {
        init();

        dfs(0, root);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            sb.append(result[i]).append(" ");
        }   
        System.out.println(sb.toString());
    }
}