import java.io.*;
import java.util.*;

public class Main {

    private static int N, r, c;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(findZOrder((1 << N), 0, 0, 0));
    }

    private static int findZOrder(int size, int sr, int sc, int cnt) {
        if (size == 1) {
            return cnt;
        }

        int half = size / 2; 
        if (r < sr + half && c < sc + half) {
            return findZOrder(half, sr, sc, cnt);
        } 
        else if (r < sr + half && c >= sc + half) {
            return findZOrder(half, sr, sc + half, cnt + half * half);
        } 
        else if (r >= sr + half && c < sc + half) {
            return findZOrder(half, sr + half, sc, cnt + 2 * half * half);
        } 
        else if (r >= sr + half && c >= sc + half) {
            return findZOrder(half, sr + half, sc + half, cnt + 3 * half * half);
        }

        return cnt;
    }
}