import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] square = new int[101][101];
		for(int t = 0; t<4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			for(int i = fx; i<sx; i++) {
				for(int j = fy; j<sy; j++) {
					square[i][j] = 1;
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i<square.length; i++) {
			for(int j = 0; j<square[i].length; j++) {
				if(square[i][j] == 1) count++;
			}
		}
		
		System.out.println(count);
	}
}