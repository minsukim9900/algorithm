import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        int n = gems.length;

        for (int i = 0; i < n; i++) {
            set.add(gems[i]);
        }

        int count = set.size();

        int left = 0;
        int right = 0;

        int answerLeft = 0;
        int answerRight = 0;

        Map<String, Integer> buffer = new HashMap<>();

        int len = Integer.MAX_VALUE;

        while (right < n) {
            buffer.put(gems[right],
                buffer.getOrDefault(gems[right], 0) + 1);

            while (buffer.size() == count) {
                if (len > right - left + 1) {
                    len = right - left + 1;
                    answerLeft = left + 1;
                    answerRight = right + 1;
                }

                String gem = gems[left];

                buffer.put(gem, buffer.get(gem) - 1);

                if (buffer.get(gem) == 0) {
                    buffer.remove(gem);
                }

                left++;
            }

            right++;
        }

        return new int[]{answerLeft, answerRight};
    }
}