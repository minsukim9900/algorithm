import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] parent, childCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        childCount = new int[N + 1];

        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
            childCount[node] = 1;
        }

        int removeOperationCount = 0;
        for (int edgeInfo = 0; edgeInfo < M; edgeInfo++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            int parentX = findParent(nodeX);
            int parentY = findParent(nodeY);

            if (parentX != parentY) {
                union(parentX, parentY);
            } else {
                removeOperationCount++;
            }
        }

        System.out.println(countParentNode(removeOperationCount));
    }

    private static int findParent(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static void union(int nodeX, int nodeY) {
        if (childCount[nodeX] >= childCount[nodeY]) {
            parent[nodeY] = nodeX;
            childCount[nodeX] += childCount[nodeY];
        } else {
            parent[nodeX] = nodeY;
            childCount[nodeY] += childCount[nodeX];
        }
    }

    private static int countParentNode(int removeOperationCount) {
        boolean[] isParent = new boolean[N + 1];

        int result = removeOperationCount;
        boolean isAddEdge = false;

        for (int node = 1; node < N + 1; node++) {
            int parentNode = findParent(node);

            if (isParent[parentNode])
                continue;

            result++;
            isParent[parentNode] = true;
            isAddEdge = true;
        }

        return isAddEdge ? result - 1 : result;
    }
}
