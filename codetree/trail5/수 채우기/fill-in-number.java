import java.io.*;
import java.util.*;

public class Main {
    private static int N;

    private static final int INF = 1_000_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
    }

    private static int cal() {
        int fiveCount = N / 5;

        while (fiveCount >= 0) {
            int remain = N - (fiveCount * 5);

            if (remain % 2 == 0) {
                return fiveCount + (remain / 2);
            }

            fiveCount--;
        }

        return -1;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}