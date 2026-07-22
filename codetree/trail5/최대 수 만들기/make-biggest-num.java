import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static String[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new String[N];

        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
        }

        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));
    }

    private static String ans() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(nums[i]);
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(ans());
    }
}