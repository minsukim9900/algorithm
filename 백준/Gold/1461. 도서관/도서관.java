import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<Integer>[] nums = new ArrayList[2];
		for (int i = 0; i < 2; i++) {
			nums[i] = new ArrayList<>();
		}

		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num < 0) {
				nums[0].add(-num);
			} else {
				nums[1].add(num);
			}
		}

		for (int i = 0; i < 2; i++) {
			nums[i].sort((a, b) -> Integer.compare(b, a));
		}

		int first = 0;
		int second = 1;

		if ((nums[0].isEmpty() && !nums[1].isEmpty())
				|| (!nums[0].isEmpty() && !nums[1].isEmpty() && nums[0].get(0) < nums[1].get(0))) {
			first = 1;
			second = 0;
		}

		System.out.println(cal(first, second, nums));
	}

	private static int cal(int first, int second, List<Integer>[] nums) {
		int answer = 0;
		answer += nums[first].get(0);

		for (int i = M; i < nums[first].size(); i += M) {
			answer += nums[first].get(i) * 2;
		}

		for (int i = 0; i < nums[second].size(); i += M) {
			answer += nums[second].get(i) * 2;
		}

		return answer;
	}
}