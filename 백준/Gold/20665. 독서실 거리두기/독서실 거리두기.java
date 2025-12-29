import java.io.*;
import java.util.*;

public class Main {
	private static int N, T, P;
	private static int[] sheet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		sheet = new int[N + 1];

		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		for (int i = 0; i < T; i++) {
			pq.add(changeMinute(br.readLine()));
		}

		System.out.println(simulate(pq));
	}

	private static int simulate(PriorityQueue<int[]> pq) {
		TreeSet<Integer> set = new TreeSet<>();
		addSheet(set);

		int result = 0;

		for (int i = 540; i < 1260; i++) {
			checkout(i, set);

			while (!pq.isEmpty() && pq.peek()[0] == i) {
				int[] curr = pq.poll();
				
				if(curr[2] == 0) continue;

				int index = 1;

				if (set.size() != N) {
					index = search(set);
				}
				set.remove(index);

				sheet[index] = curr[1];

			}

			if (sheet[P] == 0) {
				result++;
			}
		}
		return result;

	}

	private static int search(TreeSet<Integer> set) {
		int distance = 0;
		int index = Integer.MAX_VALUE;

		for (int idx : set) {
			int minDis = Integer.MAX_VALUE;

			for (int i = 1; i <= N; i++) {
				if (sheet[i] == 0) {
					continue;
				}
				minDis = Math.min(minDis, Math.abs(idx - i));
			}

			if ((distance < minDis) || (distance == minDis && index > idx)) {
				index = idx;
				distance = minDis;
			}
		}
		return index;
	}

	private static void addSheet(TreeSet<Integer> set) {
		for (int i = 1; i <= N; i++) {
			if (sheet[i] == 0) {
				set.add(i);
			}
		}
	}

	private static void checkout(int time, TreeSet<Integer> set) {
		for (int i = 1; i <= N; i++) {
			if (sheet[i] == time) {
				sheet[i] = 0;
				set.add(i);
			}
		}
	}

	private static int[] changeMinute(String time) {
		int enter = ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(2) - '0') * 10
				+ time.charAt(3) - '0';

		int out = ((time.charAt(5) - '0') * 10 + (time.charAt(6) - '0')) * 60 + (time.charAt(7) - '0') * 10
				+ time.charAt(8) - '0';

		return new int[] { enter, out, out - enter };
	}
}