import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, max;
	private static int[] comp;
	private static ArrayDeque<Integer> dq = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		comp = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			comp[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			dq.add(i);
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int idx = 0;
		int cnt = 0;

		while (idx != M) {
			if(dq.peekFirst() == comp[idx]) {
				dq.pollFirst();
				idx++;
				continue;
			}
			
			int left = cntLeft(dq.clone(), comp[idx]);
			int right = cntRight(dq.clone(), comp[idx]);
			
			if(left <= right) {
				while(dq.peekFirst() != comp[idx]) {
					dq.addLast(dq.pollFirst());
					cnt++;
				}
				dq.pollFirst();
				idx++;
				continue;
			}else {
				while(dq.peekFirst() != comp[idx]) {
					dq.addFirst(dq.pollLast());
					cnt++;
				}
				dq.pollFirst();
				idx++;
				continue;
			}
		}
		return cnt;
	}
	
	private static int cntLeft(ArrayDeque<Integer> dq, int comp) {
		int cnt = 0;
		
		while(dq.peekFirst() != comp) {
			dq.addLast(dq.pollFirst());
			cnt++;
		}
		
		return cnt;
	}
	
	private static int cntRight(ArrayDeque<Integer> dq, int comp) {
		int cnt = 0;
		
		while(dq.peekFirst() != comp) {
			dq.addFirst(dq.pollLast());
			cnt++;
		}
		
		return cnt;
	}
}