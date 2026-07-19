import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9];

        for (int count = 1; count <= 8; count++) {
            dp[count] = new HashSet<>();

            int repeatedNumber = Integer.parseInt(
                String.valueOf(N).repeat(count)
            );

            dp[count].add(repeatedNumber);

            for (int leftCount = 1; leftCount < count; leftCount++) {
                int rightCount = count - leftCount;

                for (int left : dp[leftCount]) {
                    for (int right : dp[rightCount]) {
                        dp[count].add(left + right);
                        dp[count].add(left - right);
                        dp[count].add(left * right);

                        if (right != 0) {
                            dp[count].add(left / right);
                        }
                    }
                }
            }

            if (dp[count].contains(number)) {
                return count;
            }
        }

        return -1;
    }
}