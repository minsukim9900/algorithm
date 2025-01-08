import java.io.*;
import java.util.*;

public class Main {

	private static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		int sum = 0;
		while (pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			sum += (a + b);
			pq.add(a+b);
		}
		System.out.println(sum);

	}

}
