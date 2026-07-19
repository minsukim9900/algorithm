import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
    }

    private static int cal() {
        int answer = 0;

        int count = 1;
        boolean pre = nums[0] >= 0 ? true : false;
        
        for (int i = 1; i < N; i++) {
            boolean curr = nums[i] >= 0 ? true : false;

            if (pre != curr) {
                answer = Math.max(answer, count);
                count = 0;
                pre = curr;
            }

            count++;
        }

        answer = Math.max(answer, count);

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}