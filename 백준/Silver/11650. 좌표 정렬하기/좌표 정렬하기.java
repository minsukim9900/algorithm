import java.io.*;
import java.util.*;

public class Main {

	private static class Coordinate implements Comparable<Coordinate> {
		int x, y;

		public Coordinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coordinate o) {
			
			if(this.x == o.x) return this.y - o.y;
			
			return this.x - o.x;
		}


	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Coordinate[] c = new Coordinate[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			c[i] = new Coordinate(x, y);

		}

		Arrays.sort(c);
		for (Coordinate i : c) {
			sb.append(i.x).append(" ").append(i.y).append("\n");
		}
		System.out.println(sb);

	}

}
