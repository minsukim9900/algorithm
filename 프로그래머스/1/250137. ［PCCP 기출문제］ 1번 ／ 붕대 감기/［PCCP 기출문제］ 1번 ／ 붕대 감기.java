import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = simulate(health, bandage, attacks);
        return answer;
    }
    
    private static int simulate(int health, int[] bandage, int[][] attacks) {
        int hp = health;
        
        int preTime = 0;
        for(int i = 0; i < attacks.length; i++) {
            int[] curr = attacks[i];
            
            int currTime = curr[0];
            int damage = curr[1];
            
            int count = currTime - preTime - 1;
            
            int plus = count * bandage[1] + ((count / bandage[0]) * bandage[2]);
            hp = Math.min(health, hp + plus);
            hp -= damage;
            
            if(hp <= 0) {
                return -1;
            }
            
            preTime = currTime;
        }
        
        return hp;
    }
}