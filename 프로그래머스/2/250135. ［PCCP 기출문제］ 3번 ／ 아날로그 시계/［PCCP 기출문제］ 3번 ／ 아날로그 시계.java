class Solution {
    public int solution(int h1,int m1,int s1,int h2,int m2,int s2) {
        long start = h1*3600L + m1*60 + s1;
        long end   = h2*3600L + m2*60 + s2;
        // 시침↔초침: 43200/719
        long hi = (start*719 + 43200 - 1) / 43200;
        long hj = (end  *719) / 43200;
        long H = Math.max(0, hj - hi + 1);
        // 분침↔초침: 3600/59
        long mi = (start*59 + 3600 - 1) / 3600;
        long mj = (end  *59) / 3600;
        long M = Math.max(0, mj - mi + 1);
        // 0초(=12시간 단위) 이벤트 중복 제거
        int overlap = 0;
        if (start <= 0 && end >= 0) overlap++;
        if (start <= 43200 && end >= 43200) overlap++;
        return (int)(H + M - overlap);
    }
}