class Solution {
    private int getNextNumber(int n) {
        int lowestSetBit = n & -n;
        int nextHigher = n + lowestSetBit;
        int remainingBits = ((nextHigher ^ n) >>> 2) / lowestSetBit;

        return nextHigher | remainingBits;
    }

    public int solution(int n) {
        return getNextNumber(n);
    }
}