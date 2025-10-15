import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer>[] pq = new PriorityQueue[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq[i] = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
			for (int j = 0; j < M; j++) {
				pq[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		PriorityQueue<int[]> cal = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		int max = -1;

		for (int i = 0; i < N; i++) {
			int num = pq[i].poll();
			cal.add(new int[] { num, i });
			max = Math.max(max, num);
		}
		
		int ans = Integer.MAX_VALUE;
		
		while(true) {
			int[] curr = cal.poll();
			int min = curr[0];
			int minIdx = curr[1];
			ans = Math.min(ans, max - min);
			
			if(pq[minIdx].isEmpty()) break;
			
			int next = pq[minIdx].poll();
			max = Math.max(max, next);
			cal.add(new int[] {next, minIdx});
		}
        System.out.println(ans);
	}
}
