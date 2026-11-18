import java.util.*;

class Solution {
    private int N, M;
    
    private int[][] rowNum;
    private int[][] colNum;
    
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
    
    private void dfs(int sIdx, int depth, int count, int endNum, boolean isRow, int[][] nums, int select) {
        if (depth == count) {
            
            for (int r = 0; r < N; r++) {
                int temp = 1;
                int sum = 0;
                
                for (int c = M - 1; c >= 0; c--) {
                    sum += (temp * nums[r][c]);
                    temp *= 2;
                }
                
                if (isRow) {
                    rowNum[select][r] = sum; 
                } else {
                    colNum[select][r] = sum;
                }
            }
            
            return;
        }
        
        for (int i = sIdx; i < endNum; i++) {
            if (isRow) {
                reverseRow(nums, i);
            } else {
                reverseCol(nums, i);
            }
            
            dfs(i + 1, depth + 1, count, endNum, isRow, nums, select | (1 << i));
            
            if (isRow) {
                reverseRow(nums, i);
            } else {
                reverseCol(nums, i);
            }
        }
    }
    
    private int[][] copy(int[][] arr) {
        int[][] result = new int[N][M];
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                result[r][c] = arr[r][c];
            }
        }
        
        return result;
    }
    
    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;
        
        int rowSize = 1 << N;
        int colSize = 1 << M;
        
        rowNum = new int[rowSize][N];
        colNum = new int[colSize][N];
        
        int[] diff = new int[N];
        
        for (int r = 0; r < N; r++) {
            int begin = 0;
            int end = 0;

            for (int c = 0; c < M; c++) {
                begin = (begin << 1) | beginning[r][c];
                end = (end << 1) | target[r][c];
            }

            rowNum[0][r] = begin;
            colNum[0][r] = begin;

            diff[r] = begin ^ end;
        }
        
        
        for (int count = 1; count < N + 1; count++) {
            dfs(0, 0, count, N, true, copy(beginning), 0);
        }
        
        for (int count = 1; count < M + 1; count++) {
            dfs(0, 0, count, M, false, copy(beginning), 0);
        }
        
        int answer = Integer.MAX_VALUE;
        
        for (int rowMask = 0; rowMask < rowSize; rowMask++) {
            for (int colMask = 0; colMask < colSize; colMask++) {

                boolean isMatch = true;

                for (int r = 0; r < N; r++) {
                    int value = rowNum[rowMask][r]
                              ^ colNum[colMask][r];

                    if (value != diff[r]) {
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) {
                    int count = Integer.bitCount(rowMask)
                              + Integer.bitCount(colMask);

                    answer = Math.min(answer, count);
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}