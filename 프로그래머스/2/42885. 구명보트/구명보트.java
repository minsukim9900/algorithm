import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int n = people.length;
        
        int left = 0;
        int right = n - 1;
        
        while (left <= right) {
            int low = people[left];
            int high = people[right];
            
            if (low + high <= limit) {
                left++;
            }
            
            right--;
            answer++;
        }
        
        return answer;
    }
}