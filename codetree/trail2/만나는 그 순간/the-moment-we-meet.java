import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, totalTime;
    private static int[][][] orders;

    private static int[] delta = {-1, 1};

    private static final int LEN = 100_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        totalTime = 0;

        orders = new int[2][][];

        orders[0] = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = "L".equals(st.nextToken()) ? 0 : 1;
            int time = Integer.parseInt(st.nextToken());

            totalTime += time;

            orders[0][i][0] = dir;
            orders[0][i][1] = time;
        }

        orders[1] = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = "L".equals(st.nextToken()) ? 0 : 1;
            int time = Integer.parseInt(st.nextToken());

            orders[1][i][0] = dir;
            orders[1][i][1] = time;
        }
    }

    private static int[] getDirInfo(int index, int length) {
        int[] dirInfo = new int[totalTime + 1];

        int currTime = 0;

        for (int i = 0; i < length; i++) {
            int dir = orders[index][i][0];
            int time = orders[index][i][1];

            for (int t = 0; t < time; t++) {
                currTime += 1;
                dirInfo[currTime] = dir;
            }
        }

        return dirInfo;
    }

    private static int simulate() {
        int result = -1;

        int[][] dirInfo = new int[2][totalTime + 1];
        dirInfo[0] = getDirInfo(0, N);
        dirInfo[1] = getDirInfo(1, M);

        int a = 0;
        int b = 0;

        for (int t = 1; t < totalTime + 1; t++) {
            a += dirInfo[0][t];
            b += dirInfo[1][t];

            if (a == b) {
                return t;
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        init();
        
        System.out.println(simulate());
    }
}