import java.io.*;
import java.util.*;

public class Main {
    private static final String PREFIX = "--";
    private static Trie root;
    private static class Trie {
        int count;
        boolean end;
        Map<Character, Trie> child;

        public Trie() {
            this.count = 0;
            this.end = false;
            child = new HashMap<>();
        }
    }
    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        
        root = new Trie();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());

            Trie curr = root;

            for (int j = 0; j < k; j++) {
                char x = st.nextToken().charAt(0);
                boolean end = (j == k - 1) ? true : false;

                Trie next = curr.child.get(x);

                if (next == null) {
                    next = new Trie();
                }

                if (!next.end) {
                    next.end = end;
                }

                next.count++;
                curr.child.put(x, next);

                curr = next;
            }
        }
    }

    private static void dfs(int depth, char x, Trie curr) {
        for (int i = 0; i < depth; i++) {
            System.out.print(PREFIX);
        }

        if (depth != -1) {
            System.out.println(x);
        }

        for (int i = 0; i < 'z' - 'a' + 1; i++) {
            char c = (char) ('A' + i);

            if (curr.child.containsKey(c)) {
                dfs(depth + 1, c, curr.child.get(c));
            }
        }
    }
    public static void main(String[] args) throws Exception {
        init();

        dfs(-1, ' ', root);
    }
}