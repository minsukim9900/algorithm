import java.io.*;
import java.util.*;

public class Solution {
	private static int N, answer;
	private static int[][] board, stairs, dist;
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

			dist = new int[person.size()][2];
			for (int i = 0; i < person.size(); i++) {
				int r = person.get(i)[0];
				int c = person.get(i)[1];

				for (int j = 0; j < 2; j++) {
					dist[i][j] = Math.abs(r - stairs[j][0]) + Math.abs(c - stairs[j][1]);
				}
			}
			selectStair();
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void selectStair() {
		for (int mask = 0; mask < (1 << person.size()); mask++) {
			answer = Math.min(answer, goDownStair(mask));
		}
	}

	private static int goDownStair(int select) {
		PriorityQueue<int[]>[] pq = new PriorityQueue[2];
        
		for (int i = 0; i < 2; i++) {
			pq[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		}
		int time = Integer.MAX_VALUE;

		for (int i = 0; i < person.size(); i++) {
			int[] curr = person.get(i);
			int idx = ((select & (1 << i)) > 0) ? 1 : 0;
			time = Math.min(time, dist[i][idx]);

			pq[idx].add(new int[] { i + 1, dist[i][idx] });
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