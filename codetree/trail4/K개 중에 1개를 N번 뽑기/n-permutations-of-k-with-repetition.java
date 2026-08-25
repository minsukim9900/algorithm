import java.io.*;
import java.util.*;

public class Main {
    private static int K, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dfs(0, new int[N]);
    }

    private static void dfs(int depth, int[] result) {
        if (depth == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(result[i]).append(" ");
            }

            System.out.println(sb.toString());

            return;
        }

        for (int i = 1; i < K + 1; i++) {
            result[depth] = i;
            dfs(depth + 1, result);
        }
    }
}