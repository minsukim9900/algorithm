import java.io.*;
import java.util.*;

public class Main {

	private static int[][] tobni = new int[4][8];
	private static int[] currIdx = new int[4];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < 8; j++) {
				tobni[i][j] = str.charAt(j) - '0';
			}
			
		}

		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			circulate(idx - 1, dir);
			
		}
		
		int i = 0;
		int sum = 0;
		int x = 1;
		for(int idx : currIdx) {
			sum += (tobni[i++][idx] * x);
			x *= 2;
		}
		
		System.out.println(sum);

	}

	private static void circulate(int idx, int dir) {

		int left = tobni[idx][(6 + currIdx[idx]) % 8];
		int right = tobni[idx][(2 + currIdx[idx]) % 8];

		int ltmp = left;
		int tmpDir = dir;
		
		for (int i = idx - 1; i >= 0; i--) {

			if (tobni[i][(2 + currIdx[i]) % 8] == ltmp) {
				break;
			}
			ltmp = tobni[i][(6 + currIdx[i]) % 8];
			tmpDir *= -1;
			currIdx[i] = (currIdx[i] - tmpDir + 8) % 8;

		}

		int rtmp = right;
		tmpDir = dir;
		
		for (int i = idx + 1; i < 4; i++) {
			
			if (tobni[i][(6 + currIdx[i]) % 8] == rtmp) {
				break;
			}
			
			rtmp = tobni[i][(2 + currIdx[i]) % 8];
			tmpDir *= -1;
			currIdx[i] = (currIdx[i] - tmpDir + 8) % 8;

		}
		
		currIdx[idx] = (currIdx[idx] - dir + 8) % 8;
		
	}
}
