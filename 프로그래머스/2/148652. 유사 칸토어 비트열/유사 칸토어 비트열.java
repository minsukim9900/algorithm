import java.util.*;

class Solution {
    long[] pow5 = new long[21];
    long[] pow4 = new long[21];

    public long solution(int n, long l, long r) {
        buildPowers(n);
        return prefix(n, r) - prefix(n, l - 1);
    }

    // 길이 x까지(1-indexed) 접두사에 있는 1의 개수
    private long prefix(int n, long x) {
        if (x <= 0) return 0;
        // n=0일 때 비트열은 길이 1의 "1"
        if (n == 0) return Math.min(1, x);
        if (x >= pow5[n]) return pow4[n];

        long blk = pow5[n - 1];            // 하위 단계 블록 길이
        long full = x / blk;               // 꽉 찬 블록 개수 (0~4)
        long rem  = x % blk;               // 다음 블록에서 남은 길이

        long sum = 0;
        for (int b = 0; b < full; b++) {
            if (b == 2) sum += 0;          // 가운데 블록(전체 0)
            else sum += pow4[n - 1];       // 나머지 블록(전체 1의 개수)
        }

        // 다음 블록의 일부(rem) 처리: 가운데면 0, 아니면 하위 단계로 재귀
        if (full < 5 && full != 2) {
            sum += prefix(n - 1, rem);
        }
        return sum;
    }

    private void buildPowers(int n) {
        pow5[0] = 1; pow4[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow5[i] = pow5[i - 1] * 5L;
            pow4[i] = pow4[i - 1] * 4L;
        }
    }
}