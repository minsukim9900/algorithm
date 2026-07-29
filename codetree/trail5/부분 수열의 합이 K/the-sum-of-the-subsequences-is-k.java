import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] nums, prefix;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }

        prefix = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    private static int cal() {
        int result = 0;

        for (int range = 1; range < N + 1; range++) {
            for (int left = 1; left <= N - range + 1; left++) {
                int right = left + range - 1;
                int sum = prefix[right] - prefix[left - 1];
                
                if (sum == K) {
                    result++;
                }
            }
        }
        
        return result;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}