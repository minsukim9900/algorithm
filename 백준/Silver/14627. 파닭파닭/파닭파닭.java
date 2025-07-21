import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int S, C;
	private static long max;
	private static long[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		nums = new long[S];
		for (int i = 0; i < S; i++) {
			nums[i] = Long.parseLong(br.readLine());
			max = Math.max(max, nums[i]);
		}
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long len = -1;
		long s = 1;
		long e = max;

		while (s <= e) {
			long mid = (s + e) / 2;

			long[] info = check(mid);
			if (info[0] >= C) {
				s = mid + 1;
				len = info[1];
			} else {
				e = mid - 1;
			}
		}
		return len;
	}

	private static long[] check(long v) {
		long cnt = 0;
		long len = 0;

		for (long w : nums) {
			long tmp = w / v;
			cnt += tmp;
			len += (w - (tmp * v));
			if(cnt > C) {
				len += (cnt - C) * v;
			}
		}
		
		return new long[] { cnt, len };
	}
}
