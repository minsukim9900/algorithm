class Solution {
    
    private static int w = 0;
    private static int h = 0;
    private static int B, Y;
    
    public int[] solution(int brown, int yellow) {
        B = brown;
        Y = yellow;
        int num = brown + 4;
        // 가로 * 2 + 세로 * 2 = NUM
        //a + b = num
        
        search(num);
        
        int[] answer = {w, h};
        return answer;
    }
    
    private static void search(int num) {
        int b = 2; // 세로 길이
        int a = num-2; // 가로 길이
        
        while( b <= a) {
            
            if(b % 2 == 0 && a % 2 == 0) {
                w = a / 2;
                h = b / 2;
                if(isCheck(w * h)) return;
            }
            
            b++;
            a--;
        }
        
    }
    
    private static boolean isCheck(int extent) {
        if((extent - B) == Y) return true;
        
        return false;
    }
}