import java.io.*;
import java.util.*;

public class Main {
	private static long N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Long.parseLong(br.readLine());
		long M = 1;
		
		while(M < N) {
			M = (M << 1) + 1;
		}
		
		if(M == N) {
			sb.append(1).append("\n");
			sb.append(N);
		}else {
			sb.append(2).append("\n");
			sb.append(M ^ N).append("\n");
			sb.append(N);
		}
		
		System.out.println(sb.toString());
	}
}