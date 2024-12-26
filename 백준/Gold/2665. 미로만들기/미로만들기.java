import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] maze;
    private static int[][] dist;
    private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        maze = new int[N][N];
        dist = new int[N][N];

        // Initialize maze and dist arrays
        for (int r = 0; r < N; r++) {
            String num = br.readLine();
            for (int c = 0; c < N; c++) {
                maze[r][c] = num.charAt(c) - '0';
                dist[r][c] = Integer.MAX_VALUE; // Initialize dist as a large value
            }
        }

        bfs();
        System.out.println(dist[N - 1][N - 1]); // Output the minimum wall change count
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        dist[0][0] = 0;  // Starting point, no need to change any wall
        q.offer(new Node(0, 0));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            // Explore all 4 possible directions (up, down, left, right)
            for (int i = 0; i < 4; i++) {
                int nr = curr.r + delta[i][0];
                int nc = curr.c + delta[i][1];

                // Check if the new position is within bounds
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    // If the current cell is a white cell (0), we need to increase the wall change count
                    int newDist = dist[curr.r][curr.c] + (maze[nr][nc] == 0 ? 1 : 0);

                    // If we find a better path, update the dist array and enqueue the new position
                    if (newDist < dist[nr][nc]) {
                        dist[nr][nc] = newDist;
                        q.offer(new Node(nr, nc));
                    }
                }
            }
        }
    }

    private static class Node {
        int r, c;

        private Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}