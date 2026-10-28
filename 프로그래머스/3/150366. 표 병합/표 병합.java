import java.util.*;

class Solution {
    private final int N = 2501;
    
    private int[] parent;
    private String[] values;
    
    
    private void init() {
        parent = new int[N];
        values = new String[N];
        
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            values[i] = "";
        }
    }
    
    private int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }
        
        return parent[node] = findParent(parent[node]);
    }
    
    private int[] getCoordinate(int idx) {
        return new int[] {idx / 50, idx % 50};
    }
    
    private int getIdx(int r, int c) {
        return ((r - 1) * 50) + ((c - 1) % 50);
    }
    
    private void union(int rootX, int rootY) {
        parent[rootY] = rootX;
        values[rootY] = values[rootX];
    }
    
    private void update(String value1, String value2) {
        for (int i = 0; i < N; i++) {
            if (value1.equals(values[i])) {
                values[i] = value2;
            }
        }
    }
    
    private void update(int r, int c, String value) {
        int node = getIdx(r, c);
        
        int rootNode = findParent(node);
        
        values[rootNode] = value;
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        int nodeX = getIdx(r1, c1);
        int nodeY = getIdx(r2, c2);
        
        int rootX = findParent(nodeX);
        int rootY = findParent(nodeY);
        
        if (!values[rootX].isEmpty()) {
            union(rootX, rootY);
        } else {
            union(rootY, rootX);
        }
    }
    
    private void unmerge(int r, int c) {
        int nodeX = getIdx(r, c);
        int rootX = findParent(nodeX);
        String text = values[rootX];
        
        List<Integer> members = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            int parentX = findParent(i);
            
            if (rootX == parentX) {
                members.add(i);
            }
        }
        
        for (int member : members) {
            parent[member] = member;
            values[member] = "";
        }
        
        values[nodeX] = text;
    }
    
    private String print(int r, int c) {
        int nodeX = getIdx(r, c);
        int rootX = findParent(nodeX); 
        
        return values[rootX].isEmpty() ? "EMPTY" : values[rootX];
    }
    
    public String[] solution(String[] commands) {
        init();
        
        List<String> answer = new ArrayList<>();
        
        for (String command : commands) {
            String[] com = command.split(" ");
            
            if ("UPDATE".equals(com[0])) {
                if (com.length == 3) {
                    String value1 = com[1];
                    String value2 = com[2];
                    
                    update(value1, value2);
                } else {
                    int r = Integer.parseInt(com[1]);
                    int c = Integer.parseInt(com[2]);
                    String value = com[3];
                    
                    update(r, c, value);
                }
            } else if ("MERGE".equals(com[0])) {
                int r1 = Integer.parseInt(com[1]);
                int c1 = Integer.parseInt(com[2]);
                int r2 = Integer.parseInt(com[3]);
                int c2 = Integer.parseInt(com[4]);
                
                if (r1 == r2 && c1 == c2) {
                    continue;
                }
                
                merge(r1, c1, r2, c2);
            } else if ("UNMERGE".equals(com[0])) {
                int r = Integer.parseInt(com[1]);
                int c = Integer.parseInt(com[2]);
                
                unmerge(r, c);
            } else {
                int r = Integer.parseInt(com[1]);
                int c = Integer.parseInt(com[2]);
                
                answer.add(print(r, c));
            }
        }
        
        return answer.toArray(new String[0]);
    }
}