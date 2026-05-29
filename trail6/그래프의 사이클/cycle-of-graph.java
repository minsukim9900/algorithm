import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] parent, childCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        childCount = new int[N + 1];

        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
            childCount[node] = 1;
        }

        String answer = "happy";
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int px = findParent(x);
            int py = findParent(y);

            if (px != py) {
                union(px, py);
            } else {
                answer = String.valueOf(i + 1);
                break;
            }
        }
        System.out.println(answer);
    }

    private static int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findParent(parent[x]);
    }

    private static void union(int x, int y) {
        if (childCount[x] >= childCount[y]) {
            childCount[x] += childCount[y];
            parent[y] = x;
        } else {
            childCount[y] += childCount[x];
            parent[x] = y;
        }
    }
}