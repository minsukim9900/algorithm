import java.io.*;
import java.util.*;

public class Main {

	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int minX, minY, maxX, maxY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			String order = br.readLine();
			int[] location = new int[] { 0, 0, 0 };

			minX = location[0];
			minY = location[1];
			maxX = location[0];
			maxY = location[1];

			for (int c = 0; c < order.length(); c++) {
				int control = dir(order.charAt(c));
				process(location, control);
			}
			
			int totalX = Math.abs(maxX) + Math.abs(minX);
			int totalY = Math.abs(maxY) + Math.abs(minY);
			System.out.println(totalX * totalY);
		}

	}

	private static void process(int[] location, int control) {
		switch (control) {
		case 0:
			int curr_dir = location[2];
			int nx = location[0] + dx[curr_dir];
			int ny = location[1] + dy[curr_dir];

			min_max_process(nx, ny);
			location[0] = nx;
			location[1] = ny;
			break;
		case 1:
			curr_dir = location[2];
			nx = location[0] - dx[curr_dir];
			ny = location[1] - dy[curr_dir];
			min_max_process(nx, ny);
			location[0] = nx;
			location[1] = ny;
			break;
		case 2:
			curr_dir = location[2] - 1;
			if (curr_dir == -1) {
				curr_dir = 3;
			}
			location[2] = curr_dir;
			break;
		case 3:
			curr_dir = location[2] + 1;
			if (curr_dir == 4) {
				curr_dir = 0;
			}
			location[2] = curr_dir;
			break;
		}
	}

	private static int dir(char c) {

		if (c == 'F') {
			return 0;
		} else if (c == 'B') {
			return 1;
		} else if (c == 'L') {
			return 2;
		} else {
			return 3;
		}

	}

	private static void min_max_process(int x, int y) {
		minX = Math.min(minX, x);
		minY = Math.min(minY, y);
		maxX = Math.max(maxX, x);
		maxY = Math.max(maxY, y);
	}

}
