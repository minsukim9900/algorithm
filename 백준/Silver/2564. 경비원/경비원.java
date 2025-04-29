import java.io.*;
import java.util.*;

public class Main {
	private static int R, C, N, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());

		P = (R + C) << 1;
		int[] info = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i] = distance(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		int pos = distance(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		System.out.println(simulate(pos, info));
	}

	private static int simulate(int pos, int[] info) {
		int ans = 0;

		for (int i = 0; i < info.length; i++) {
			int dis = Math.abs(pos - info[i]);
			
			ans += Math.min(dis, P - dis);
		}

		return ans;
	}

	private static int distance(int dir, int x) {

		switch (dir) {
		case 1: {
			return x;
		}
		case 4: {
			return C + x;
		}
		case 2: {
			return C + R + C - x;
		}
		case 3: {
			return (C << 1) + R + (R - x);
		}
		default:
			return 0;
		}

	}

}