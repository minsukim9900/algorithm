import java.util.*;

class Solution {
    private static int N, W;
    private static int[] trucks;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        N = bridge_length;
        W = weight;
        trucks = truck_weights;
        
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < N; i++) {
            q.add(0);
        }
        
        int time = 0;
        int currWeight = 0;
        int outCount = 0;
        int idx = 0;
        
        while (outCount != trucks.length) {
            time++;
            int truck = q.poll();
            
            if (truck > 0) {
                outCount++;
                currWeight -= truck;
            }
            
            int next = 0;
            
            if (idx < trucks.length && currWeight + trucks[idx] <= W) {
                next = trucks[idx++];
            }
            
            currWeight += next;
            q.add(next);
        }
        
        return time;
    }
}