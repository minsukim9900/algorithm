import java.io.*;
import java.util.*;

public class Main {

	private static int x, y, z;
	private static Queue<int[]> q = new ArrayDeque<>();
	private static int[][][] tomatoBox;
	// 상, 하, 좌, 우, 위, 아래
	private static int[] dx = { -1, 1, 0, 0, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1, 0, 0 };
	private static int[] dz = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		z = Integer.parseInt(st.nextToken());
		tomatoBox = new int[z][y][x];
		
		for (int i = 0; i < z; i++) {
			for (int j = 0; j < y; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < x; k++) {
					int tmp = Integer.parseInt(st.nextToken());
					tomatoBox[i][j][k] = tmp;
					if (tmp == 1)
						q.add(new int[] { k, j, i, 0 });
				}
			}
		}
		
		int result = bfs();
		
		for (int i = 0; i < z; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < x; k++) {
					if(tomatoBox[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(result);
	}

	public static int bfs() {
		int max = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			max = curr[3];
			for (int i = 0; i < 6; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				int nz = curr[2] + dz[i];
				
				if (nx >= 0 && nx < x && ny >= 0 && ny < y && nz >= 0 && nz < z) {
					
					if (tomatoBox[nz][ny][nx] == 0) {
						q.add(new int[] { nx, ny, nz, curr[3]+1} );
						tomatoBox[nz][ny][nx] = 1;
					}
					
				}
				
			}
			
		}
		
		return max;

	}

}
