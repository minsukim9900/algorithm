import java.io.*;
import java.util.*;

public class Solution {

	private static int N, X;
	private static int[][] map;
	private static ArrayList<int[]>[] rowInfo, columnInfo;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			rowInfo = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				rowInfo[i] = new ArrayList<>();
			}

			columnInfo = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				columnInfo[i] = new ArrayList<>();
			}

			saveInfo();
			sb.append("#" + t + " " + cntAirStrip() + "\n");
		}
		System.out.println(sb.toString());

	}

	private static int cntAirStrip() {
		int cnt = 0;

		for (ArrayList<int[]> line : rowInfo) {
			if(isPos(line)) cnt++;
		}
		
		for (ArrayList<int[]> line : columnInfo) {
			if(isPos(line)) cnt++;
		}

		return cnt;
	}

	private static boolean isPos(ArrayList<int[]> line) {

		int preH = line.get(0)[0];
		int preL = line.get(0)[1];

		for (int i = 1; i < line.size(); i++) {

			int currH = line.get(i)[0];
			int currL = line.get(i)[1];
			
			if(Math.abs(currH - preH) > 1) return false;
			
			if (preH < currH ) { // 오르막길
				// 오르막길은 이전의 길이가 X이상 길어야 오르막길을 만들 수 있어
				if (preL < X) {
					return false;
				}

				preH = currH;
				preL = currL;

			} else { // 내리막길
				// 내리막길은 이후의 길이가 X길이 이상 길어야 내리막길을 만들 수 있어
				if (currL < X) {
					return false;
				}

				preH = currH;
				preL = currL - X; // 내리막길을 지었으니까 그 길이를 없애줘

			}

		}

		return true;
	}

	private static void saveInfo() {

		for (int r = 0; r < N; r++) {

			int cnt = 1;
			int pre = map[r][0];

			for (int c = 1; c < N; c++) {

				if (pre == map[r][c]) {
					cnt++;
				} else {
					rowInfo[r].add(new int[] { pre, cnt });
					pre = map[r][c];
					cnt = 1;
				}
			}

			rowInfo[r].add(new int[] { pre, cnt });
		}

		for (int c = 0; c < N; c++) {

			int cnt = 1;
			int pre = map[0][c];

			for (int r = 1; r < N; r++) {

				if (pre == map[r][c]) {
					cnt++;
				} else {
					columnInfo[c].add(new int[] { pre, cnt });
					pre = map[r][c];
					cnt = 1;
				}
			}

			columnInfo[c].add(new int[] { pre, cnt });
		}

	}
}
