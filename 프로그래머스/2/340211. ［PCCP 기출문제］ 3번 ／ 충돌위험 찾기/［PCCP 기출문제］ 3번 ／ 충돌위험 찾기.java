import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Map<String, Integer> countMap = new HashMap<>();

        for (int[] route : routes) {
            int time = 0;

            int r = points[route[0] - 1][0];
            int c = points[route[0] - 1][1];

            record(countMap, time, r, c);

            for (int i = 1; i < route.length; i++) {
                int nr = points[route[i] - 1][0];
                int nc = points[route[i] - 1][1];

                while (r != nr) {
                    r += r < nr ? 1 : -1;
                    time++;
                    record(countMap, time, r, c);
                }

                while (c != nc) {
                    c += c < nc ? 1 : -1;
                    time++;
                    record(countMap, time, r, c);
                }
            }
        }

        int answer = 0;

        for (int count : countMap.values()) {
            if (count >= 2) {
                answer++;
            }
        }

        return answer;
    }

    private void record(Map<String, Integer> countMap, int time, int r, int c) {
        String key = time + "," + r + "," + c;
        countMap.put(key, countMap.getOrDefault(key, 0) + 1);
    }
}