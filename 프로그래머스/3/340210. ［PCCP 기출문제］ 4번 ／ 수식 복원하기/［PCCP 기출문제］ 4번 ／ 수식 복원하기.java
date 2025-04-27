import java.io.*;
import java.util.*;

class Solution {
    private static int N;
    private static String[] A, B, C, op1, op2;
    
    public ArrayList<String> solution(String[] expressions) {
        
        N = expressions.length;
        A = new String[N];
        B = new String[N];
        C = new String[N];
        op1 = new String[N];
        op2 = new String[N];
        
        int idx = 0;
        int minRange = 0;
        for(String str : expressions) {
            minRange = Math.max(minRange, findMinFormation(str));
            String[] tmp = str.split(" ");
            A[idx] = tmp[0];
            op1[idx] = tmp[1];
            B[idx] = tmp[2];
            op2[idx] = tmp[3];
            C[idx++] = tmp[4];
        }
        
        ArrayList<Integer> possibleBases = findPossibleBase(minRange);

        ArrayList<String> answer = fillValueX(possibleBases);
        return answer;
    }
    
    private static ArrayList<String> fillValueX(ArrayList<Integer> bases) {
        ArrayList<String> answer = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            if(!C[i].equals("X")) continue;
    
            Set<String> rs = new HashSet<>();
            
            for(int base : bases) {
                int a = toInt(A[i], base);
                int b = toInt(B[i], base);
                int c = op1[i].equals("+") ? (a + b) : (a - b);
                rs.add(fromInt(c, base));
            }
            
            String fill = "?";
             if(rs.size() == 1) {
                for(String s : rs) {
                    fill = s;
                }
            }
        
        answer.add(A[i] + " " + op1[i] + " " + B[i] + " " + op2[i] + " " + fill);
        }
        return answer;
    }
    
    private static String fromInt(int x, int base) {
        if(x == 0) return "0";
        StringBuilder sb = new StringBuilder();
        
        while(x > 0) {
            sb.append(x % base);
            x /= base;
        }
        return sb.reverse().toString();
    }
    
    private static ArrayList<Integer> findPossibleBase(int minRange) {
        
        ArrayList<Integer> possibleBase = new ArrayList<>();
        
        for(int i = minRange; i<=9; i++) {
            boolean isPoss = true;
            
            for(int j = 0; j < N; j++) {
                if(!C[j].equals("X")) {
                    int a = toInt(A[j], i);
                    int b = toInt(B[j], i);
                    
                    int c = op1[j].equals("+") ? (a + b) : (a - b);

                    if(c != toInt(C[j], i)) {
                        isPoss = false;
                        break;
                    }
                }
            }
            
            if(isPoss) {
                possibleBase.add(i);
            }
        }
    
        return possibleBase;
    }
    
    private static int toInt(String num, int base) {
        int rs = 0;

        for(int i = 0; i < num.length(); i++) {
            rs = rs * base + (num.charAt(i) - '0');
        }
        return rs;
    }
    
    private static int findMinFormation(String expression) {
        int max = 0;
        
        for(int i = 0; i< expression.length(); i++) {
            if(expression.charAt(i) >= '1' && expression.charAt(i) <= '9') {
                max = Math.max(max, expression.charAt(i));
            }
        }
        return max - '0' + 1;
    }
}