import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static boolean[] cards;
    private static PriorityQueue<Integer> B;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = 2 * N + 1;
        
        B = new PriorityQueue<>();
        cards = new boolean[M];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            cards[num] = true;
            B.add(num);
        }
    }

    private static int cal() {
        int count = 0;

        for (int num = 1; num < M; num++) {
            if (cards[num]) {
                continue;
            }

            if (num > B.peek()) {
                count++;
                B.poll();
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}