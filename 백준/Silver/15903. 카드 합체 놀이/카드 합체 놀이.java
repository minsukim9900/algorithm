import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Long> pq = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
		
		System.out.println(game(M, pq));

	}

	private static long game(int M, PriorityQueue<Long> pq) {

		for (int i = 0; i < M; i++) {
			
			long numA = pq.poll();
			long numB = pq.poll();
			
			long tmp = numA + numB;
			pq.offer(tmp);
			pq.add(tmp);
		}
		
		long sum = 0;
		
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}

		return sum;
	}

}