import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        nums = new boolean[2000001];
        for(int i = 0; i < N; i++){
            nums[Integer.parseInt(br.readLine())+1000000] = true;
        }


        for(int i = 0; i < nums.length; i++){
            if(nums[i]) sb.append(i-1000000).append("\n");
        }
        System.out.println(sb.toString());
    }


}