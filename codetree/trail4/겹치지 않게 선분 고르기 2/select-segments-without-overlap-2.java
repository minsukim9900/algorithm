import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] lines;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            lines[i][0] = x1;
            lines[i][1] = x2;
        }

        Arrays.sort(lines, (a, b) -> Integer.compare(a[1], b[1]));
    }

    private static int cal() {
        int result = 0;
        
        int end = -1;
        for(int i = 0; i < N; i++) {
            int x1 = lines[i][0];
            int x2 = lines[i][1];

            if(end < x1) {
                result++;
                end = x2;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}