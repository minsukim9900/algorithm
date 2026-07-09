import java.util.*;

class Solution {
    private static int N, M;
    private static int plusCount, salesAmount;
    private static int[][] users;
    private static int[] emoticons;
    private static int[] selectedDiscounts;

    private static final int[] PERCENTS = {10, 20, 30, 40};

    public int[] solution(int[][] userInfos, int[] emoticonInfos) {
        users = userInfos;
        emoticons = emoticonInfos;

        N = users.length;
        M = emoticons.length;

        plusCount = 0;
        salesAmount = 0;
        selectedDiscounts = new int[M];

        dfs(0);

        return new int[] {plusCount, salesAmount};
    }

    private void dfs(int depth) {
        if (depth == M) {
            calculate();
            return;
        }

        for (int percent : PERCENTS) {
            selectedDiscounts[depth] = percent;
            dfs(depth + 1);
        }
    }

    private void calculate() {
        int currentPlusCount = 0;
        int currentSalesAmount = 0;

        for (int i = 0; i < N; i++) {
            int requiredPercent = users[i][0];
            int requiredAmount = users[i][1];

            int totalAmount = 0;

            for (int j = 0; j < M; j++) {
                int discountPercent = selectedDiscounts[j];

                if (discountPercent < requiredPercent) {
                    continue;
                }

                totalAmount += getDiscountPrice(emoticons[j], discountPercent);
            }

            if (totalAmount >= requiredAmount) {
                currentPlusCount++;
            } else {
                currentSalesAmount += totalAmount;
            }
        }

        if (plusCount < currentPlusCount ||
                (plusCount == currentPlusCount && salesAmount < currentSalesAmount)) {
            plusCount = currentPlusCount;
            salesAmount = currentSalesAmount;
        }
    }

    private int getDiscountPrice(int price, int percent) {
        return price * (100 - percent) / 100;
    }
}