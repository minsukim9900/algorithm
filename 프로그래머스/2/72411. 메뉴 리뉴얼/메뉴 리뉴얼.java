import java.util.*;

class Solution {
    private int N;
    private int[] bitMenu;

    private void init(String[] orders) {
        N = orders.length;
        bitMenu = new int[N];

        for (int i = 0; i < N; i++) {
            int num = 0;

            for (char c : orders[i].toCharArray()) {
                num |= (1 << (c - 'A'));
            }

            bitMenu[i] = num;
        }
    }

    private void dfs(
        int[] menu,
        int sIdx,
        int depth,
        int comb,
        int limit,
        Map<Integer, Integer> countMap
    ) {
        if (depth == limit) {
            countMap.merge(comb, 1, Integer::sum);
            return;
        }

        for (int i = sIdx; i <= menu.length - (limit - depth); i++) {
            dfs(
                menu,
                i + 1,
                depth + 1,
                comb | menu[i],
                limit,
                countMap
            );
        }
    }

    private String changeBitToAlpha(int comb) {
        StringBuilder sb = new StringBuilder();

        while (comb != 0) {
            int lowBit = comb & -comb;

            int idx = Integer.numberOfTrailingZeros(lowBit);

            sb.append((char) ('A' + idx));

            comb &= (comb - 1);
        }

        return sb.toString();
    }

    public String[] solution(String[] orders, int[] course) {
        init(orders);

        List<String> answer = new ArrayList<>();

        for (int limit : course) {
            Map<Integer, Integer> countMap = new HashMap<>();

            for (int bit : bitMenu) {
                int size = Integer.bitCount(bit);

                if (size < limit) {
                    continue;
                }

                int[] menu = new int[size];

                int idx = 0;

                while (bit != 0) {
                    int lowBit = bit & -bit;
                    menu[idx++] = lowBit;
                    bit &= (bit - 1);
                }

                dfs(menu, 0, 0, 0, limit, countMap);
            }

            int max = 2;

            for (int count : countMap.values()) {
                max = Math.max(max, count);
            }

            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == max) {
                    answer.add(changeBitToAlpha(entry.getKey()));
                }
            }
        }

        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }
}