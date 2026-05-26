class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long num = l - 1; num < r; num++) {
            boolean flag = true;
            
            long x = num;
            while(x >= 5) {
                if (x % 5 == 2) {
                    flag = false;
                    break;
                }
                
                x /= 5;
            }
            
            if (x == 2) {
                flag = false;
            }
            
            if(flag) {
                answer++;
            }
        }
        return answer;
    }
}