import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static Trie root;
    private static Set<String> set;

    private static class Trie {
        int count;
        boolean isEnd;
        Map<Character, Trie> child;

        public Trie(boolean isEnd) {
            this.count = 0;
            this.isEnd = isEnd;
            child = new HashMap<>();
        }
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        root = new Trie(false);
        set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            set.add(str);
        }
    }

    private static int cal() {
        boolean isPoss = false;

        for (String str : set) {
            Trie curr = root;

            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                boolean isEnd = (j == str.length() - 1) ? true : false;

                Trie next = curr.child.getOrDefault(c, new Trie(isEnd));
                
                if (!next.isEnd && isEnd) {
                    next.isEnd = true;
                }

                next.count++;
                curr.child.put(c, next);

                if (next.count > 1 && next.isEnd) {
                    return 0;
                }

                curr = next;
            }
        }

        return 1;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}