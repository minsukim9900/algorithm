import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M, K;
    private static int[] colorBit;
    private static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            colorBit = new int[N + 1];
            adj = new ArrayList[N + 1];

            st = new StringTokenizer(br.readLine());

            for (int node = 1; node <= N; node++) {
                adj[node] = new ArrayList<>();

                int color = Integer.parseInt(st.nextToken());
                colorBit[node] = 1 << (color - 1);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj[a].add(b);
                adj[b].add(a);
            }

            int maxMask = 1 << K;
            long[][] dp = new long[maxMask][N + 1];

            for (int node = 1; node <= N; node++) {
                dp[colorBit[node]][node] = 1;
            }

            long answer = 0;

            for (int mask = 1; mask < maxMask; mask++) {
                for (int node = 1; node <= N; node++) {
                    if (dp[mask][node] == 0) {
                        continue;
                    }

                    for (int next : adj[node]) {
                        int nextColorBit = colorBit[next];

                        if ((mask & nextColorBit) != 0) {
                            continue;
                        }

                        int nextMask = mask | nextColorBit;

                        dp[nextMask][next] += dp[mask][node];

                        answer += dp[mask][node];
                    }
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}