import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] values;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        values = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            int v = Integer.parseInt(st.nextToken());

            values[i] = v;
        }
    }

    private static int cal() {
        int[] dp = new int[N + 1];
        
        for(int currLen = 1; currLen < N + 1; currLen++) {
            int v = values[currLen];

            for(int compLen = currLen; compLen < N + 1; compLen++) {
                dp[compLen] = Math.max(dp[compLen], dp[compLen - currLen] + v);
            }
        }

        return dp[N];
    }
    
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}