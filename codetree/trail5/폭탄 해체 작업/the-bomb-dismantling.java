import java.io.*;
import java.util.*;
public class Main {
    private static int N, endTime;
    private static int[][] bombs;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        endTime = 0;

        bombs = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int score = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            bombs[i][0] = score;
            bombs[i][1] = time;
        }

        Arrays.sort(bombs, (a, b) -> a[1] == b[1] ? Integer.compare(b[0], a[0]) : Integer.compare(a[1], b[1]));
    }

    private static int cal() {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int[] curr = bombs[i];

            int score = curr[0];
            int time = curr[1];

            pq.add(score);

            if (pq.size() > time) {
                pq.poll();
            }
        }

        while(!pq.isEmpty()) {
            answer += pq.poll();
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}