import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] nums;
    private static List<Integer> arr;
    private static Map<Integer, Integer> map = new HashMap<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];
        arr = new ArrayList<>();
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;

            if(map.containsKey(num)) {
                int count = map.get(num);
                map.put(num, count + 1);
            } else {
                map.put(num, 1);
                arr.add(num);
            }
        }
    }

    private static int cal() {
        arr.sort((a, b) -> Integer.compare(a, b));

        int left = 0;
        int right = arr.size() - 1;
        int answer = 0;

        while(left <= right) {
            int value = arr.get(left) + arr.get(right);

            if(value < K) {
                left++;
            }else if(value > K) {
                right--;
            } else {
                if(left < right) {
                    answer += (map.get(arr.get(left)) * map.get(arr.get(right)));
                } else {
                    int temp = map.get(arr.get(left));

                    answer += (temp) * (temp - 1) / 2;
                }
                left++;
                right--;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}