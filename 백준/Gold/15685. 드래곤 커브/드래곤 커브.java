import java.io.*;
import java.util.*;

public class Main {
    
   private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static boolean[][] map = new boolean[101][101];

	private static int[][] delta = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = read();

		for (int i = 0; i < N; i++) {
			int c = read();
			int r = read();
			int dir = read();
			int gen = read();
            
			int[] info = makeGeneration(dir, gen);
			visit(r, c, info);
		}
		int cnt = 0;
        
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (map[r][c] && map[r + 1][c] && map[r][c + 1] && map[r + 1][c + 1])
					cnt++;
			}
		}

		System.out.println(cnt);

	}

	private static void visit(int r, int c, int[] info) {
		map[r][c] = true;
        
		for (int i = 0; i < info.length; i++) {
			r += delta[info[i]][0];
			c += delta[info[i]][1];
			map[r][c] = true;
		}

	}

	private static int[] makeGeneration(int dir, int gen) {
		int[] info = new int[(1 << gen)];
		int l = 1;
		info[0] = dir;

		for (int g = 0; g < gen; g++) {
			for (int i = l - 1; i >= 0; i--) {
				int nd = (info[i] + 1) & 3;
				info[l++] = nd;
			}
		}

		return info;
	}
}
