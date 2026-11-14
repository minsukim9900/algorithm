
class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = (brown + 4) / 2;
        int area = brown + yellow;

        int discriminant = sum * sum - 4 * area;
        int sqrt = (int) Math.sqrt(discriminant);

        int w = (sum + sqrt) / 2;
        int h = (sum - sqrt) / 2;

        return new int[] {w, h};
    }
}
