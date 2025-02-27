import java.util.*;
import java.io.*;

public class Main {

	private static int N, W, L;
	private static int[] truck;
	private static Queue<Integer> bridge = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		truck = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(process());

	}

	private static int process() {

		for (int i = 0; i < W; i++) {
			bridge.offer(0);
		}

		int cnt = 0;
		int sum = 0;
		int idx = 0;

		while (idx < N) {

			cnt++;

			sum -= bridge.poll();

			if (sum + truck[idx] > L) {

				bridge.offer(0);

			} else {

				bridge.offer(truck[idx]);
				sum += truck[idx++];

			}

		}

		return cnt + W;
	}

}