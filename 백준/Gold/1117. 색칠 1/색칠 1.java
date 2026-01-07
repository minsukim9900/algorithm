import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long W = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());
        long f = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        long rectangleCount = Math.abs(x2 - x1) * Math.abs(y2 - y1);
        long rightSideRectangleCount = rectangleCount * (c + 1);
        long temp = W - f;

        long overlap = Math.max(0, Math.min(x2, Math.min(W - f, f)) - x1);
        long leftSideRectrangleCount = overlap * (y2 - y1) * (c + 1);
        System.out.println((W * H) - leftSideRectrangleCount - rightSideRectangleCount);
    }
}