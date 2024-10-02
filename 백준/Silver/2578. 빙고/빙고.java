import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] bingo = new int[5][5];

		for (int r = 0; r < 5; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 5; c++) {
				bingo[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int count = 0;
		int[] cross_r1 = { 0, 1, 2, 3, 4 };
		int[] cross_c1 = { 4, 3, 2, 1, 0 };
		int[] cross_r2 = { 4, 3, 2, 1, 0 };
		int[] cross_c2 = { 4, 3, 2, 1, 0 };

		out2:for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int num = Integer.parseInt(st.nextToken());
				out: for (int nr = 0; nr < 5; nr++) {
					for (int nc = 0; nc < 5; nc++) {
						if (bingo[nr][nc] == num) {
							bingo[nr][nc] = 0;
							count++;
							break out;
						}
					}
				}
				
				
				
				
				
				int bingoCount = 0;
				
				for(int r = 0; r<5; r++) {
					int sum = 0;
					for(int c = 0; c<5; c++) {
						sum+=bingo[r][c];
					}
					if (sum == 0) {
						bingoCount++;
					}
				}
				
				for(int c = 0; c<5; c++) {
					int sum = 0;
					for(int r = 0; r<5; r++) {
						sum+=bingo[r][c];
					}
					if (sum == 0) {
						bingoCount++;
					}
				}
				
				int sum1 = 0;
				for(int k = 0; k<5; k++) {
					sum1 += bingo[cross_r1[k]][cross_c1[k]];
				}
				if(sum1 == 0) {
					bingoCount++;
				}
				
				int sum2 = 0;
				for(int k = 0; k<5; k++) {
					sum2 += bingo[cross_r2[k]][cross_c2[k]];
				}
				if(sum2 == 0) {
					bingoCount++;
				}
				
				if(bingoCount >= 3) {
					break out2;
				}
				
				

			}
		}
		System.out.println(count);
	}
}