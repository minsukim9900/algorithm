class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int len = 2 * w + 1;
        int start = 1;
        
        for (int i = 0; i < stations.length; i++) {
            int station = stations[i];
            
            int gap = station - w - start;
            
            answer += (gap + len - 1) / len;
            
            start = station + w + 1;
            
            if (start > n) {
                return answer;
            }
        }
        int gap = n - start + 1;
        answer += (gap + len - 1) / len;
        
        return answer;
    }
}