import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int value = Integer.parseInt(br.readLine());
		int[][] snail = new int[N][N];

		int number = N;
		int num = N * N;
		int direction = 1;
		int c = 0;
		int r = -1;

		for (int i = 0; i < N; i++) {

			// 열
			for (int j = 0; j < number; j++) {
				r += direction;
				snail[r][c] = num--;
			}

			number--;

			// 행
			for (int j = 0; j < number; j++) {
				c += direction;
				snail[r][c] = num--;
			}

			direction *= -1;
		}
		
		int x = 0;
		int y = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(snail[i][j] == value) {
					x = i+1;
					y = j+1;
				}
				sb.append(snail[i][j]).append(" ");
			}
			sb.append("\n");
		}
		sb.append(x).append(" ").append(y);
		System.out.println(sb.toString());

	}
}