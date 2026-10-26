import java.util.*;

class Solution {
    private int[] bitMenu;
    private int[] combinations;
    private int size;

    private void init(String[] orders) {
        int N = orders.length;

        bitMenu = new int[N];
        combinations = new int[N * (1 << 10)];

        for (int i = 0; i < N; i++) {
            int mask = 0;

            for (char c : orders[i].toCharArray()) {
                mask |= (1 << (c - 'A'));
            }

            bitMenu[i] = mask;
        }
    }

    private void dfs(int[] menu, int sIdx, int depth,
                     int comb, int limit) {
        if (depth == limit) {
            combinations[size++] = comb;
            return;
        }

        for (int i = sIdx; i <= menu.length - (limit - depth); i++) {
            dfs(menu, i + 1, depth + 1,
                comb | menu[i], limit);
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
            size = 0;

            for (int mask : bitMenu) {
                int len = Integer.bitCount(mask);

                if (len < limit) {
                    continue;
                }

                int[] menu = new int[len];
                int idx = 0;
                int bit = mask;

                while (bit != 0) {
                    int lowBit = bit & -bit;
                    menu[idx++] = lowBit;
                    bit &= (bit - 1);
                }

                dfs(menu, 0, 0, 0, limit);
            }

            Arrays.sort(combinations, 0, size);

            int max = 2;
            List<String> temp = new ArrayList<>();

            for (int i = 0; i < size;) {
                int j = i + 1;

                while (j < size &&
                       combinations[i] == combinations[j]) {
                    j++;
                }

                int count = j - i;

                if (count > max) {
                    max = count;
                    temp.clear();
                }

                if (count == max) {
                    temp.add(changeBitToAlpha(combinations[i]));
                }

                i = j;
            }

            answer.addAll(temp);
        }

        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }
}