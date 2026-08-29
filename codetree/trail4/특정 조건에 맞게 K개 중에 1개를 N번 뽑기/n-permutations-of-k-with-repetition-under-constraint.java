import java.io.*;
import java.util.*;

public class Main {
    private static int K, N;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dfs(0, 0, 0, new int[N]);
        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int pre, int count, int[] result) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                sb.append(result[i]).append(" ");
            }
            
            sb.append("\n");
            return;
        }

        for (int i = 1; i < K + 1; i++) {
            if (pre == i && count == 2) {
                continue;
            }

            if (pre == i) {
                result[depth] = i;
                dfs(depth + 1, i, count + 1,result);
            } else {
                result[depth] = i;
                dfs(depth + 1, i, 1, result);
            }
        }
    }
}