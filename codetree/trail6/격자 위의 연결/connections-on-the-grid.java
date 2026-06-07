import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, Node;
    private static int[] parent, size;
    private static List<int[]> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Node = N * M;
        parent = new int[Node + 1];
        size = new int[Node + 1];

        for (int node = 1; node < Node + 1; node++) {
            parent[node] = node;
            size[node] = 1;
        }

        PriorityQueue<int[]> edges = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < M; c++) {
                int x = r * M + c;
                int y = x + 1;
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new int[] { x, y, weight });
            }
        }

        for (int r = 0; r < N - 1; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < M + 1; c++) {
                int x = r * M + c;
                int y = (r + 1) * M + c;
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new int[] { x, y, weight });
            }
        }

        int pick = 0;
        int answer = 0;

        while (!edges.isEmpty() && pick != Node - 1) {
            int[] curr = edges.poll();

            int nodeX = curr[0];
            int nodeY = curr[1];
            int weight = curr[2];

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                union(rootX, rootY);
                pick++;
                answer += weight;
            }
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
        if (size[rootX] >= size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }
}
