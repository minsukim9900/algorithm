import java.util.HashSet;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 1/42421초 단위를 1로 본다 (42421 = lcm(59, 719))
        final long UNIT = 42421L;

        long start = ((long)h1 * 3600 + m1 * 60 + s1) * UNIT;
        long end   = ((long)h2 * 3600 + m2 * 60 + s2) * UNIT;

        final long TH = 43200L * 59;
        final long TM = 3600L  * 719;

        HashSet<Long> events = new HashSet<>();

        for (long i = 0; ; i++) {
            long t = TH * i;
            if (t > end) break;
            if (t >= start) events.add(t);
        }

        for (long j = 0; ; j++) {
            long t = TM * j;
            if (t > end) break;
            if (t >= start) events.add(t);
        }
        return events.size();
    }
}