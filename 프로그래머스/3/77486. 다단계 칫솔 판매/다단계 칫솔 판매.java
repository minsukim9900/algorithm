import java.util.*;

class Solution {

    public int[] solution(String[] enroll, String[] referral,
                          String[] seller, int[] amount) {

        int n = enroll.length;

        Map<String, Integer> map = new HashMap<>();
        int[] parent = new int[n];
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            map.put(enroll[i], i);
        }

        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1;
            } else {
                parent[i] = map.get(referral[i]);
            }
        }

        for (int i = 0; i < seller.length; i++) {

            int current = map.get(seller[i]);
            int money = amount[i] * 100;

            while (current != -1 && money > 0) {

                int commission = money / 10;
                int myMoney = money - commission;

                result[current] += myMoney;

                current = parent[current];
                money = commission;
            }
        }

        return result;
    }
}