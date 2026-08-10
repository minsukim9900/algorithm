import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static Map<String, String> map;
    private static String[] orders;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();

        for (int i = 1; i < N + 1; i++) {
            String word = br.readLine();
            String idx = String.valueOf(i);

            map.put(word, idx);
            map.put(idx, word);
        }

        orders = new String[M];
        
        for (int i = 0; i < M; i++) {
            String order = br.readLine();

            orders[i] = order;
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String order = orders[i];

            sb.append(map.get(order)).append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(solution());
    }
}