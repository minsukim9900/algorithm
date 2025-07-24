import java.io.*;
import java.util.*;

public class Main {
	private static int N, W, L;
	private static int[] trucks;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		trucks = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int time = 0;
		int idx = 0;

		Queue<Integer> bridge = new ArrayDeque<>();
		for (int i = 0; i < W; i++) {
			bridge.add(0);
		}
		int l = 0;

		while (true) {
			if (idx == N) {
				break;
			}

			time++;
			l -= bridge.poll();
			if (l + trucks[idx] <= L) {
				l += trucks[idx];
				bridge.add(trucks[idx++]);
			} else {
				bridge.add(0);
			}
		}
		return time += W;
	}
}