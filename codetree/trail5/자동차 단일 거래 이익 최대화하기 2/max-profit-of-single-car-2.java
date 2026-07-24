import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }
    }

    private static int cal() {
        int result = 0;
        int min = nums[0];

        for (int i = 1; i < N; i++) {
            int curr = nums[i];

            result = Math.max(result, curr - min);
            min = Math.min(min, curr);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}