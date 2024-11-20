import java.io.*;
import java.util.*;

public class Main {

	private static int N, maxIdx;
	private static int[][] tower;
	private static int[] towerMax = new int[2];
	private static int sum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		tower = new int[1001][1];
		towerMax[1] = Integer.MIN_VALUE;
		maxIdx = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			if(towerMax[1] < height) {
				towerMax[0] = start;
				towerMax[1] = height;
			}
			if(maxIdx < start) {
				maxIdx = start;
			}
			tower[start][0] = height;
		}
		
		uphill();
		downhill();
		System.out.println(sum);
		
		
	}
	
	public static void uphill() {
		int tmp = tower[0][0];
		int height = tower[0][0];
		for(int i = 0; i<towerMax[0]; i++) {
			if(height >= tower[i+1][0]) {
				sum += height;
			}else if(height < tower[i+1][0]) {
				sum += height;
				height = tower[i+1][0];
			}
		}
	}
	
	public static void downhill() {
		int tmp = tower[maxIdx][0];
		int height = tower[maxIdx][0];
		for(int i = maxIdx; i>=towerMax[0]; i--) {
			if(height >= tower[i-1][0]) {
				sum += height;
			}else if(height < tower[i-1][0]) {
				sum += height;
				height = tower[i-1][0];
			}
		}
	}
	
	

}
