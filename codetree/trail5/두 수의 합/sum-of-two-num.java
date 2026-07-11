import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static long cal() {
        Map<Integer, Integer> countMap = new HashMap<>();
        long answer = 0;

        for (int num : nums) {
            int target = K - num;

            answer += countMap.getOrDefault(target, 0);

            countMap.put(
                    num,
                    countMap.getOrDefault(num, 0) + 1
            );
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}