
import java.util.*;

class Solution {
    private class Info {
        List<int[]> shape;
        int height;
        int width;
        int count;

        public Info(List<int[]> shape, int height, int width, int count) {
            this.shape = shape;
            this.height = height;
            this.width = width;
            this.count = count;
        }
    }

    private int N;

    private final int[][] DELTA = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;

        boolean[][] visited = new boolean[N][N];

        List<List<int[]>[]>[][] save = new ArrayList[N + 1][N + 1];

        for (int r = 0; r <= N; r++) {
            for (int c = 0; c <= N; c++) {
                save[r][c] = new ArrayList<>();
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (game_board[r][c] == 0 && !visited[r][c]) {
                    Info info = getInfo(r, c, 0, visited, game_board);

                    save[info.height][info.width].add(
                        rotate(info.shape, info.height, info.width)
                    );
                }
            }
        }

        visited = new boolean[N][N];

        int answer = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (table[r][c] == 1 && !visited[r][c]) {
                    Info info = getInfo(r, c, 1, visited, table);

                    int h = info.height;
                    int w = info.width;

                    boolean matched = match(save[h][w], info.shape);

                    if (!matched && h != w) {
                        matched = match(save[w][h], info.shape);
                    }

                    if (matched) {
                        answer += info.count;
                    }
                }
            }
        }

        return answer;
    }

    private boolean match(
        List<List<int[]>[]> candidates,
        List<int[]> shape
    ) {
        for (int i = 0; i < candidates.size(); i++) {
            List<int[]>[] rotations = candidates.get(i);

            for (int d = 0; d < 4; d++) {
                if (isSame(shape, rotations[d])) {
                    candidates.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isSame(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] ||
                a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }

    private boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private Info getInfo(
        int sr,
        int sc,
        int target,
        boolean[][] visited,
        int[][] board
    ) {
        visited[sr][sc] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});

        int count = 0;

        List<int[]> buffer = new ArrayList<>();

        int minR = sr;
        int minC = sc;
        int maxR = sr;
        int maxC = sc;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            count++;

            minR = Math.min(minR, r);
            minC = Math.min(minC, c);
            maxR = Math.max(maxR, r);
            maxC = Math.max(maxC, c);

            buffer.add(curr);

            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];

                if (isRange(nr, nc) &&
                    board[nr][nc] == target &&
                    !visited[nr][nc]) {

                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        List<int[]> result = new ArrayList<>();

        int height = maxR - minR + 1;
        int width = maxC - minC + 1;

        for (int[] curr : buffer) {
            int r = curr[0];
            int c = curr[1];

            result.add(new int[]{r - minR, c - minC});
        }

        result.sort((a, b) ->
            a[0] == b[0]
                ? Integer.compare(a[1], b[1])
                : Integer.compare(a[0], b[0])
        );

        return new Info(result, height, width, count);
    }

    private List<int[]>[] rotate(
        List<int[]> shape,
        int height,
        int width
    ) {
        List<int[]>[] result = new ArrayList[4];

        for (int i = 0; i < 4; i++) {
            result[i] = new ArrayList<>();
        }

        for (int[] point : shape) {
            int r = point[0];
            int c = point[1];

            result[0].add(new int[]{r, c});
            result[1].add(new int[]{c, height - 1 - r});
            result[2].add(new int[]{
                height - 1 - r, width - 1 - c
            });
            result[3].add(new int[]{width - 1 - c, r});
        }

        for (int i = 0; i < 4; i++) {
            result[i].sort((a, b) ->
                a[0] == b[0]
                    ? Integer.compare(a[1], b[1])
                    : Integer.compare(a[0], b[0])
            );
        }

        return result;
    }
}
