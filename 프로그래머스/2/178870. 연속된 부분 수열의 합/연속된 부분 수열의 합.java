class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int n = sequence.length;
        int length = n + 1;
        int left = 0;
        int sum = 0;
        
        for (int right = 0; right < n; right++) {
            sum += sequence[right];
            
            while (sum > k) {
                sum -= sequence[left++];
            }
            
            if (sum == k && length > (right - left + 1)) {
                length = right - left + 1;
                answer[0] = left;
                answer[1] = right;
            }
        }
        
        return answer;
    }
}