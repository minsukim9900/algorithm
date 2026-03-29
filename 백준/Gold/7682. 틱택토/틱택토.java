import java.io.*;

public class Main {
    private static final String VALID = "valid";
    private static final String INVALID = "invalid";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();
            if (s.equals("end")) break;

            int xCount = 0;
            int oCount = 0;

            for (int i = 0; i < 9; i++) {
                if (s.charAt(i) == 'X') xCount++;
                else if (s.charAt(i) == 'O') oCount++;
            }

            if (!(xCount == oCount || xCount == oCount + 1)) {
                sb.append(INVALID).append('\n');
                continue;
            }

            boolean xWin = isWin(s, 'X');
            boolean oWin = isWin(s, 'O');

            boolean result = false;

            if (xWin && oWin) {
                result = false;
            } else if (xWin) {
                result = (xCount == oCount + 1);
            } else if (oWin) {
                result = (xCount == oCount);
            } else {
                result = (xCount == 5 && oCount == 4);
            }

            sb.append(result ? VALID : INVALID).append('\n');
        }

        System.out.print(sb);
    }

    private static boolean isWin(String s, char ch) {
        return
            // 가로
            (s.charAt(0) == ch && s.charAt(1) == ch && s.charAt(2) == ch) ||
            (s.charAt(3) == ch && s.charAt(4) == ch && s.charAt(5) == ch) ||
            (s.charAt(6) == ch && s.charAt(7) == ch && s.charAt(8) == ch) ||

            // 세로
            (s.charAt(0) == ch && s.charAt(3) == ch && s.charAt(6) == ch) ||
            (s.charAt(1) == ch && s.charAt(4) == ch && s.charAt(7) == ch) ||
            (s.charAt(2) == ch && s.charAt(5) == ch && s.charAt(8) == ch) ||

            // 대각선
            (s.charAt(0) == ch && s.charAt(4) == ch && s.charAt(8) == ch) ||
            (s.charAt(2) == ch && s.charAt(4) == ch && s.charAt(6) == ch);
    }
}