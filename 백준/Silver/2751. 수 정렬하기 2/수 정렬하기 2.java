import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] nums;
    private static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        tmp = new int[N];
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0,N-1);

        for(int i = 0; i < N; i++){
            sb.append(nums[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void mergeSort(int left, int right){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private static void merge(int left, int mid, int right) {
        int L = left;
        int R = mid + 1;
        int idx = left;
        while(L <= mid && R <= right){
            if(nums[L] <= nums[R]){
                tmp[idx++] = nums[L++];
            }else{
                tmp[idx++] = nums[R++];
            }
        }

        if(L <= mid){
            for(int i = L; i <= mid; i++ ){
                tmp[idx++] = nums[i];
            }
        }else{
            for(int i = R; i<=right; i++){
                tmp[idx++] = nums[i];
            }
        }

        for(int i = left; i<= right; i++){
            nums[i] = tmp[i];
        }
    }

}