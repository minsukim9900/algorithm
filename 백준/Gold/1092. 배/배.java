import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] crane, boxes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int max = 0;

		N = Integer.parseInt(br.readLine());
		crane = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, crane[i]);
		}

		M = Integer.parseInt(br.readLine());
		boxes = new int[M];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			boxes[i] = Integer.parseInt(st.nextToken());
			if (max < boxes[i]) {
				System.out.println(-1);
				return;
			}
		}

		Arrays.sort(crane);
		Arrays.sort(boxes);
		int[] cranes = new int[N];
		int[] box = new int[M];
		for (int i = 0; i < N; i++) {
			cranes[i] = crane[N - 1 - i];
		}

		for (int i = 0; i < M; i++) {
			box[i] = boxes[M - 1 - i];
		}

		int move = 0;
		int time = 0;
		int[] pos = new int[N];
		boolean[] visited = new boolean[M];
		while (move < M) {
			for (int i = 0; i < N; i++) {
				
				while (pos[i] < M) {
					if (!visited[pos[i]] && cranes[i] >= box[pos[i]]) {
						visited[pos[i]] = true;
						move++;
						pos[i]++;
						break;
					}
					pos[i]++;
				}
			}
			time++;
		}
		System.out.println(time);
	}
}