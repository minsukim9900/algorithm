import java.util.*;

class Solution {
    // 속성 조합(key) → 해당 조합을 만족하는 지원자의 점수 리스트
    private HashMap<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        // 1) info 전처리: 각 엔트리당 16가지 속성 조합으로 점수 저장
        for (String entry : info) {
            String[] tokens = entry.split(" "); // ["언어", "직군", "경력", "음식", "점수"]
            String lang = tokens[0], job = tokens[1], exp = tokens[2], food = tokens[3];
            int score = Integer.parseInt(tokens[4]);

            String[] attrs = { lang, job, exp, food };
            // mask: 0부터 15까지 순회하며 비트가 1인 속성은 "-"로 대체
            for (int mask = 0; mask < (1 << 4); mask++) {
                StringBuilder keyBuilder = new StringBuilder();
                for (int bit = 0; bit < 4; bit++) {
                    if ((mask & (1 << bit)) != 0) {
                        keyBuilder.append("-");
                    } else {
                        keyBuilder.append(attrs[bit]);
                    }
                    if (bit < 3) {
                        keyBuilder.append(" ");
                    }
                }
                String key = keyBuilder.toString();
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            }
        }

        // 2) 각 키에 대응하는 점수 리스트 정렬
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        // 3) query 처리
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            // 예: "java and backend and junior and pizza 100"
            String[] tokens = query[i].split(" ");
            String key = tokens[0] + " " + tokens[2] + " " + tokens[4] + " " + tokens[6];
            int targetScore = Integer.parseInt(tokens[7]);

            List<Integer> list = map.getOrDefault(key, Collections.emptyList());
            int cnt = 0;
            if (!list.isEmpty()) {
                int idx = lowerBound(list, targetScore);
                cnt = list.size() - idx;
            }
            answer[i] = cnt;
        }

        return answer;
    }

    private int lowerBound(List<Integer> arr, int target) {
        int lo = 0, hi = arr.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}