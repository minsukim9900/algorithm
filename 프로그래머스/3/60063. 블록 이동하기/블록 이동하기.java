import java.util.*;

class Solution {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    private static class Node {
        int r;
        int c;
        int dir;
        int time;

        Node(int r, int c, int dir, int time) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.time = time;
        }
    }

    private int N;
    private int[][] prefix;
    private boolean[][][] visited;

    public int solution(int[][] board) {
        N = board.length;

        makePrefixSum(board);

        visited = new boolean[N + 1][N + 1][2];

        Queue<Node> q = new ArrayDeque<>();

        // 시작 상태: (1, 1), (1, 2)
        q.add(new Node(1, 1, HORIZONTAL, 0));
        visited[1][1][HORIZONTAL] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (isArrived(curr)) {
                return curr.time;
            }

            for (Node next : getNextNodes(curr)) {
                if (visited[next.r][next.c][next.dir]) {
                    continue;
                }

                visited[next.r][next.c][next.dir] = true;
                q.add(next);
            }
        }

        return -1;
    }

    private void makePrefixSum(int[][] board) {
        prefix = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int value = board[r - 1][c - 1];

                prefix[r][c] = value
                        + prefix[r - 1][c]
                        + prefix[r][c - 1]
                        - prefix[r - 1][c - 1];
            }
        }
    }

    private List<Node> getNextNodes(Node curr) {
        List<Node> result = new ArrayList<>();

        int r = curr.r;
        int c = curr.c;
        int dir = curr.dir;
        int nextTime = curr.time + 1;

        if (dir == HORIZONTAL) {
            addIfPossible(result, r - 1, c, HORIZONTAL, nextTime);
            addIfPossible(result, r + 1, c, HORIZONTAL, nextTime);
            addIfPossible(result, r, c - 1, HORIZONTAL, nextTime);
            addIfPossible(result, r, c + 1, HORIZONTAL, nextTime);

            if (isEmpty(r - 1, c, r - 1, c + 1)) {
                addIfPossible(result, r - 1, c, VERTICAL, nextTime);

                addIfPossible(result, r - 1, c + 1, VERTICAL, nextTime);
            }

            if (isEmpty(r + 1, c, r + 1, c + 1)) {
                addIfPossible(result, r, c, VERTICAL, nextTime);
                addIfPossible(result, r, c + 1, VERTICAL, nextTime);
            }
        } else {
            addIfPossible(result, r - 1, c, VERTICAL, nextTime);
            addIfPossible(result, r + 1, c, VERTICAL, nextTime);
            addIfPossible(result, r, c - 1, VERTICAL, nextTime);
            addIfPossible(result, r, c + 1, VERTICAL, nextTime);

            if (isEmpty(r, c - 1, r + 1, c - 1)) {
                addIfPossible(result, r, c - 1, HORIZONTAL, nextTime);
                addIfPossible(result, r + 1, c - 1, HORIZONTAL, nextTime);
            }

            if (isEmpty(r, c + 1, r + 1, c + 1)) {
                addIfPossible(result, r, c, HORIZONTAL, nextTime);
                addIfPossible(result, r + 1, c, HORIZONTAL, nextTime);
            }
        }

        return result;
    }

    private void addIfPossible(List<Node> result, int r, int c, int dir, int time) {
        if (canPlace(r, c, dir)) {
            result.add(new Node(r, c, dir, time));
        }
    }

    private boolean canPlace(int r, int c, int dir) {
        if (dir == HORIZONTAL) {
            return isEmpty(r, c, r, c + 1);
        }
        
        return isEmpty(r, c, r + 1, c);
    }

    private boolean isEmpty(int r1, int c1, int r2, int c2) {
        if (r1 < 1 || c1 < 1 || r2 > N || c2 > N) {
            return false;
        }

        return getSum(r1, c1, r2, c2) == 0;
    }

    private int getSum(int r1, int c1, int r2, int c2) {
        return prefix[r2][c2]
                - prefix[r1 - 1][c2]
                - prefix[r2][c1 - 1]
                + prefix[r1 - 1][c1 - 1];
    }

    private boolean isArrived(Node node) {
        int r = node.r;
        int c = node.c;
        int dir = node.dir;

        if (dir == HORIZONTAL) {
            return (r == N && c == N) || (r == N && c + 1 == N);
        }

        return (r == N && c == N) || (r + 1 == N && c == N);
    }
}