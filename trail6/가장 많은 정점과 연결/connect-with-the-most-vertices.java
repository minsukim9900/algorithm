import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[] parent, cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int node = 1; node < N + 1; node++) {
            cost[node] = Integer.parseInt(st.nextToken());
            parent[node] = node;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                union(rootX, rootY);
            }
        }

        String answer = "NO";

        boolean[] visited = new boolean[N + 1];
        int componentCount = 0;
        int minCost = Integer.MAX_VALUE;
        int sum = 0;

        for (int node = 1; node < N + 1; node++) {
            int currRoot = findParent(node);

            if (visited[currRoot]) {
                continue;
            }

            int currCost = cost[currRoot];

            componentCount++;
            visited[currRoot] = true;
            sum += currCost;
            minCost = Math.min(currCost, minCost);
        }

        if (componentCount == 1) {
            answer = "0";
        } else {
            int result = sum + (componentCount - 2) * minCost;

            answer = result <= K ? String.valueOf(result) : answer;
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
        if (cost[rootX] <= cost[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
    }
}