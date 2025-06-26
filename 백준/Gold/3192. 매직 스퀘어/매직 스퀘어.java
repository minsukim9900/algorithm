import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] a = new int[3][3];
        List<int[]> zeros = new ArrayList<>();
        long sumKnown = 0;

        // 1) 입력 읽고 0 위치와 알려진 값들의 합 기록
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                } else {
                    sumKnown += a[i][j];
                }
            }
        }

        // 2) 마법 상수 S를 구해본다 (완전 채워진 가로·세로·대각선이 있으면 바로 결정)
        int S = 0;
        // 행
        outer:
        for (int i = 0; i < 3 && S == 0; i++) {
            if (a[i][0] != 0 && a[i][1] != 0 && a[i][2] != 0) {
                S = a[i][0] + a[i][1] + a[i][2];
                break outer;
            }
        }
        // 열
        outer:
        for (int j = 0; j < 3 && S == 0; j++) {
            if (a[0][j] != 0 && a[1][j] != 0 && a[2][j] != 0) {
                S = a[0][j] + a[1][j] + a[2][j];
                break outer;
            }
        }
        // 주대각선
        if (S == 0 && a[0][0] != 0 && a[1][1] != 0 && a[2][2] != 0) {
            S = a[0][0] + a[1][1] + a[2][2];
        }
        // 부대각선
        if (S == 0 && a[0][2] != 0 && a[1][1] != 0 && a[2][0] != 0) {
            S = a[0][2] + a[1][1] + a[2][0];
        }

        // 3) 특수 케이스: 0이 정확히 3개이고, 위에서 S를 못 구했다면
        //    (즉 3개 모두가 한 대각선 위에 몰려 있는 경우)
        //    sumKnown = 6개의 알려진 값 합 → 6 + (x+y+z) = 3S, 또 x+y+z=S 이므로
        //    sumKnown + S = 3S → sumKnown = 2S → S = sumKnown/2
        if (S == 0 && zeros.size() == 3) {
            S = (int)(sumKnown / 2);
        }

        // 4) 이제 “빈 칸이 1개인 줄”을 찾아 반복적으로 채워 나간다
        boolean updated;
        do {
            updated = false;
            for (int[] z : zeros) {
                int i = z[0], j = z[1];
                if (a[i][j] != 0) continue;  // 이미 채워진 칸이면 건너뛰기

                // (1) 같은 행에서
                boolean ok = true;
                int sum = 0;
                for (int k = 0; k < 3; k++) {
                    if (k != j && a[i][k] == 0) { ok = false; break; }
                    sum += a[i][k];
                }
                if (ok) {
                    a[i][j] = S - sum;
                    updated = true;
                    continue;
                }

                // (2) 같은 열에서
                ok = true; sum = 0;
                for (int k = 0; k < 3; k++) {
                    if (k != i && a[k][j] == 0) { ok = false; break; }
                    sum += a[k][j];
                }
                if (ok) {
                    a[i][j] = S - sum;
                    updated = true;
                    continue;
                }

                // (3) 주대각선
                if (i == j) {
                    ok = true; sum = 0;
                    for (int k = 0; k < 3; k++) {
                        if (k != i && a[k][k] == 0) { ok = false; break; }
                        sum += a[k][k];
                    }
                    if (ok) {
                        a[i][j] = S - sum;
                        updated = true;
                        continue;
                    }
                }

                // (4) 부대각선
                if (i + j == 2) {
                    ok = true; sum = 0;
                    for (int k = 0; k < 3; k++) {
                        int x = k, y = 2 - k;
                        if (x != i && a[x][y] == 0) { ok = false; break; }
                        sum += a[x][y];
                    }
                    if (ok) {
                        a[i][j] = S - sum;
                        updated = true;
                    }
                }
            }
        } while (updated);

        // 5) 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(a[i][0]).append(' ')
              .append(a[i][1]).append(' ')
              .append(a[i][2]).append('\n');
        }
        System.out.print(sb);
    }
}