import java.io.*;
import java.util.*;

public class Solution {
    static int N, L, answer;
    static int[][] infos;        // 1..N, [i][0]=flavor, [i][1]=cal
    static int[] suffixFlavor;   // suffixFlavor[i] = i..N의 flavor 합(낙관적 상한)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            infos = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                infos[i][0] = Integer.parseInt(st.nextToken()); // flavor
                infos[i][1] = Integer.parseInt(st.nextToken()); // cal
            }

            // 낙관적 상한(칼로리 무시하고 남은 맛 점수 총합)
            suffixFlavor = new int[N + 2];
            for (int i = N; i >= 1; i--) {
                suffixFlavor[i] = suffixFlavor[i + 1] + infos[i][0];
            }

            answer = 0;
            dfs(1, 0, 0);
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb.toString());
    }

    static void dfs(int idx, int flavorSum, int calSum) {
        if (calSum > L) return;                    // 칼로리 초과 컷
        if (idx == N + 1) {                        // 끝
            if (flavorSum > answer) answer = flavorSum;
            return;
        }
        // 낙관적 상한으로 가지치기 (남은 걸 다 더해도 이하면 컷)
        if (flavorSum + suffixFlavor[idx] <= answer) return;

        // 선택
        dfs(idx + 1, flavorSum + infos[idx][0], calSum + infos[idx][1]);
        // 스킵
        dfs(idx + 1, flavorSum, calSum);
    }
}