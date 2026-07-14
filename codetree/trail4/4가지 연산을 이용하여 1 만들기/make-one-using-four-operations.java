import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] delta = {-1, 1, 2, 3};

    private static final int MAX = 4_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
    }

    private static int bfs() {
        boolean[][] visited = new boolean[4][MAX];
        visited[0][N] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {N, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int num = curr[0];
            int count = curr[1];

            if (num == 1) {
                return count;
            }

            for (int i = 0; i < 4; i++) {
                int addNum = delta[i];

                if (num % addNum != 0) {
                    continue;
                }

                int next = 0;

                if (i < 2) {
                    next = num + addNum;
                } else {
                    next = num / addNum;
                }

                if (next < MAX && !visited[i][next]) {
                    visited[i][next] = true;
                    q.add(new int[] {next, count + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(bfs());
    }
}