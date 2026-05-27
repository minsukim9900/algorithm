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

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            childCount[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            char command = st.nextToken().charAt(0);

            if (command == 'x') {
                int px = findP(Integer.parseInt(st.nextToken()));
                int py = findP(Integer.parseInt(st.nextToken()));
                
                if(px != py) {
                    union(px, py);
                }
            } else {
                sb.append(childCount[findP(Integer.parseInt(st.nextToken()))]).append("\n");
            }
        }
        
        System.out.println(sb.toString());
    }

    private static int findP(int x) {
        if (x == parent[x]) {
            return parent[x];
        }

        return parent[x] = findP(parent[x]);
    }

    private static void union(int x, int y) {
        if (childCount[x] >= childCount[y]) {
            parent[y] = x;
            childCount[x] += childCount[y];
        } else {
            parent[x] = y;
            childCount[y] += childCount[x];
        }
    }
}