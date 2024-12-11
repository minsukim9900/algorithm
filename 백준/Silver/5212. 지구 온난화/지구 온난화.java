import java.io.*;
import java.util.*;

public class Main {
	private static int[][] island;
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static Queue<int[]> land = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		island = new int[R + 2][C + 2];

		for (int x = 1; x <= R; x++) {
			String situ = br.readLine();
			for (int y = 1; y <= C; y++) {
				char tmp = situ.charAt(y - 1);

				if (tmp == 'X') {
					island[x][y] = 1;
					land.add(new int[] { x, y });
				}

			}

		}

		later();
		int[] now = new int[4];
		opti(now, R, C);
		
		
		for (int i = now[0]; i <= now[2] ; i++) {
			for (int j = now[1]; j <= now[3]; j++) {
				if(island[i][j] == 0) {
					sb.append(".");
				}else {
					sb.append("X");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static void later() {

		Queue<int[]> change = new ArrayDeque<>();

		while (!land.isEmpty()) {

			int[] curr = land.poll();

			int x = curr[0];
			int y = curr[1];
			int cnt = 0;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (island[nx][ny] == 0) {
					cnt++;
				}

			}

			if (cnt >= 3) {
				change.add(new int[] { x, y });
			}

		}

		transland(change);

	}

	private static void transland(Queue<int[]> change) {
		while (!change.isEmpty()) {
			int[] curr = change.poll();
			int x = curr[0];
			int y = curr[1];
			island[x][y] = 0;
		}
	}
	
	private static void opti(int[] now,int r, int c) {
		int minX = r+2;
		int minY = c+2;
		int maxX = 0;
		int maxY = 0;
		
		for (int i = 0; i < island.length; i++) {
			for (int j = 0; j < island[i].length; j++) {
				
				if(island[i][j] == 1) {
					minX = Math.min(minX, i);
					minY = Math.min(minY, j);
					maxX = Math.max(maxX, i);
					maxY = Math.max(maxY, j);
					
				}
				
			}
			
		}
		
		now[0] = minX;
		now[1] = minY;
		now[2] = maxX;
		now[3] = maxY;
	}

}
