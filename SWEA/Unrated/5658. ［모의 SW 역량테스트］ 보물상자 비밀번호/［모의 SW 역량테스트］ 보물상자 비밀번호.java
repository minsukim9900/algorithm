import java.io.*;
import java.util.*;

import javax.lang.model.type.UnionType;

public class Solution {
	private static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String pw = br.readLine();

			// 회전하면서 만든 숫자들
			Set<Integer> nums = simulate(pw);

			// 정렬을 하기 위해 arr 배열에 넣어둠
			Integer[] arr = new Integer[nums.size()];
			int idx = 0;

			// SET자료형 nums를 arr에 넣어두기 위한 작업
			Iterator<Integer> iter = nums.iterator();

			while (iter.hasNext()) {
				arr[idx++] = iter.next();
			}

			/////////////////////////////////////////
			// 내림차순 정렬
			// 내림차순 정렬을 하기위해서는 int[] 자료형이 아닌 Wrapper 자료형 Integer로
			// 배열 만들어서 정렬 시켜야함.
			Arrays.sort(arr, Collections.reverseOrder());

			// 출력하기 위해 StringBuilder에 저장
			sb.append("#" + t + " " + arr[K - 1] + "\n");
		}
		System.out.println(sb.toString());
	}

	// 자물쇠 회전하는 시뮬레이션
	private static Set<Integer> simulate(String pw) {
		Set<Integer> nums = new HashSet<>();
		char[] cr = pw.toCharArray();
		int side = N >> 2;
		
		// 회전 수 ( 0회전부터 ~ N/4 - 1회전까지)
		for (int i = 0; i < side; i++) {
			// 회전하고 한 면씩 
			for (int j = 0; j < N; j += side) {
				int num = 0;
				
				// 한 면에 나오는 숫자들 16진수로 변환하기
				for (int k = 0; k < side; k++) {
					int idx = (j + k - i + N) % N;
					int digit = Character.digit(cr[idx], 16);
					num = num * 16 + digit;
				}
				nums.add(num);
			}
		}

		return nums;

	}
}