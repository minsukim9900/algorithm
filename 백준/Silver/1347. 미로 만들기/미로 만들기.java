import java.io.*;
import java.util.*;

public class Main {

	private static int[] dr = { -1, 0, 1, 0 };
	private static int[] dc = { 0, -1, 0, 1 };
	private static int currR = 50;
	private static int currC = 50;
	private static char[][] maze = new char[101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		maze[currR][currC] = '.';
		String order = br.readLine();
		
		int dir = 2;
		int[] max = {currR, currC};
		int[] min = {currR, currC};
		for(int i = 0; i<N; i++) {
			char commit =  order.charAt(i);
			if(commit == 'R') {
				dir = (4+(dir-1)) % 4;
			}else if(commit == 'L') {
				dir = (4 + (dir+1)) % 4;
			}else {
				move(dir, max, min);
			}
		}
		
		
		for(int r = min[0]; r<=max[0]; r++) {
			for(int c = min[1]; c<=max[1]; c++) {
				if(maze[r][c] =='.') continue;
				else maze[r][c] = '#';
			}
		}
		
		for(int r = min[0]; r<=max[0]; r++) {
			for(int c = min[1]; c<=max[1]; c++) {
				sb.append(maze[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void move(int dir, int[] max, int[] min) {
		currR = currR + dr[dir];
		currC = currC + dc[dir];
		max[0] = Math.max(max[0], currR);
		max[1] = Math.max(max[1], currC);
		min[0] = Math.min(min[0], currR);
		min[1] = Math.min(min[1], currC);
		maze[currR][currC] = '.';
	}
	
}
