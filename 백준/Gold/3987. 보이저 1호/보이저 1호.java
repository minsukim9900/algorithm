import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] b;
    static int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};
    static char[] C = {'U','R','D','L'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        b = new char[N][];
        for (int i = 0; i < N; i++) b[i] = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1, sc = Integer.parseInt(st.nextToken()) - 1;

        int bestDir = 0, maxT = 0;
        for (int dir = 0; dir < 4; dir++) {
            int t = simulate(sr, sc, dir);
            if (t < 0) {
                System.out.println(C[dir] + "\nVoyager");
                return;
            }
            if (t > maxT) {
                maxT = t;
                bestDir = dir;
            }
        }
        System.out.println(C[bestDir] + "\n" + maxT);
    }

    static int simulate(int r, int c, int dir) {
        boolean[][][] vis = new boolean[N][M][4];
        int t = 0;
        while (true) {
            r += d[dir][0]; c += d[dir][1];
            if (++t > 0 && (r<0||r>=N||c<0||c>=M||b[r][c]=='C')) break;
            if (vis[r][c][dir]) return -1;
            vis[r][c][dir] = true;
            char ch = b[r][c];
            if (ch == '/') dir ^= 1;
            else if (ch == '\\') dir = 3 - dir;
        }
        return t;
    }
}