import java.io.*;
import java.util.*;

public class Solution {
	private static int N, answer;
	private static int[][] board, stairs;
	private static ArrayList<int[]> person;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			person = new ArrayList<>();
			board = new int[N][N];
			stairs = new int[2][3];
			answer = Integer.MAX_VALUE;
			int tempIdx = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] == 1) {
						person.add(new int[] { r, c });
					} else if (board[r][c] > 1) {
						stairs[tempIdx][0] = r;
						stairs[tempIdx][1] = c;
						stairs[tempIdx++][2] = board[r][c];
					}
				}
			}
			selectStair(0, 0);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void selectStair(int depth, int select) {
		if (depth == person.size()) {
			answer = Math.min(answer, goDownStair(select));
			return;
		}
		// A 계단을 이용할 때
		selectStair(depth + 1, select);

		// B 계단을 이용할 때
		select |= (1 << depth);
		selectStair(depth + 1, select);
	}

	private static int goDownStair(int select) {
		PriorityQueue<int[]>[] pq = new PriorityQueue[2];
		for (int i = 0; i < 2; i++) {
			pq[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		}
		int time = Integer.MAX_VALUE;

		for (int i = 0; i < person.size(); i++) {
			int[] curr = person.get(i);
			int idx = 0;

			if ((select & (1 << i)) > 0) {
				idx = 1;
			}
			int distance = Math.abs(stairs[idx][0] - curr[0]) + Math.abs(stairs[idx][1] - curr[1]);
			time = Math.min(time, distance);

			pq[idx].add(new int[] { i + 1, distance });
		}

		Queue<int[]>[] q = new ArrayDeque[2];
		for (int i = 0; i < 2; i++) {
			q[i] = new ArrayDeque<>();
		}

		int count = person.size();

		while (count >= 1) {
			time++;

			// 먼저 계단에 있는 사람 한 칸 이동한다.
			for (int i = 0; i < 2; i++) {
				if (q[i].isEmpty()) {
					continue;
				}

				while (!q[i].isEmpty() && q[i].peek()[1] + stairs[i][2] == time) {
					q[i].poll();
					count--;
				}
			}

			// 도착하거나 대기하고 있는 사람을 계단에 넣는다.
			for (int i = 0; i < 2; i++) {
				// 대기자가 없을 경우는 패스'
				if (pq[i].isEmpty()) {
					continue;
				}

				while (true) {
					if (q[i].size() == 3 || (!pq[i].isEmpty() && pq[i].peek()[1] + 1 > time)) {
						break;
					}
					if (pq[i].isEmpty()) {
						break;
					}
					int[] curr = pq[i].poll();
					q[i].add(new int[] { curr[1], time });
				}
			}
		}
		return time;
	}
}