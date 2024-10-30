import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] nums;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        B = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(B);

        int i = 0;
        int j = N-1;
        int sum = 0;
        while(i != N &&j != -1){
            sum += nums[i] * B[j];
            i++;
            j--;
        }
        System.out.println(sum);
    }
}