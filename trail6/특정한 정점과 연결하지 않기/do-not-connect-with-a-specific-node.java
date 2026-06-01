import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] parent, size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            size[i] = 1;
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

        boolean[] visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int rootA = findParent(A);
        int rootB = findParent(B);

        int answer = size[rootA];
        visited[rootA] = true;
        visited[rootB] = true;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int node = 1; node < N + 1; node++) {
            int rootNode = findParent(node);

            if (visited[rootNode])
                continue;

            visited[rootNode] = true;
            int componentSize = size[rootNode];

            if (pq.size() < K) {
                pq.add(componentSize);
            } else if (K > 0 && pq.peek() < componentSize) {
                pq.poll();
                pq.add(componentSize);
            }
        }

        while(!pq.isEmpty()) {
            answer += pq.poll();
        }
        
        System.out.println(answer);
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static void union(int nodeX, int nodeY) {
        if (size[nodeX] >= size[nodeY]) {
            size[nodeX] += size[nodeY];
            parent[nodeY] = nodeX;
        } else {
            size[nodeY] += size[nodeX];
            parent[nodeX] = nodeY;
        }
    }
}