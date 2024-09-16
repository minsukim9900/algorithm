import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken()); // 층 수
			int W = Integer.parseInt(st.nextToken()); // 객실 수
			int N = Integer.parseInt(st.nextToken());
			int dy = 1; // 층을 나타내는 변화량
			int dx = 1; // 호실을 나타내는 변화량
			int[][] room = new int[W][H];
			int count = 0;

			// 호텔 객실 2차원 배열
			for (int c = 0; c < room.length; c++) {
				for (int r = 0; r < room[c].length; r++) {
					count++;
					room[c][r] = (dy*100)+dx;
					dy++;
					if(dy ==H+1) {
						dy =1;
					}
					if(count == N) {
						System.out.println(room[c][r]);
						break;
					}
				}
				dx++;
			}
			

		}
	}
}