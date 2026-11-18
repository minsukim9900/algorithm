
import java.util.*;

class Solution {
    private int N, M;

    private final int INF = Integer.MAX_VALUE;

    private void reverseRow(int[][] nums, int row) {
        for (int c = 0; c < M; c++) {
            nums[row][c] ^= 1;
        }
    }

    private void reverseCol(int[][] nums, int col) {
        for (int r = 0; r < N; r++) {
            nums[r][col] ^= 1;
        }
    }

    private int[][] copy(int[][] arr) {
        int[][] result = new int[N][M];

        for (int r = 0; r < N; r++) {
            result[r] = arr[r].clone();
        }

        return result;
    }

    private int simulate(int[][] beginning, int[][] target,
                         boolean flipFirstRow) {

        int[][] nums = copy(beginning);
        int count = 0;

        if (flipFirstRow) {
            reverseRow(nums, 0);
            count++;
        }

        for (int c = 0; c < M; c++) {
            if (nums[0][c] != target[0][c]) {
                reverseCol(nums, c);
                count++;
            }
        }

        for (int r = 1; r < N; r++) {
            if (nums[r][0] != target[r][0]) {
                reverseRow(nums, r);
                count++;
            }
        }
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (nums[r][c] != target[r][c]) {
                    return INF;
                }
            }
        }

        return count;
    }

    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;

        int answer = Math.min(
            simulate(beginning, target, false),
            simulate(beginning, target, true)
        );

        return answer == INF ? -1 : answer;
    }
}
