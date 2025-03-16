import java.io.*;
import java.util.*;

public class Main {

	private static Deque<Integer> dq;
	private static String order;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			order = br.readLine();

			dq = new ArrayDeque<>();

			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			str = str.replaceAll("\\[|\\]", "");
			int[] nums = null;
			if (str.length() != 0) {
				nums = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
			}

			if (nums != null) {
				for (int i = 0; i < nums.length; i++) {
					dq.add(nums[i]);
				}
			}

			int state = 1;
			boolean isClear = false;

			for (int i = 0; i < order.length(); i++) {
				char method = order.charAt(i);

				if (method == 'R') {
					state *= -1;
				} else {

					if (dq.isEmpty()) {
						isClear = true;
						break;
					}

					if (state == 1) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}

				}
			}

			if (isClear) {
				sb.append("error" + "\n");
			} else {

				if (state == 1) {
					sb.append("[");
					while (!dq.isEmpty()) {

						sb.append(dq.pollFirst());
						if (dq.size() == 0) {
							break;
						}
						sb.append(",");

					}
					sb.append("]" + "\n");

				} else {
					sb.append("[");
					while (!dq.isEmpty()) {

						sb.append(dq.pollLast());
						if (dq.size() == 0) {
							break;
						}
						sb.append(",");

					}
					sb.append("]" + "\n");
				}

			}

		}
		System.out.println(sb.toString());

	}
}
