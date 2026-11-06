import java.util.*;

class Solution {
    private int N, M;
    private Map<String, Integer> map;
    private List<String>[] adj;
    private boolean[][] visited;
    private List<String> path;
    private String[] answer;

    public String[] solution(String[][] tickets) {
        map = new HashMap<>();
        M = tickets.length;

        int count = 0;

        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];

            if (!map.containsKey(from)) {
                map.put(from, count++);
            }

            if (!map.containsKey(to)) {
                map.put(to, count++);
            }
        }

        N = count;

        adj = new ArrayList[N];
        visited = new boolean[N][];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];

            int fromIdx = map.get(from);

            adj[fromIdx].add(to);
        }

        for (int i = 0; i < N; i++) {
            Collections.sort(adj[i]);
            visited[i] = new boolean[adj[i].size()];
        }

        path = new ArrayList<>();
        path.add("ICN");

        dfs("ICN", 0);

        return answer;
    }

    private boolean dfs(String current, int depth) {
        if (depth == M) {
            answer = path.toArray(new String[0]);
            return true;
        }

        int currentIdx = map.get(current);

        for (int i = 0; i < adj[currentIdx].size(); i++) {
            if (visited[currentIdx][i]) {
                continue;
            }

            String next = adj[currentIdx].get(i);

            visited[currentIdx][i] = true;
            path.add(next);

            if (dfs(next, depth + 1)) {
                return true;
            }

            visited[currentIdx][i] = false;
            path.remove(path.size() - 1);
        }

        return false;
    }
}