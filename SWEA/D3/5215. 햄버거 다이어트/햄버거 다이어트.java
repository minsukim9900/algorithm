import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {

	private static int N, L;
	private static ArrayList<int[]> info;
	private static int branch;
	private static int[] resultScore;
	private static int[] resultKal;
	private static int max;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;
			info = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kal = Integer.parseInt(st.nextToken());
				info.add(new int[] { score, kal });
			}
			for (int i = 0; i <= N; i++) {
				branch = i;
				resultScore = new int[branch];
				resultKal = new int[branch];
				combination(0, 0);
			}
			
			System.out.println("#"+t+" "+ max);

		}

	}

	private static void combination(int num, int depth) {
		
		if (depth == branch) {
			
			int score = 0;
			int kal = 0;
			
			for (int i = 0; i < branch; i++) {
				score += resultScore[i];
				kal += resultKal[i];
			}
			
			if(kal > L) return;
			
			max = Math.max(max, score);
			
			
		} else {

			for (int i = num; i < N; i++) {
				
				int[] curr = info.get(i);
				resultScore[depth] = curr[0];
				resultKal[depth] = curr[1];
				combination(i + 1, depth + 1);

			}
		}

	}

}
