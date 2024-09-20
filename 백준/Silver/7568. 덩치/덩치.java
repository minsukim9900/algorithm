import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] weight = new int[N][2];
		int[] rank = new int[N];
		for (int i = 0; i < N; i++) {
			rank[i] = 1;
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < weight[i].length; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < N; j++) {
				
				
				if(weight[i][0] < weight[j][0] && weight[i][1] < weight[j][1]) {
					rank[i]++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			sb.append(rank[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}