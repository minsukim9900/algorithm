class Solution {
    private static int answer;
    private static int N;
    public int solution(int n) {
        N = n;
        answer = 0;
        nQueen(0,0,0);
        return answer;
    }
    
    private static void nQueen(int curr, int diag1, int diag2) {
        if(curr == (1 << N) - 1) {
            answer++;
            return;
        }
        
        int possible = ~(curr | diag1 | diag2) & ((1 << N) - 1);
        while (possible != 0) {
            int u = possible & -possible;
            possible -= u;
            nQueen(curr | u, (diag1 | u) << 1, (diag2 | u) >> 1);
        }
    }
}