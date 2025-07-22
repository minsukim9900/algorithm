import java.io.*;
import java.util.*;

public class Solution {
	private static int N, K, answer;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			int max = 0;
			answer = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
					max = Math.max(max, board[r][c]);
				}
			}

			ArrayList<int[]> high = new ArrayList<>();

			/*
			 * high에 저장할 배열 내용은 가장 높은 정상부분의 정보를 저장한다.
			 */
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] == max) {
						high.add(new int[] { r, c });
					}
				}
			}

			for (int[] curr : high) {
				dfs(curr[0], curr[1], false, 1, new boolean[N][N]);
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, boolean isComplete, int distance, boolean[][] visited) {
		visited[r][c] = true;

		// 4방위 탐색
		for (int idx = 0; idx < 4; idx++) {
			int nr = r + delta[idx][0];
			int nc = c + delta[idx][1];
			// 범위에 들어왔는지 && 방문한 적이 없는지
			if (isRange(nr, nc) && !visited[nr][nc]) {
				int savePoint = board[nr][nc];
				// 다음 위치가 현재 위치보다 낮은 지형일 때
				if (board[nr][nc] < board[r][c]) {
					visited[nr][nc] = true;
					dfs(nr, nc, isComplete, distance + 1, visited);
				} else {
					// 다음 위치가 현재 위치보다 같거나 높은 지형일 때
					// 깍은 적이 없고 깎았을 때 내리막길이 될 경우에만
					if (!isComplete && board[nr][nc] - K < board[r][c]) {
						isComplete = true;
						board[nr][nc] = board[r][c] - 1;
						visited[nr][nc] = true;
						dfs(nr, nc, isComplete, distance + 1, visited);
						// 다시 깎았는지 여부 확인 복구
						isComplete = false;
					} else {
						// 이미 한 번 깍은 상태라면
						answer = Math.max(answer, distance);
					}
				}
				// 깎은 지형 다시 복구
				visited[nr][nc] = false;
				board[nr][nc] = savePoint;
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}