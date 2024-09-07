import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] whitePaper = new int[100][100];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j = x; j<x+10; j++) {
				for(int k = y; k<y+10; k++) {
					whitePaper[j][k]=1;
				}
				
			}
		}
		
		int count = 0;
		
		for(int i=0; i<whitePaper.length;i++) {
			for(int j=0; j<whitePaper[i].length; j++) {
				if(whitePaper[i][j] >= 1) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
		
	}
}