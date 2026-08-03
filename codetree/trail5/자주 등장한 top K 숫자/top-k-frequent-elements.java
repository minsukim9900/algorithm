import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static Map<Integer, Integer> map;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            int count = map.getOrDefault(num, 0);

            map.put(num, count + 1);
        }
    }

    private static String solution() {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());

        entries.sort((a, b) -> a.getValue() == b.getValue() ? Integer.compare(b.getKey(), a.getKey()) : Integer.compare(b.getValue(), a.getValue()));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            sb.append(entries.get(i).getKey()).append(" ");
        }

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(solution());
    }
}