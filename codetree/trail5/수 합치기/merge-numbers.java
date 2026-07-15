import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static PriorityQueue<Integer> pq;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            pq.add(num);
        }
    }

    private static int cal() {
        int sum = 0;

        while (pq.size() != 1) {
            int a = pq.poll();
            int b = pq.poll();

            sum += (a + b);
            pq.add(a + b);
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}