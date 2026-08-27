import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();

        dfs(1, 0, new int[M]);

        System.out.println(sb.toString());
    }

    private static void dfs(int idx, int depth, int[] result) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = idx; i < N + 1; i++) {
            result[depth] = i;

            dfs(i + 1, depth + 1, result);
        }
    }
}