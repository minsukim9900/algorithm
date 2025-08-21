import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int count = 0;
        while (N >= 0) {
            if (N % 5 == 0) {            // 5로 나누어떨어지면 종료
                count += N / 5;
                System.out.println(count);
                return;
            }
            N -= 3;                      // 3kg 하나 사용 (조정)
            count++;
        }
        System.out.println(-1);          // 정확히 만들 수 없음
    }
}