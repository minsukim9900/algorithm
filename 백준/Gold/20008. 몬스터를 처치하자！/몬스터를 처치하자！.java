import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 6;
    static final int INF = 2_000_000_000;
    static int N, HP;
    static int[] C = new int[MAX];
    static int[] D = new int[MAX];
    static int[] T = new int[MAX];
    static int Answer = INF;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        HP = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, HP);
        System.out.println(Answer);
    }

    static void dfs(int time, int nowHP) {
        if (time > Answer) return;

        if (nowHP <= 0) {
            Answer = Math.min(Answer, time);
            return;
        }

        boolean fired = false;

        for (int i = 0; i < N; i++) {
            if (T[i] <= time) {
                fired = true;
                int prev = T[i];
                T[i] = time + C[i];
                dfs(time + 1, nowHP - D[i]);
                T[i] = prev;
            }
        }

        if (!fired) {
            dfs(time + 1, nowHP);
        }
    }
}