import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽 보이는 탑
        int[] leftCount   = new int[N];
        int[] leftNearest = new int[N];
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek()[0] <= nums[i]) {
                stack.pop();
            }
            leftCount[i]   = stack.size();
            leftNearest[i] = stack.isEmpty() ? -1 : stack.peek()[1];
            stack.push(new int[]{nums[i], i});
        }

        // 오른쪽 보이는 탑
        int[] rightCount   = new int[N];
        int[] rightNearest = new int[N];
        stack.clear();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek()[0] <= nums[i]) {
                stack.pop();
            }
            rightCount[i]   = stack.size();
            rightNearest[i] = stack.isEmpty() ? -1 : stack.peek()[1];
            stack.push(new int[]{nums[i], i});
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int total = leftCount[i] + rightCount[i];
            if (total == 0) {
                sb.append("0\n");
            } else {
                int nearestIdx;
                if (leftNearest[i] == -1) {
                    nearestIdx = rightNearest[i];
                } else if (rightNearest[i] == -1) {
                    nearestIdx = leftNearest[i];
                } else {
                    int ldist = i - leftNearest[i];
                    int rdist = rightNearest[i] - i;
                    // 거리 같으면 왼쪽 우선
                    nearestIdx = (ldist <= rdist) ? leftNearest[i] : rightNearest[i];
                }
                sb.append(total)
                  .append(' ')
                  .append(nearestIdx + 1)
                  .append('\n');
            }
        }
        System.out.print(sb);
    }
}