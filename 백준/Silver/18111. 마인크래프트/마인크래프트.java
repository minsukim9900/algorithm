import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, B;
	private static int[][] land;
	private static int[]result = new int[] {Integer.MAX_VALUE,0};
	private static int time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		land = new int[N][M];

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				land[r][c] = tmp;
				max = Math.max(max, tmp);
				min = Math.min(min, tmp);
			}

		}
		for (int i = min; i <= max; i++) {
			cntTime(i);
		}
		
		for(int w : result) {
			sb.append(w).append(" ");
		}
		System.out.println(sb.toString());
		

	}

	public static void cntTime(int blockHeight) {
		time = 0;
		int inventory = B;
		
		for(int r = 0; r<N; r++) {
			
			for(int c = 0; c<M; c++) {
				
				if(land[r][c] == blockHeight) continue;
				
				else if(land[r][c] > blockHeight) {
					
					time += (land[r][c] - blockHeight) * 2;
					inventory += (land[r][c] - blockHeight);
				}
				
				else if(land[r][c] < blockHeight) {
					
					time += (blockHeight - land[r][c]);
					inventory -= (blockHeight-land[r][c]);
				}
				
			}
			
		}
		
		if(time < result[0] && inventory >= 0) {
			result[0] = time;
			result[1] = blockHeight;
		}
		else if(time == result[0] && blockHeight >= result[1] && inventory >= 0) {
			result[1] = blockHeight;
		}
		
	}


}
