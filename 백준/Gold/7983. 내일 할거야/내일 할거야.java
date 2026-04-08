
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] infos = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(infos, (a, b) -> Integer.compare(b[1], a[1]));

		int time = Integer.MAX_VALUE;

		for (int[] info : infos) {
			time = Math.min(time, info[1]);
			time -= info[0];
		}

		System.out.println(time);
	}
}