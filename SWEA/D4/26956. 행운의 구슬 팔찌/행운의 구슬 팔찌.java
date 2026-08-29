import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			LinkedList<Integer> arr = new LinkedList<>();

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());

				arr.add(num);
			}

			int cursor = 0;

			for (int i = 0; i < K; i++) {
				int size = arr.size();

				cursor = (cursor + M - 1) % size + 1;

				if (cursor == size) {
					int newNum = arr.get(size - 1) + arr.get(0);

					arr.add(newNum);
				} else {
					int leftNum = arr.get(cursor - 1);
					int rightNum = arr.get(cursor);

					arr.add(cursor, leftNum + rightNum);
				}
			}

			int size = arr.size();

			sb.append("#").append(t).append(" ");
			for (int i = size - 1; i >= Math.max(size - 10, 0); i--) {
				sb.append(arr.get(i)).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}
