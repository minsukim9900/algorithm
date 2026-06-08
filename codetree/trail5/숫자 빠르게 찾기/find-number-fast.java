import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(br.readLine());

            sb.append(binarySearch(target)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int binarySearch(int target) {
        int s = 0;
        int e = N - 1;
        int answer = 0;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (nums[mid] >= target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return nums[answer] == target ? answer + 1 : -1;
    }
}
