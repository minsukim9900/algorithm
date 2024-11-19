import java.io.*;
import java.util.*;

public class Main {
	
	private static int N;
	private static char[][] boni;
	private static boolean result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		boni = new char[N][N];
		for(int r = 0; r<N; r++) {
			String str = br.readLine();
			for(int c = 0; c<N; c++) {
				char tmp = str.charAt(c);
				boni[r][c] = tmp;
			}
		}
		int max = cntSame();
		check(max);
		if(result) {
			System.out.println(N);
			return;
		}
		
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<N; c++) {
				swapRow(r,c);
				int tmp = cntSame();
				max = Math.max(max, tmp);
				if(result) {
					System.out.println(N);
					return;
				}
				swapRow(r,c);
			}
		}
		
		for(int c= 0; c<N; c++) {
			for(int r = 0; r<N; r++) {
				swapColumn(r, c);
				int tmp = cntSame();
				max = Math.max(max, tmp);
				if(result) {
					System.out.println(N);
					return;
				}
				swapColumn(r, c);
			}
		}
		
		System.out.println(max);
		
	}
	
	public static void swapRow(int r, int c) {
		if(c+1 == N) {
			return;
		}
		char tmp = boni[r][c];
		boni[r][c] = boni[r][c+1];
		boni[r][c+1] = tmp;
	}
	
	public static void swapColumn(int r, int c) {
		if(r + 1 == N) {
			return;
		}
		char tmp = boni[r][c];
		boni[r][c] = boni[r+1][c];
		boni[r+1][c] = tmp;
	}
	
	public static int cntSame() {
		int max = 0;
		int tmp = 0;
		for(int r = 0; r<N; r++) {
			int cnt = 1;
			for(int c = 0; c<N-1; c++) {
				if(boni[r][c] == boni[r][c+1]) {
					cnt++;
					tmp = cnt;
				}else {
					cnt = 1;
				}
				max = Math.max(max, cnt);
			}
		}
		
		tmp = 0;
		for(int c = 0; c<N; c++) {
			int cnt = 1;
			for(int r = 0; r<N-1; r++) {
				if(boni[r][c] == boni[r+1][c]) {
					cnt++;
					tmp = cnt;
				}else {
					cnt = 1;
				}
				max = Math.max(max, cnt);
			}
		}
		
		
		return max;
	}
	
	public static void check(int max) {
		if(max == N) {
			result = true;
		}
	}

}
