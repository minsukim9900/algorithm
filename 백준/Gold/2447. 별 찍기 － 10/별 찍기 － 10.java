import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static boolean[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		makeShape(0, 0, N);
		
		for(boolean[] r : map) {
			for(boolean c : r) {
				if(c) {
					sb.append("*");
				}else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void makeShape(int r, int c, int size) {
		if (size == 1) {
			map[r][c] = true;
			return;
		}

		int newSize = size / 3;
		int count = 1;

		for (int i = r; i < r + size; i += newSize) {
			for (int j = c; j < c + size; j += newSize) {
				if (count++ == 5)
					continue;
				makeShape(i, j, newSize);
			}
		}
	}
}