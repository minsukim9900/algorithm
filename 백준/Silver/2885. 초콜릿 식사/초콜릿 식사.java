import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int size = Integer.highestOneBit(K);
        if (size != K) size <<= 1;

        int cnt = 0;
        int low = Integer.lowestOneBit(K);

        while (size > low) {
            size >>= 1;
            cnt++;
        }

        System.out.println((Integer.highestOneBit(K) == K ? K : Integer.highestOneBit(K) << 1) + " " + cnt);
    }
}