import java.io.*;
import java.util.*;

public class Main {

	private static int[] p;
	private static PriorityQueue<int[]> house;
		


	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			
			house = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2]- o2[2]	;
				}
				
				
			
			});
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m == 0 && n == 0) {
				break;
			}else {
				
				p = new int[m];
				
				for(int i = 0; i<m; i++) {
					p[i] = i;
				}
				long ans = 0;
				for(int i = 0; i<n; i++) {
					st = new StringTokenizer(br.readLine());
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					ans += w;
					house.offer(new int[] {x,y,w});
				}
				
				int pick = 0;
				
				for(int i = 0; i<n; i++) {
					int[] curr = house.poll();
					int px = findP(curr[0]);
					int py = findP(curr[1]);
					
					if(px != py) {
						union(px, py);
						ans -= curr[2];
						pick++;
					}
					
					if(pick >= m-1) {
						break;
					}
				}
				
				System.out.println(ans);
				
			}
		}
	}
	
	private static int findP(int x) {
		if(x != p[x]) {
			p[x] = findP(p[x]);
		}
		return p[x];
	}
	
	private static void union(int x, int y) {
		p[y] = x;
	}
	
}
