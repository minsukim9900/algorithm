import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        answer = 0L;

        for (int i = 1; i < 5; i++) {
            dfs(1, i, 1);
        }

        System.out.println(answer);
    }

    private static void dfs(int depth, int pre, int count) {
        if (depth == N) {
            if (count % pre == 0) {
                answer++;
            }
            return;
        }

        for (int num = 1; num < 5; num++) {

            if (depth == 0) {
                dfs(depth + 1, num, 1);
            }
            
            if (num == pre) {
                dfs(depth + 1, pre, count + 1);
            } else if (count % pre == 0) {
                dfs(depth + 1, num, 1);
            }
        }
    }
}