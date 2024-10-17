import java.io.*;
import java.util.*;

public class Main {



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N][M];
		for(int r = 0; r<N; r++) {
			st= new StringTokenizer(br.readLine());
			for(int c = 0; c<M; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int nM = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] B = new int[nM][K];
		
		for(int r = 0; r <nM; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<K; c++) {
				B[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] result = new int[N][K];
		
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<K; c++) {
				for(int l = 0; l<M; l++) {
					result[r][c] += A[r][l] * B[l][c];
				}
			}
		}
		
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<K; c++) {
				sb.append(result[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		

	}
}