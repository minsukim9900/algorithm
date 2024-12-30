import java.io.*;
import java.util.*;

public class Main {

	private static int[] nums = new int[6];

	private static PriorityQueue<Integer>[] pq = new PriorityQueue[2];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < 2; i++) {
			pq[i] = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
		}

		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());

			if (dir == 1 || dir == 2) {
				pq[0].add(length);
			} else {
				pq[1].add(length);
			}

			nums[i] = length;

		}
		
		System.out.println(process() * N);

	}

	private static int process() {

		int heightMax = pq[0].poll();
		int widthMax = pq[1].poll();

		int hidx = 0;
		int widx = 0;

		for (int i = 0; i < 6; i++) {
			if (heightMax == nums[i]) {
				hidx = i;
			}
			if (widthMax == nums[i]) {
				widx = i;
			}
		}

		int extent = heightMax * widthMax;

		int left_Hidx = hidx - 1;
		if (left_Hidx == -1)
			left_Hidx = 5;
		int right_Hidx = hidx + 1;
		if (right_Hidx == 6)
			right_Hidx = 0;

		int subHeight = Math.abs(nums[left_Hidx] - nums[right_Hidx]);

		int left_Widx = widx - 1;
		if (left_Widx == -1)
			left_Widx = 5;
		int right_Widx = widx + 1;
		if (right_Widx == 6)
			right_Widx = 0;

		int subWidth = Math.abs(nums[left_Widx] - nums[right_Widx]);

		int subArea = subHeight * subWidth;

		return extent - subArea;
	}

}