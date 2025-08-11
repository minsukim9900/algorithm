import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        final int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static int N, M, T, K;
    static Point[] jewels;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 가로 한계 (x 최대)
        M = Integer.parseInt(st.nextToken());  // 세로 한계 (y 최대)
        T = Integer.parseInt(st.nextToken());  // 보석 개수
        K = Integer.parseInt(st.nextToken());  // 그물 한 변 길이

        jewels = new Point[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            jewels[i] = new Point(x, y);
        }

        // 좌상단 후보 집합 만들기 (중복 제거를 위해 Set 사용)
        Set<Integer> candX = new HashSet<>();
        Set<Integer> candY = new HashSet<>();

        // 좌상단 x는 [0, N-K] 범위여야 함 → 각 보석 x를 클램프하여 후보로
        // 좌상단 y는 [K, M] 범위여야 함 → 각 보석 y를 클램프하여 후보로
        int maxLeftX = Math.max(0, N - K); // N-K가 음수일 가능성 거의 없지만 안전 가드
        for (Point p : jewels) {
            candX.add(clamp(p.x, 0, maxLeftX));
            candY.add(clamp(p.y, K, M));
        }
        // 경계에 정확히 붙는 케이스도 포함 (이미 들어있어도 Set이라 중복 무시)
        candX.add(maxLeftX);
        candY.add(K);
        candY.add(M);

        // 탐색
        int bestTx = 0, bestTy = K; // 좌상단 초기값(유효 범위 내)
        int bestCnt = -1;

        for (int tx : candX) {
            int right = tx + K;
            for (int ty : candY) {
                int bottom = ty - K;

                int cnt = 0;
                for (Point p : jewels) {
                    if (tx <= p.x && p.x <= right && bottom <= p.y && p.y <= ty) {
                        cnt++;
                    }
                }

                // 더 많은 보석을 잡거나, 동률이면 좌상단이 "사전순으로 앞선" 좌표 선택
                if (cnt > bestCnt || (cnt == bestCnt && (tx < bestTx || (tx == bestTx && ty < bestTy)))) {
                    bestCnt = cnt;
                    bestTx = tx;
                    bestTy = ty;
                }
            }
        }

        // 출력: 좌상단 (x, y)와 최대 개수
        System.out.println(bestTx + " " + bestTy);
        System.out.println(bestCnt);
    }

    /** a를 [lo, hi] 범위로 클램프 */
    private static int clamp(int a, int lo, int hi) {
        if (a < lo) return lo;
        if (a > hi) return hi;
        return a;
    }
}