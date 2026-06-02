import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            int nodeX = Integer.parseInt(br.readLine());
            int rootX = findParent(nodeX);

            if (rootX == 0) {
                break;
            }

            answer++;
            union(rootX, rootX - 1);
        }

        System.out.println(answer);
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static void union(int rootX, int rootY) {
        parent[rootX] = rootY;
    }
}