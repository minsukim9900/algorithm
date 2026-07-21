import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static PriorityQueue<int[]> pq;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.add(new int[] {s, e});
        }
    }

    private static int cal() {
        int count = 1;
        int endTime = pq.poll()[1];

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();

            int start = curr[0];
            int end = curr[1];

            if (endTime > start) {
                continue;
            }

            count++;
            endTime = end;
        }

        return count;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}