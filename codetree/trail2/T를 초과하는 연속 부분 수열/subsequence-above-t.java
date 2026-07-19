import java.io.*;
import java.util.*;

public class Main {
    private static int N, T;
    private static int[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }
    }

    private static int cal() {
        int answer = 0;
        int count = 0;
        int pre = -1;

        for (int i = 0; i < N; i++) {
            int curr = nums[i];

            if (curr <= T) {
                count = 0;
            } else {
                count++;
            }

            pre = curr;
            answer = Math.max(answer, count);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}