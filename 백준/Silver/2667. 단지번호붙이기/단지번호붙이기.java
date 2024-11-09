import java.io.*;
import java.util.*;

public class Main {
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N;
	private static ArrayList<Integer> result = new ArrayList<>();
	private static int[][] apt;
	private static boolean[][] check;
	private static int count = 0; // 아파트 동 갯수
	private static int cnt; // 각 아파트 방으 갯수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 총 배열의 크기, check의 크기이기도 함
		apt = new int[N][N];
		check = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			String num = br.readLine();
			for (int c = 0; c < N; c++) {
				int tmp = num.charAt(c) - '0';
				apt[r][c] = tmp;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!check[r][c] && apt[r][c] == 1) {
					cnt = 0;
					count++;
					cntApt(r, c);
					result.add(cnt);
				}
			}
		}
		
		Collections.sort(result);

		sb.append(count).append("\n");
		for(int i = 0; i<result.size(); i++) {
			sb.append(result.get(i)).append("\n");
		}
	

		System.out.println(sb.toString());

	}

	public static void cntApt(int r, int c) {
		check[r][c] = true;
		cnt++;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				int num = apt[nr][nc];
				if (num == 1 && !check[nr][nc]) {
					cntApt(nr, nc);
				}
			}
		}
	}
}
