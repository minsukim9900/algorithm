import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, A, B;
    private static int[] parent, size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];

        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
            size[node] = 1;
        }

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            edges.add(new int[] { nodeX, nodeY, s });
        }

        System.out.println(getEdgeMaxValue(edges));
    }

    private static int getEdgeMaxValue(List<int[]> edges) {
        edges.sort((edge1, edge2) -> Integer.compare(edge2[2], edge1[2]));

        int result = Integer.MAX_VALUE;

        for (int[] edge : edges) {
            int nodeX = edge[0];
            int nodeY = edge[1];
            int s = edge[2];

            int px = findParent(nodeX);
            int py = findParent(nodeY);

            if (px != py) {
                union(px, py);
                result = Math.min(s, result);

                if (findParent(parent[A]) == findParent(parent[B])) {
                    return result;
                }
            }
        }
        return -1;
    }

    private static int findParent(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static void union(int nodeX, int nodeY) {
        if (size[nodeX] >= size[nodeY]) {
            parent[nodeY] = nodeX;
            size[nodeX] += size[nodeY];
        } else {
            parent[nodeX] = nodeY;
            size[nodeY] += size[nodeX];
        }
    }
}
