class Solution {

    public int[] solution(long begin, long end) {

        int[] answer = new int[(int) (end - begin + 1)];
        
        for (int num = 1; num <= 10_000_000; num++) {
            long start = Math.max((long) num * 2, ((begin + num - 1) / num) * num);

            for (long target = start; target <= end; target += num) {
                answer[(int) (target - begin)] = num;
            }
        }
  
        return answer;
    }
}