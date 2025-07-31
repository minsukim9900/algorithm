import java.io.*;
import java.util.*;

class Solution {
    private static int N, P;
    private static long[][] memo;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int T = fr.nextInt();
        for (int t = 1; t <= T; t++) {
            N = fr.nextInt();
            P = fr.nextInt();

            memo = new long[N + 1][P + 1];
            for (long[] row : memo) Arrays.fill(row, -1);

            long ans = simulate(N, P);
            out.printf("#%d %d\n", t, ans);
        }

        out.flush();
        out.close();
    }

    private static long simulate(int n, int p) {
        if (p == 1) return n;
        if (memo[n][p] != -1) return memo[n][p];
        long result = (n / p) * simulate(n - n / p, p - 1);
        memo[n][p] = result;
        return result;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
        String nextLine() throws IOException {
            return br.readLine();
        }
    }
}