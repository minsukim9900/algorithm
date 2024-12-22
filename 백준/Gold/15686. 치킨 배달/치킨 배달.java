import java.io.*;
import java.util.*;

public class Main {

	private static List<int[]> house = new ArrayList<>();
	private static List<int[]> chicken = new ArrayList<>();
	private static int[] result;
	private static int N, M;
	private static int total = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		result = new int[M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					house.add(new int[] { r, c });
				} else if (tmp == 2) {
					chicken.add(new int[] { r, c });
				}
			}

		}
		
		comb(0, 0);
		System.out.println(total);

	}

	private static void comb(int num, int depth) {
		if (depth == M) {
			List<int[]> choice = new ArrayList<>();
			
			for (int i = 0; i < M; i++) {
				int idx = result[i];
				choice.add(new int[] { chicken.get(idx)[0], chicken.get(idx)[1] });
			}
			
			calLength(choice);

		} else {
			for (int i = num; i < chicken.size(); i++) {
				result[depth] = i;
				comb(i + 1, depth + 1);
			}
		}
	}

	private static void calLength(List<int[]> choice) {
		
		int sum = 0;
		
		for (int i = 0; i < house.size(); i++) {
			int dist = Integer.MAX_VALUE;
			int[] home = house.get(i);

			for (int j = 0; j < choice.size(); j++) {
				int[] curr = choice.get(j);
				int tmp = Math.abs(home[0] - curr[0]) + Math.abs(home[1] - curr[1]);
				dist = Math.min(dist, tmp);
			}
			sum += dist;
		}
		
		total = Math.min(total, sum);

	}

}
