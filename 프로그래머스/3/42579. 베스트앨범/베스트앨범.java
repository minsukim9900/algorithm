import java.util.*;

class Solution {

    private static class Node {
        int idx;
        int count;

        public Node(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }

    private static class Info {
        String genre;
        int total;
        TreeSet<Node> nodes;

        public Info(String genre) {
            this.genre = genre;
            this.total = 0;

            nodes = new TreeSet<>((a, b) ->
                a.count == b.count
                    ? Integer.compare(a.idx, b.idx)
                    : Integer.compare(b.count, a.count)
            );
        }

        public void addNode(Node node) {
            total += node.count;
            nodes.add(node);
        }
    }

    public List<Integer> solution(String[] genres, int[] plays) {

        Map<String, Info> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];

            Info info = map.computeIfAbsent(
                genre,
                key -> new Info(key)
            );

            info.addNode(new Node(i, plays[i]));
        }

        TreeSet<Info> infos = new TreeSet<>((a, b) -> {
            if (a.total == b.total) {
                return a.genre.compareTo(b.genre);
            }

            return Integer.compare(b.total, a.total);
        });

        infos.addAll(map.values());

        List<Integer> answer = new ArrayList<>();

        while (!infos.isEmpty()) {
            Info info = infos.pollFirst();

            int count = 0;

            while (!info.nodes.isEmpty() && count < 2) {
                answer.add(info.nodes.pollFirst().idx);
                count++;
            }
        }

        return answer;
    }
}