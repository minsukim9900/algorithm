import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] coordinate;
    private static int[][] orders;
    
    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coordinate = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());

            coordinate[i] = x;
        }

        Arrays.sort(coordinate);

        orders = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            orders[i][0] = start;
            orders[i][1] = end;
        }
    }

    private static String cal() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int start = orders[i][0];
            int end = orders[i][1];

            int startCount = lowerBound(start);
            int endCount = upperBound(end);

            sb.append(endCount - startCount).append("\n");
        }

        return sb.toString();
    }

    private static int upperBound(int target) {
        int s = 0;
        int e = N - 1;
        int answer = N;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (coordinate[mid] > target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return answer;
    }

    private static int lowerBound(int target) {
        int s = 0;
        int e = N - 1;
        int answer = N;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (coordinate[mid] >= target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}