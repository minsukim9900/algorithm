import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static Queue<String> card1;
	private static Queue<String> card2;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			card1 = new ArrayDeque<>();
			card2 = new ArrayDeque<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			if(N % 2 == 0) {
				for (int i = 0; i < N / 2; i++) {
					card1.offer(st.nextToken());
				}
				
				for (int i = N / 2; i < N; i++) {
					card2.offer(st.nextToken());
				}
			}else {
				for (int i = 0; i <= N / 2; i++) {
					card1.offer(st.nextToken());
				}
				
				for (int i = N / 2 + 1; i < N; i++) {
					card2.offer(st.nextToken());
				}
			}
			
			shuffle(t);
		}
		
		System.out.println(sb.toString());

	}
	
	
	private static void shuffle(int t) {
		sb.append("#").append(t).append(" ");
		while(!card1.isEmpty()) {
			sb.append(card1.poll()).append(" ");
			if(card2.isEmpty()) break;
			sb.append(card2.poll()).append(" ");
		}
		sb.append("\n");
	}

}
