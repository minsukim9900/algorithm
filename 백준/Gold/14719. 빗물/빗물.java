import java.io.*;
import java.util.*;

public class Main {

	private static int H, W, max_idx;
	private static int[] room;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		room = new int[W];

		st = new StringTokenizer(br.readLine());

		int max = 0;
		max_idx = 0;

		for (int i = 0; i < W; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (max < tmp) {
				max = tmp;
				max_idx = i;
			}
			room[i] = tmp;
		}

		leftSide();
		rightSide();
		System.out.println(result);

	}

	private static void leftSide() {
		int idx = 0;
		int maxNum = 0;
		while (idx < max_idx) {
			maxNum = Math.max(maxNum, room[idx]);
			result += (maxNum - room[idx]);
			idx++;
		}
	}

	private static void rightSide() {
		int idx = W - 1;
		int maxNum = 0;
		while (idx > max_idx) {
			maxNum = Math.max(maxNum, room[idx]);
			result += (maxNum - room[idx]);
			idx--;
		}
	}
}
