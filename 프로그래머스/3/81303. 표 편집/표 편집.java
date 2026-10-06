import java.util.*;

class Solution {

    public String solution(int n, int k, String[] cmd) {

        int[] prev = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        next[n - 1] = -1;

        Deque<Integer> stack = new ArrayDeque<>();

        int currIdx = k;

        for (String command : cmd) {

            char type = command.charAt(0);

            if (type == 'U') {

                int len = Integer.parseInt(command.substring(2));

                while (len-- > 0) {
                    currIdx = prev[currIdx];
                }

            } else if (type == 'D') {

                int len = Integer.parseInt(command.substring(2));

                while (len-- > 0) {
                    currIdx = next[currIdx];
                }

            } else if (type == 'C') {

                stack.push(currIdx);

                int p = prev[currIdx];
                int nIdx = next[currIdx];

                if (p != -1) {
                    next[p] = nIdx;
                }

                if (nIdx != -1) {
                    prev[nIdx] = p;
                }

                if (nIdx != -1) {
                    currIdx = nIdx;
                } else {
                    currIdx = p;
                }

            } else {

                int idx = stack.pop();

                int p = prev[idx];
                int nIdx = next[idx];

                if (p != -1) {
                    next[p] = idx;
                }

                if (nIdx != -1) {
                    prev[nIdx] = idx;
                }
            }
        }

        char[] answer = new char[n];
        Arrays.fill(answer, 'O');

        while (!stack.isEmpty()) {
            answer[stack.pop()] = 'X';
        }

        return new String(answer);
    }
}