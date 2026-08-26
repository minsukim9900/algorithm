import java.io.*;
import java.util.*;

public class Main {
    private static int N, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        answer = 0;

        dfs(0, new int[N]);

        System.out.println(answer);
    }

    private static void dfs(int depth, int[] result) {

        if (depth == N) {
            if (isPoss(depth - 1, result)) {
                answer++;
            }
            return;
        }

        for (int num = 1; num < 5; num++) {
            result[depth] = num;
            dfs(depth + 1, result);
            
        }
    }

    private static boolean isPoss(int depth, int[] result) {
        int pre = result[0];
        int count = 1;

        for (int i = 1; i < depth + 1; i++) {
            if (pre != result[i]) {
                if (count % pre != 0) {
                    return false;
                }

                pre = result[i];
                count = 1;
            } else {
                count++;
            }
        }

        if (count % pre != 0) {
            return false;
        }

        return true;
    }
}