import java.io.*;
import java.util.*;

public class Main {

	private static int N, W, L;
	private static int[] trucks;
	private static int count = 0;
	private static Queue<Integer> bridge = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		trucks = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int t = 0; t < N; t++) {
			trucks[t] = Integer.parseInt(st.nextToken());
		}

		for (int w = 0; w < W; w++) {
			bridge.add(0);
		}
		
		cross();
		
		System.out.println(count);

	}

	private static void cross() {
		int t = 0;
		int weight = 0;

		while (t < N) {
			weight -= bridge.poll();
			count++;
			if (weight + trucks[t] > L) {
				bridge.add(0);
			} else {
				bridge.add(trucks[t]);
				weight += trucks[t];
				t++;
			}

		}
		
		count+= W;

	}

}
