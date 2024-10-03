import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] x = new int[N];
		int[] y = new int[N];
		
		for(int i = 0; i<N;	i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int minx = 10001;
		int maxx = -10001;
		
		int miny = 10001;
		int maxy = -10001;
		
		for(int i = 0; i<N; i++) {
			if(minx > x[i]) {
				minx = x[i];
			}
			if(maxx < x[i]) {
				maxx = x[i];
			}
			
			if(miny > y[i]) {
				miny = y[i];
			}
			
			if(maxy < y[i]) {
				maxy = y[i];
			}
		}
		
		int a = Math.abs((maxx-minx) * (maxy-miny));
		System.out.println(a);
		
		
	}
}