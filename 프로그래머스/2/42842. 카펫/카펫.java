class Solution {
    public int[] solution(int brown, int yellow) {
        
        for (int w = 1; w < 1_000_000; w++) {
            for (int h = 1; h < w + 1; h++) {
                if ((w + h) == (brown + 4) / 2 && (w * h) == yellow + brown) {
                    return new int[] {w, h};
                }
            }
        }
        
        
        return null;
    }
}