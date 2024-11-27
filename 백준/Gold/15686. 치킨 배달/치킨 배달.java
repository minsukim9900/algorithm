import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, min;
	private static List<int[]> house = new ArrayList<>();
	private static List<int[]> chicken = new ArrayList<>();
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());

				if (tmp == 1) {
					house.add(new int[] { i + 1, j + 1 });
				} else if (tmp == 2) {
					chicken.add(new int[] { i + 1, j + 1 });
				}
			}

		}
		visited = new boolean[chicken.size()];
		
		dfs(0,0);
		
		System.out.println(min);
	}

	public static void dfs(int num, int depth) {

		if (depth == M) {
			
			int sum = 0;
			
			for (int i = 0; i < house.size(); i++) {
				int[] curr_house = house.get(i);
				int min_dis = Integer.MAX_VALUE;

				for (int j = 0; j < chicken.size(); j++) {
					if(visited[j]) {
						int[] curr_chicken = chicken.get(j);
						int tmp = Math.abs(curr_chicken[0] - curr_house[0]) + Math.abs(curr_chicken[1] - curr_house[1]);
						if (min_dis > tmp)
							min_dis = tmp;
					}
				}
				sum += min_dis;
			}
			if(min > sum) min = sum;
			
			

		} else {

			for (int i = num; i < chicken.size(); i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				dfs(i+1, depth + 1);
				visited[i] = false;
			}

		}

	}

}
