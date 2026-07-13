import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] parent, size;
    private static int[][] dots;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        size = new int[N];
        dots = new int[N][4];
        
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            dots[i][0] = i;
            dots[i][1] = x;
            dots[i][2] = y;
            dots[i][3] = z;
        }
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

    private static int[][] getEdges(int order) {
        Arrays.sort(dots, (a, b) -> Integer.compare(a[order], b[order]));

        int[][] edges = new int[N - 1][3];

        for (int i = 0; i < N - 1; i++) {
            int[] curr = dots[i];
            int[] next = dots[i + 1];

            int currNode = curr[0];
            int nextNode = next[0];
            int dist = next[order] - curr[order];

            edges[i][0] = currNode;
            edges[i][1] = nextNode;
            edges[i][2] = dist;
        }

        return edges;
    }

    private static long solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int order = 1; order < 4; order++) {
            int[][] edges = getEdges(order);

            for(int[] edge : edges) {
                pq.add(edge);
            }
        }

        int pick = 0;
        long answer = 0;

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();

            int nodeX = edge[0];
            int nodeY = edge[1];
            int weight = edge[2];

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                union(rootX, rootY);
                pick++;
                answer += weight;

                if (pick == N - 1) {
                    break;
                }
            }
        }


        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(solution());
    }
}