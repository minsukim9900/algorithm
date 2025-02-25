import java.io.*;
import java.util.*;

public class Solution {

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[][] map;
	private static int N, M, K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			Queue<int[]> micro = new ArrayDeque<>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				map[r][c] = cnt;
				micro.add(new int[] { r, c, cnt, d });
			}
			Queue<int[]> q = bfs(micro);
			sb.append("#" + t + " ").append(cnt(q) + "\n");

		}

		System.out.println(sb.toString());

	}
	
	private static int cnt(Queue<int[]> q) {
		int sum = 0;
		
		while(!q.isEmpty()) {
			sum += q.poll()[2];
		}
		
		return sum;
	}

	private static Queue<int[]> bfs(Queue<int[]> q) {

		for (int t = 0; t < M; t++) {

			int size = q.size();
			
			
			ArrayList<int[]> ch = new ArrayList<>();
			for(int j = 0; j<size; j++) {
				

				int[] curr = q.poll();

				int r = curr[0];
				int c = curr[1];
				int nr = curr[0] + delta[curr[3] - 1][0];
				int nc = curr[1] + delta[curr[3] - 1][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					
					//레드 라인에 걸쳤을 때
					if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {
						int value = map[r][c] / 2;
						map[r][c] = 0;
						
						if(value != 0) {
							ch.add(new int[] { nr, nc, value, redir(curr[3]), 0 });
						}
						
					} else { // 안에서 뛰어놀고 있을 때,
						int value = map[r][c];
						map[r][c] = 0;
						ch.add(new int[] { nr, nc, value, curr[3], 1 });

					}

				}

			}
			
			// 미생물들이 이동하고 난 후 결과 값
			ArrayList<int[]> c = after(ch);
			
			// 결과 값을 다시 q에 넣어줌
			for(int[] w : c) {
				
				q.offer(w);
				
			}
			

		}
		
		return q;

	}
	
	// 맵의 값을 합치면서 이동시켜주는 함수
	private static ArrayList<int[]> after(ArrayList<int[]> s) {
		
		
		// r, c 오름차순, value 내림차순
		Collections.sort(s, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {

				if (o1[0] == o2[0]) {
					if (o1[1] == o2[1]) {
						return o2[2] - o1[2];
					}
					return o1[1] - o2[1];
				}

				return o1[0] - o2[0];
			}
		});
		
		// 합쳐졌을 때 방향을 정해주기 위해 visited 2차원 int 배열 선언
		int[][] visited = new int[N][N];

		for (int[] w : s) {
			
			// 레드라인에 걸치지 않고 맵 안에 있는 미생물들
			if (w[4] != 0) {
				
				// 가장 큰 값을 visited 배열에 저장해놓음
				if (visited[w[0]][w[1]] == 0) {
					visited[w[0]][w[1]] = w[3];
				}
				
				// 미생물들이 합쳐지는 연산
				map[w[0]][w[1]] += w[2];
				
				// 레드라인에 걸친 미생물들
			}else {
				visited[w[0]][w[1]] = w[3];
				map[w[0]][w[1]] = w[2];
			}

		}
		
		
		// 반환 해줄 move 객체
		ArrayList<int[]> move = new ArrayList<>();
		
		// 중복된 행 열 값을 걸러주기 위한 2차원 boolean 배열
		boolean[][] vis = new boolean[N][N];
		
		for (int[] w : s) {
			
			if(!vis[w[0]][w[1]]) {
				vis[w[0]][w[1]] = true;
				move.add(new int[] {w[0], w[1], map[w[0]][w[1]], visited[w[0]][w[1]]});
			}

		}
			
		return move;

	}
	
	// 레드 존에 왔을 때 방향을 반대로 바꿔줘야 함.
	private static int redir(int dir) {
		if (dir == 1)
			return 2;
		else if (dir == 2)
			return 1;
		else if (dir == 3)
			return 4;
		return 3;
	}

}