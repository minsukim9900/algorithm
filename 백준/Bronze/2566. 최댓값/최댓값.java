import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] grid = new int[9][9];
		for(int i=0;i<9;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = -1;
		int a = 0;
		int b = 0;
		for(int i=0; i<9;i++) {
			for(int j=0; j<9;j++) {
				if(max<grid[i][j]) {
					max = grid[i][j];
					a = i+1;
					b = j+1;
				}
			}
		}
		System.out.println(max);
		System.out.println(a + " " + b);
		
		
	}
}